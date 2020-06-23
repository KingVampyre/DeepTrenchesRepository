package github.kingvampire.DeepTrenches.api.entity.goals;

import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;
import static github.kingvampire.DeepTrenches.core.init.ModAttributes.MOVEMENT_SPEED_BOOST;
import static net.minecraft.entity.ai.goal.Goal.Flag.MOVE;

import java.util.EnumSet;

import github.kingvampire.DeepTrenches.api.capabilities.anger.IAnger;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.util.LazyOptional;

public class ChargeAttackGoal extends Goal {

    protected CreatureEntity creature;
    protected double maxDist;
    protected double minDist;

    public ChargeAttackGoal(CreatureEntity creature, double maxDist, double minDist) {
	this.creature = creature;

	this.maxDist = maxDist;
	this.minDist = minDist;

	this.setMutexFlags(EnumSet.of(MOVE));
    }

    protected void moveToAttackTarget(LivingEntity target) {
	double speedBoost = this.creature.getAttribute(MOVEMENT_SPEED_BOOST).getValue();

	Vec3d vec3d = target.getEyePosition(1F);

	this.creature.getMoveHelper().setMoveTo(vec3d.x, vec3d.y, vec3d.z, speedBoost);
    }

    @Override
    public boolean shouldContinueExecuting() {
	LazyOptional<IAnger> anger = this.creature.getCapability(ANGER_CAPABILITY);

	if (anger.isPresent()) {
	    IAnger ianger = anger.orElseThrow(IllegalArgumentException::new);

	    MovementController moveController = creature.getMoveHelper();
	    LivingEntity target = this.creature.getAttackTarget();

	    if (ianger.isAngry() && moveController.isUpdating())
		return target != null && target.isAlive();
	}

	return false;
    }

    @Override
    public boolean shouldExecute() {
	LazyOptional<IAnger> anger = this.creature.getCapability(ANGER_CAPABILITY);

	if (anger.isPresent()) {
	    IAnger ianger = anger.orElseThrow(IllegalArgumentException::new);

	    LivingEntity target = this.creature.getAttackTarget();
	    MovementController moveController = this.creature.getMoveHelper();

	    if (ianger.isAngry() && !moveController.isUpdating())
		return target != null && this.creature.getDistanceSq(target) >= this.minDist;
	}

	return false;
    }

    @Override
    public void startExecuting() {
	this.moveToAttackTarget(this.creature.getAttackTarget());
    }

    @Override
    public void tick() {
	LivingEntity target = this.creature.getAttackTarget();
	AxisAlignedBB aabb = target.getBoundingBox();

	if (this.creature.getBoundingBox().intersects(aabb))
	    this.creature.attackEntityAsMob(target);

	else if (this.creature.getDistanceSq(target) <= this.maxDist)
	    this.moveToAttackTarget(target);
    }

}