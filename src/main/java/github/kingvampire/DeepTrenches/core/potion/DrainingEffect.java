package github.kingvampire.DeepTrenches.core.potion;

import static github.kingvampire.DeepTrenches.core.init.DamageSources.DRAINING;
import static net.minecraft.potion.EffectType.HARMFUL;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;

public class DrainingEffect extends Effect {

	public DrainingEffect() {
		super(HARMFUL, 4004144);
	}

	@Override
	public void affectEntity(Entity source, Entity indirectSource, LivingEntity entityLivingBaseIn, int amplifier,
			double health) {

		float hearts = 1 << amplifier;
		float amount = 2f + hearts;

		entityLivingBaseIn.attackEntityFrom(DRAINING, amount);

		if (source instanceof LivingEntity) {
			LivingEntity livingEntity = (LivingEntity) source;

			if (livingEntity.getHealth() < livingEntity.getMaxHealth())
				livingEntity.heal(amount);
		}
	}

	@Override
	public boolean isReady(int duration, int amplifier) {
		int period = 120 >> amplifier;

		if (period > 0)
			return duration % period == 0;

		return true;
	}

}
