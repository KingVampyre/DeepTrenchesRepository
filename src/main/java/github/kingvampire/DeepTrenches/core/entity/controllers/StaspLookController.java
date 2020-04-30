package github.kingvampire.DeepTrenches.core.entity.controllers;

import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.controller.LookController;

public class StaspLookController extends LookController {

	public StaspLookController(MobEntity mob) {
		super(mob);
	}

	@Override
	public void tick() {
		StaspEntity staspEntity = (StaspEntity) this.mob;

		if (!staspEntity.isAngry())
			super.tick();
	}

}
