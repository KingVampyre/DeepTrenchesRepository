package github.kingvampire.DeepTrenches.core.entity.controllers;

import static java.lang.Math.PI;
import static net.minecraft.entity.SharedMonsterAttributes.MOVEMENT_SPEED;
import static net.minecraft.entity.ai.controller.MovementController.Action.MOVE_TO;

import github.kingvampire.DeepTrenches.api.entity.DragonfishEntity;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.util.math.MathHelper;

public class DragonfishMovementController extends MovementController {

    public DragonfishMovementController(DragonfishEntity dragonfish) {
	super(dragonfish);
    }

    public void tick() {
	DragonfishEntity dragonfish = (DragonfishEntity) this.mob;

	if (dragonfish.isInWater())
	    dragonfish.setMotion(dragonfish.getMotion().add(0, 0.005D, 0));

	if (this.action == MOVE_TO && !dragonfish.isLuring() && !dragonfish.isBlinking() && !dragonfish.isRespondingBlink()) {

	    double dx = this.posX - dragonfish.posX;
	    double dy = this.posY - dragonfish.posY;
	    double dz = this.posZ - dragonfish.posZ;
	    double distance = dx * dx + dy * dy + dz * dz;

	    if (distance < (double) 2.5000003E-7F)
		this.mob.setMoveForward(0);
	    else {
		float angle = (float) (MathHelper.atan2(dz, dx) * (double) (180F / (float) PI)) - 90F;
		float speed = (float) (this.speed * dragonfish.getAttribute(MOVEMENT_SPEED).getValue());

		dragonfish.rotationYaw = this.limitAngle(dragonfish.rotationYaw, angle, 10F);
		dragonfish.renderYawOffset = dragonfish.rotationYaw;
		dragonfish.rotationYawHead = dragonfish.rotationYaw;

		if (dragonfish.isInWater()) {
		    dragonfish.setAIMoveSpeed(speed * 0.02F);

		    double sqrt = (double) MathHelper.sqrt(dx * dx + dz * dz);
		    double radiands = (double) (180F / (float) PI);
		    double atan = MathHelper.atan2(dy, sqrt);

		    float deg = MathHelper.wrapDegrees(-(float) (atan * radiands));
		    float clamp = MathHelper.clamp(MathHelper.wrapDegrees(deg), -85F, 85F);

		    dragonfish.rotationPitch = this.limitAngle(dragonfish.rotationPitch, clamp, 5F);

		    float f3 = MathHelper.cos(dragonfish.rotationPitch * ((float) PI / 180F));
		    float f4 = MathHelper.sin(dragonfish.rotationPitch * ((float) PI / 180F));

		    dragonfish.moveForward = f3 * speed;
		    dragonfish.moveVertical = -f4 * speed;

		} else
		    dragonfish.setAIMoveSpeed(speed * 0.1F);
	    }
	} else {
	    dragonfish.setAIMoveSpeed(0);
	    dragonfish.setMoveStrafing(0);
	    dragonfish.setMoveVertical(0);
	    dragonfish.setMoveForward(0);
	}
    }

}
