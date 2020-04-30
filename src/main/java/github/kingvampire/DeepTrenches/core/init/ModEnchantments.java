package github.kingvampire.DeepTrenches.core.init;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import java.util.Random;

import github.kingvampire.DeepTrenches.core.potion.DrainingEffectInstance;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(MODID)
public class ModEnchantments {

	public static final Enchantment DRAINING = null;

	public static void applyDraining(LivingEntity living, LivingEntity trueSource, ItemStack stack) {

		if (!living.isPotionActive(ModEffects.DRAINING)) {
			int amplifier = getDrainingModifier(stack) - 1;

			living.addPotionEffect(new DrainingEffectInstance(trueSource, 200, amplifier));
		}
	}

	public static void applyDrainingBonus(LivingEntity living, LivingEntity trueSource, int amplifier) {
		Random random = trueSource.getRNG();
		int chance = (amplifier << 5) + 35;

		if (random.nextInt(100) < chance)
			ModEffects.DRAINING.affectEntity(trueSource, null, living, amplifier, trueSource.getHealth());
	}

	public static void applyDrainingBonus(LivingEntity living, LivingEntity trueSource, ItemStack stack) {
		applyDrainingBonus(living, trueSource, getDrainingModifier(stack) * 2);
	}

	public static int getDrainingModifier(ItemStack stack) {
		return EnchantmentHelper.getEnchantmentLevel(DRAINING, stack);
	}

	public static int getDrainingModifier(LivingEntity entityIn) {
		return EnchantmentHelper.getMaxEnchantmentLevel(DRAINING, entityIn);
	}

	public static boolean hasDraining(ItemStack stack) {
		return getDrainingModifier(stack) > 0;
	}

	public static boolean hasDraining(LivingEntity entityIn) {
		return getDrainingModifier(entityIn) > 0;
	}

}
