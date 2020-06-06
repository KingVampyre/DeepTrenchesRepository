package github.kingvampire.DeepTrenches.api.entity.goals;

import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.entity.DragonfishEntity.PREY_DETECTION;

import github.kingvampire.DeepTrenches.api.capabilities.anger.IAnger;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public class PreyingGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {

    public PreyingGoal(CreatureEntity creature, Class<T> targetClass) {
	super(creature, targetClass, true);
    }

    protected EntityPredicate getEntityPredicate() {
	return this.targetEntitySelector;
    }

    @Override
    protected AxisAlignedBB getTargetableArea(double targetDistance) {
	return this.goalOwner.getBoundingBox().grow(targetDistance);
    }

    @Override
    protected double getTargetDistance() {
	return this.goalOwner.getAttribute(PREY_DETECTION).getValue();
    }

    @Override
    public void resetTask() {
	this.target = null;
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

	    if (!ianger.isAngry()) {
		this.nearestTarget = world.getClosestEntityWithinAABB(this.targetClass, predicate, null, x, y, z, box);

		return this.nearestTarget != null && this.nearestTarget != this.goalOwner;
	    }
	}

	return false;

    }

    @Override
    public void startExecuting() {
	LazyOptional<IAnger> anger = this.goalOwner.getCapability(ANGER_CAPABILITY);

	if (anger.isPresent()) {
	    IAnger ianger = anger.orElseThrow(IllegalArgumentException::new);

	    ianger.setAnger(this.nearestTarget);
	    super.startExecuting();
	}
    }

}
