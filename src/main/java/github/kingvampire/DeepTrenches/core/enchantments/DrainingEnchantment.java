package github.kingvampire.DeepTrenches.core.enchantments;

import static github.kingvampire.DeepTrenches.core.init.EnchantmentTypes.RANGED;
import static net.minecraft.enchantment.Enchantment.Rarity.RARE;
import static net.minecraft.enchantment.Enchantments.MULTISHOT;
import static net.minecraft.enchantment.Enchantments.PIERCING;
import static net.minecraft.inventory.EquipmentSlotType.MAINHAND;

import github.kingvampire.DeepTrenches.core.entity.AdaiggerEntity;
import github.kingvampire.DeepTrenches.core.init.DamageSources;
import github.kingvampire.DeepTrenches.core.init.ModEffects;
import github.kingvampire.DeepTrenches.core.init.ModEnchantments;
import github.kingvampire.DeepTrenches.core.potion.DrainingEffectInstance;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

public class DrainingEnchantment extends Enchantment {

	@EventBusSubscriber
	public static class Subscriber {

		@SubscribeEvent
		public static void onAdaiggerHit(LivingDamageEvent event) {
			DamageSource source = event.getSource();
			LivingEntity livingEntity = event.getEntityLiving();
			Entity inmediateSource = source.getImmediateSource();

			if (inmediateSource instanceof AdaiggerEntity && source.getDamageType() == "trident") {
				DamageSource adaigger = DamageSources.causeAdaiggerDamage(inmediateSource, livingEntity);

				AdaiggerEntity adaiggerEntity = (AdaiggerEntity) inmediateSource;
				ItemStack stack = AdaiggerEntity.getItemStack(adaiggerEntity);
				LivingEntity trueSource = (LivingEntity) source.getTrueSource();

				float modifier = EnchantmentHelper.getModifierForCreature(stack, trueSource.getCreatureAttribute());

				if (livingEntity.attackEntityFrom(adaigger, modifier + 5))
					event.setCanceled(true);
			}
		}

		@SubscribeEvent
		public static void onAdaiggerOrTridentHit(LivingAttackEvent event) {
			DamageSource source = event.getSource();
			String damageType = source.getDamageType();

			if (damageType == "adaigger" || damageType == "trident") {
				Entity inmediateSource = source.getImmediateSource();
				Entity entity = source.getTrueSource();

				if (inmediateSource != null || inmediateSource instanceof AbstractArrowEntity) {
					AbstractArrowEntity arrow = (AbstractArrowEntity) inmediateSource;

					if (entity != null && entity instanceof LivingEntity) {
						LivingEntity trueSource = (LivingEntity) entity;

						ItemStack stack = AdaiggerEntity.getItemStack(arrow);
						LivingEntity livingEntity = event.getEntityLiving();

						if (ModEnchantments.hasDraining(stack) && trueSource != livingEntity)
							ModEnchantments.applyDraining(livingEntity, trueSource, stack);
					}
				}
			}
		}

		@SubscribeEvent
		public static void onArrowHit(LivingAttackEvent event) {
			DamageSource source = event.getSource();
			String damageType = source.getDamageType();

			if (damageType == "arrow") {
				Entity entity = source.getTrueSource();

				if (entity != null && entity instanceof LivingEntity) {
					LivingEntity trueSource = (LivingEntity) entity;

					Hand hand = trueSource.getActiveHand();
					ItemStack stack = trueSource.getHeldItem(hand);
					LivingEntity livingEntity = event.getEntityLiving();

					if (ModEnchantments.hasDraining(stack) && trueSource != livingEntity)
						ModEnchantments.applyDraining(event.getEntityLiving(), trueSource, stack);
				}
			}
		}

		@SubscribeEvent
		public static void onKill(LivingDeathEvent event) {
			LivingEntity livingEntity = event.getEntityLiving();
			DamageSource source = event.getSource();

			if (livingEntity.isPotionActive(ModEffects.DRAINING)) {
				DrainingEffectInstance effectInstance = (DrainingEffectInstance) livingEntity
						.getActivePotionEffect(ModEffects.DRAINING);

				LivingEntity trueSource = effectInstance.getLivingEntity();
				int amplifier = effectInstance.getAmplifier() * 2;

				if (trueSource == source.getTrueSource() && trueSource != livingEntity)
					ModEnchantments.applyDrainingBonus(livingEntity, trueSource, amplifier);
			}
		}

	}

	public DrainingEnchantment() {
		super(RARE, RANGED, new EquipmentSlotType[] { MAINHAND });
	}

	@Override
	protected boolean canApplyTogether(Enchantment ench) {
		return ench != MULTISHOT && ench != PIERCING;
	}

	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return 50;
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 25;
	}
}
