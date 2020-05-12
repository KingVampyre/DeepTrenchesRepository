package github.kingvampire.DeepTrenches.core.entity.controllers;

import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.controller.LookController;

public class StaspLookController extends LookController {

    public StaspLookController(MobEntity mob) {
	super(mob);
    }

    @Override
    public void tick() {
	this.mob.getCapability(ANGER_CAPABILITY).ifPresent(ianger -> {

	    if (!ianger.isAngry())
		super.tick();
	});
    }

}
