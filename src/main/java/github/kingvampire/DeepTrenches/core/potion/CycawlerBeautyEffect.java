package github.kingvampire.DeepTrenches.core.potion;

import static github.kingvampire.DeepTrenches.core.init.ModEffects.CYCAWLER_BEAUTY;
import static net.minecraft.entity.SharedMonsterAttributes.MAX_HEALTH;
import static net.minecraft.entity.SharedMonsterAttributes.MOVEMENT_SPEED;
import static net.minecraft.entity.ai.attributes.AttributeModifier.Operation.ADDITION;
import static net.minecraft.entity.ai.attributes.AttributeModifier.Operation.MULTIPLY_TOTAL;
import static net.minecraft.potion.EffectType.BENEFICIAL;
import static net.minecraft.util.DamageSource.LIGHTNING_BOLT;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

public class CycawlerBeautyEffect extends Effect {

    public CycawlerBeautyEffect() {
	super(BENEFICIAL, 7689983);

	this.addAttributesModifier(MAX_HEALTH, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", 4.0F, ADDITION);
	this.addAttributesModifier(MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.15F, MULTIPLY_TOTAL);
    }

    @EventBusSubscriber
    public static class Subscriber {

	@SubscribeEvent
	public static void onDamage(LivingDamageEvent event) {
	    LivingEntity living = event.getEntityLiving();

	    if (event.getSource() == LIGHTNING_BOLT && living.isPotionActive(CYCAWLER_BEAUTY))
		event.setAmount(event.getAmount() * 0.1F);

	}

    }

}
