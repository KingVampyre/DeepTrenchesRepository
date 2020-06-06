package github.kingvampire.DeepTrenches.core.entity.controllers;

import static java.lang.Math.PI;
import static net.minecraft.entity.SharedMonsterAttributes.MOVEMENT_SPEED;
import static net.minecraft.entity.ai.controller.MovementController.Action.MOVE_TO;
import static net.minecraft.tags.FluidTags.WATER;

import github.kingvampire.DeepTrenches.core.entity.DeepLakeBettaEntity;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.MathHelper;

public class DeepLakeBettaMovementController extends MovementController {

    private final DeepLakeBettaEntity deepLakeBetta;

    public DeepLakeBettaMovementController(DeepLakeBettaEntity deepLakeBetta) {
	super(deepLakeBetta);

	this.deepLakeBetta = deepLakeBetta;
    }

    public void tick() {
	PathNavigator navigator = this.deepLakeBetta.getNavigator();

	if (this.deepLakeBetta.areEyesInFluid(WATER))

	    if (this.deepLakeBetta.isLuring())
		this.deepLakeBetta.setMotion(this.deepLakeBetta.getMotion().add(0, 0.0005D, 0));
	    else
		this.deepLakeBetta.setMotion(this.deepLakeBetta.getMotion().add(0, 0.005D, 0));

	if (this.action == MOVE_TO && !navigator.noPath() && !this.deepLakeBetta.isLuring()) {

	    double dx = this.posX - this.deepLakeBetta.posX;
	    double dy = this.posY - this.deepLakeBetta.posY;
	    double dz = this.posZ - this.deepLakeBetta.posZ;

	    float iaSpeed = this.deepLakeBetta.getAIMoveSpeed();

	    double distance = (double) MathHelper.sqrt(dx * dx + dy * dy + dz * dz);
	    double y = (double) iaSpeed * (dy / distance) * 0.1;
	    double radiands = (double) (180F / (float) PI);

	    float angle = (float) (MathHelper.atan2(dz, dx) * radiands) - 90F;

	    this.deepLakeBetta.rotationYaw = this.limitAngle(this.deepLakeBetta.rotationYaw, angle, 90F);
	    this.deepLakeBetta.renderYawOffset = this.deepLakeBetta.rotationYaw;

	    float speed = (float) (this.speed * this.deepLakeBetta.getAttribute(MOVEMENT_SPEED).getValue());

	    this.deepLakeBetta.setAIMoveSpeed(MathHelper.lerp(0.125F, iaSpeed, speed));
	    this.deepLakeBetta.setMotion(this.deepLakeBetta.getMotion().add(0, y, 0));
	} else
	    this.deepLakeBetta.setAIMoveSpeed(0);

    }

}
