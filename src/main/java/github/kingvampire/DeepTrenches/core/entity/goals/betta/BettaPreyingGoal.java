package github.kingvampire.DeepTrenches.core.entity.goals.betta;

import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.group.GroupProvider.GROUP_CAPABILITY;
import static net.minecraft.entity.SharedMonsterAttributes.FOLLOW_RANGE;

import java.util.List;
import java.util.stream.Collectors;

import github.kingvampire.DeepTrenches.api.capabilities.anger.IAnger;
import github.kingvampire.DeepTrenches.api.capabilities.group.IGroup;
import github.kingvampire.DeepTrenches.api.entity.goals.PreyingGoal;
import github.kingvampire.DeepTrenches.core.entity.BettaEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public class BettaPreyingGoal extends PreyingGoal<BettaEntity> {

    protected final IGroup igroup;

    public BettaPreyingGoal(BettaEntity bettaEntity) {
	super(bettaEntity, BettaEntity.class);

	this.igroup = bettaEntity.getCapability(GROUP_CAPABILITY).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    protected double getTargetDistance() {
	return this.goalOwner.getAttribute(FOLLOW_RANGE).getBaseValue();
    }

    @Override
    public boolean shouldExecute() {
	double distance = this.getTargetDistance();

	AxisAlignedBB box = this.getTargetableArea(distance);
	EntityPredicate predicate = this.getEntityPredicate();
	World world = this.goalOwner.getEntityWorld();

	double x = this.goalOwner.posX;
	double y = this.goalOwner.posY;
	double z = this.goalOwner.posZ;

	LazyOptional<IAnger> anger = this.goalOwner.getCapability(ANGER_CAPABILITY);

	if (anger.isPresent()) {
	    IAnger ianger = anger.orElseThrow(IllegalArgumentException::new);

	    if (!ianger.isAngry() && this.igroup.isGroupLeader()) {
		List<BettaEntity> bettas = world.getEntitiesWithinAABBExcludingEntity(this.goalOwner, box)
			.stream()
			.filter(this.targetClass::isInstance)
			.map(this.targetClass::cast)
			.filter(betta -> !this.igroup.getMembers().contains(betta))
			.collect(Collectors.toList());

		this.nearestTarget = world.getClosestEntity(bettas, predicate, null, x, y, z);

		if (this.nearestTarget != null)
		    return this.goalOwner.getRNG().nextDouble() < 0.02;
	    }
	}

	return false;

    }

    @Override
    protected EntityPredicate getEntityPredicate() {
	return super.getEntityPredicate().setCustomPredicate(living -> {
	    CreatureEntity creature = (CreatureEntity) living;

	    return !this.igroup.getMembers().contains(creature);
	});
    }

}
