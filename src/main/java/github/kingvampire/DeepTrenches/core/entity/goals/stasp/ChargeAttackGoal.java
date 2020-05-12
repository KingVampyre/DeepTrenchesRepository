package github.kingvampire.DeepTrenches.core.entity.goals.stasp;

import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;
import static net.minecraft.entity.SharedMonsterAttributes.FLYING_SPEED;
import static net.minecraft.entity.ai.goal.Goal.Flag.MOVE;

import java.util.EnumSet;

import github.kingvampire.DeepTrenches.api.capabilities.anger.IAnger;
import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.util.LazyOptional;

public class ChargeAttackGoal extends Goal {

    private MovementController moveController;
    private double speed;
    private StaspEntity staspEntity;

    public ChargeAttackGoal(StaspEntity staspEntity, double speed) {
	this.moveController = staspEntity.getMoveHelper();
	this.staspEntity = staspEntity;
	this.speed = speed;

	this.setMutexFlags(EnumSet.of(MOVE));
    }

    private void moveToAttackTarget(LivingEntity target) {
	double flyingSpeed = this.staspEntity.getAttribute(FLYING_SPEED).getValue();

	Vec3d vec3d = target.getEyePosition(1.0F);

	this.moveController.setMoveTo(vec3d.x, vec3d.y, vec3d.z, this.speed * flyingSpeed);
    }

    @Override
    public boolean shouldContinueExecuting() {
	LazyOptional<IAnger> anger = this.staspEntity.getCapability(ANGER_CAPABILITY);

	if (anger.isPresent()) {
	    IAnger ianger = anger.orElseThrow(IllegalArgumentException::new);
	    LivingEntity target = this.staspEntity.getAttackTarget();

	    if (this.moveController.isUpdating() && ianger.isAngry())
		return target != null && target.isAlive();
	}

	return false;
    }

    @Override
    public boolean shouldExecute() {
	LazyOptional<IAnger> anger = this.staspEntity.getCapability(ANGER_CAPABILITY);

	if (anger.isPresent()) {
	    IAnger ianger = anger.orElseThrow(IllegalArgumentException::new);
	    LivingEntity target = this.staspEntity.getAttackTarget();

	    if (target != null && !this.moveController.isUpdating() && ianger.isAngry())
		return this.staspEntity.getDistanceSq(target) >= 5;
	}

	return false;
    }

    @Override
    public void startExecuting() {
	this.moveToAttackTarget(this.staspEntity.getAttackTarget());
    }

    @Override
    public void tick() {
	LivingEntity target = this.staspEntity.getAttackTarget();

	if (this.staspEntity.getBoundingBox().intersects(target.getBoundingBox()))
	    this.staspEntity.attackEntityAsMob(target);

	else if (this.staspEntity.getDistanceSq(target) <= 10)
	    this.moveToAttackTarget(target);
    }

}