package github.kingvampire.DeepTrenches.core.entity.controllers;

import github.kingvampire.DeepTrenches.api.entity.DragonfishEntity;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.util.math.MathHelper;

public class DragonfishLookController extends LookController {

    public DragonfishLookController(DragonfishEntity dragonfish) {
	super(dragonfish);
    }

    @Override
    public void tick() {

	if (this.isLooking) {
	    this.isLooking = false;
	    this.mob.rotationYawHead = this.func_220675_a(this.mob.rotationYawHead, this.func_220678_h() + 20F,
		    this.deltaLookYaw);

	    this.mob.rotationPitch = this.func_220675_a(this.mob.rotationPitch, this.func_220677_g() + 10F,
		    this.deltaLookPitch);
	} else {
	    if (this.mob.getNavigator().noPath())
		this.mob.rotationPitch = this.func_220675_a(this.mob.rotationPitch, 0, 5F);

	    this.mob.rotationYawHead = this.func_220675_a(this.mob.rotationYawHead, this.mob.renderYawOffset,
		    this.deltaLookYaw);
	}

	float f = MathHelper.wrapDegrees(this.mob.rotationYawHead - this.mob.renderYawOffset);

	if (f < 10)
	    this.mob.renderYawOffset -= 4F;
	else if (f > 10)
	    this.mob.renderYawOffset += 4F;

    }

}
