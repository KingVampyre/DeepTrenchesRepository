package github.kingvampire.DeepTrenches.core.potion;

import static github.kingvampire.DeepTrenches.core.init.ModEffects.SOFTBONES;
import static net.minecraft.potion.EffectType.NEUTRAL;
import static net.minecraft.util.DamageSource.ANVIL;
import static net.minecraft.util.DamageSource.CRAMMING;
import static net.minecraft.util.DamageSource.FALL;
import static net.minecraft.util.DamageSource.FLY_INTO_WALL;
import static net.minecraft.util.DamageSource.IN_WALL;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

public class SoftbonesEffect extends Effect {

    public SoftbonesEffect() {
	super(NEUTRAL, 10592434);
    }

    @EventBusSubscriber
    public static class Subscriber {

	@SubscribeEvent
	public static void onLivingDamage(LivingDamageEvent event) {
	    LivingEntity living = event.getEntityLiving();

	    if (living.isPotionActive(SOFTBONES)) {
		EffectInstance effectInstance = living.getActivePotionMap().get(SOFTBONES);
		int amplifier = effectInstance.getAmplifier();

		float amount = event.getAmount();
		DamageSource source = event.getSource();

		if (source == ANVIL)
		    event.setAmount(amplifier > 0 ? amount * 1.5F : amount * 1.35F);

		if (source == CRAMMING || source == IN_WALL)
		    event.setAmount(amplifier > 0 ? amount * 1.75F : amount * 1.35F);

		if (source == FALL)
		    event.setAmount(amplifier > 0 ? amount * 1.5F : amount * 1.35F);

		if (source == FLY_INTO_WALL)
		    event.setAmount(amplifier > 0 ? amount * 1.45F : amount * 1.35F);

	    }
	}

    }

}
