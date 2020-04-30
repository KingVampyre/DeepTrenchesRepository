package github.kingvampire.DeepTrenches.core.entity.controllers;

import static java.lang.Math.PI;
import static net.minecraft.entity.SharedMonsterAttributes.FLYING_SPEED;
import static net.minecraft.entity.ai.controller.MovementController.Action.MOVE_TO;
import static net.minecraft.entity.ai.controller.MovementController.Action.WAIT;

import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class StaspMovementController extends FlyingMovementController {

	public StaspMovementController(MobEntity mob) {
		super(mob);
	}

	@Override
	public void tick() {

		if (this.action == MOVE_TO) {
			this.action = WAIT;

			this.mob.setNoGravity(true);

			double dx = this.posX - this.mob.posX;
			double dy = this.posY - this.mob.posY + 0.25;
			double dz = this.posZ - this.mob.posZ;

			double area = dx * dx + dy * dy + dz * dz;

			if (area < (double) 2.5000003E-7F) {
				this.mob.setMoveVertical(0.0F);
				this.mob.setMoveForward(0.0F);

				return;
			}

			double flyingSpeed = this.mob.getAttribute(FLYING_SPEED).getValue();
			float movementSpeed = (float) (this.speed * flyingSpeed);

			Vec3d vec3d = new Vec3d(dx, dy, dz);
			Vec3d scale = vec3d.scale(movementSpeed / vec3d.length());

			this.mob.setMotion(this.mob.getMotion().add(scale));

			StaspEntity staspEntity = (StaspEntity) this.mob;
			LivingEntity target = this.mob.getAttackTarget();

			if (!staspEntity.isAngry()) {
				BlockPos pos = staspEntity.getNavigator().getTargetPos();

				double adx = pos.getX() - this.mob.posX;
				double adz = pos.getZ() - this.mob.posZ;

				this.mob.rotationYaw = -((float) MathHelper.atan2(adx, adz)) * (180F / (float) PI);

			} else if (target != null) {
				double adx = target.posX - this.mob.posX;
				double adz = target.posZ - this.mob.posZ;

				this.mob.rotationYaw = -((float) MathHelper.atan2(adx, adz)) * (180F / (float) PI);
			}

			this.mob.renderYawOffset = this.mob.rotationYaw;
			this.mob.setAIMoveSpeed(movementSpeed);

			double sqrt = (double) MathHelper.sqrt(dx * dx + dz * dz);
			float angle = (float) (-(MathHelper.atan2(dy, sqrt) * (double) (180F / (float) PI)));

			this.mob.rotationPitch = this.limitAngle(this.mob.rotationPitch, angle, 10F);
			this.mob.setMoveVertical(dy > 0 ? movementSpeed : -movementSpeed);

		} else {
			this.mob.setNoGravity(false);
			this.mob.setMoveVertical(0);
			this.mob.setMoveForward(0);
		}

	}

}
