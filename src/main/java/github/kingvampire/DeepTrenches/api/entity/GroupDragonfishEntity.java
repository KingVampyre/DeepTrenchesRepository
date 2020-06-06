package github.kingvampire.DeepTrenches.api.entity;

import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.group.GroupProvider.GROUP_CAPABILITY;

import github.kingvampire.DeepTrenches.api.capabilities.anger.IAnger;
import github.kingvampire.DeepTrenches.api.entity.goals.GroupAngerGoal;
import github.kingvampire.DeepTrenches.api.entity.goals.UntamedFollowGroupLeaderGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public abstract class GroupDragonfishEntity extends DragonfishEntity {

    public GroupDragonfishEntity(EntityType<? extends GroupDragonfishEntity> type, World worldIn) {
	super(type, worldIn);
    }

    @Override
    public void onDeath(DamageSource cause) {
	super.onDeath(cause);

	this.getCapability(GROUP_CAPABILITY).ifPresent(group -> group.onDeath(cause));
    }

    @Override
    protected void registerGoals() {
	super.registerGoals();

	this.goalSelector.addGoal(3, new UntamedFollowGroupLeaderGoal(this));

	this.targetSelector.addGoal(0, new GroupAngerGoal(this));
    }

    @Override
    public void livingTick() {
	super.livingTick();

	LazyOptional<IAnger> anger = this.getCapability(ANGER_CAPABILITY);

	if (!world.isRemote() && anger.isPresent()) {
	    IAnger ianger = anger.orElseThrow(IllegalArgumentException::new);

	    ianger.livingTick();

	    LivingEntity target = this.getAttackTarget();

	    if (target == null && ianger.isAngry())
		ianger.setAnger(0);

	    if (target != null && !ianger.isAngry())
		this.setAttackTarget(null);

	    if (target != null && !target.isAlive() && ianger.isAngry()) {
		ianger.setAnger(0);

		this.setAttackTarget(null);
	    }

	}

    }

}
