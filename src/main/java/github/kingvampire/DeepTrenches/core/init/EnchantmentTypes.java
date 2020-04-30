package github.kingvampire.DeepTrenches.core.init;

import github.kingvampire.DeepTrenches.core.items.AdaiggerItem;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.TridentItem;

public class EnchantmentTypes {

	public static final EnchantmentType RANGED = EnchantmentType.create("ranged", item -> {
		return item instanceof AdaiggerItem || item instanceof TridentItem || item instanceof BowItem
				|| item instanceof CrossbowItem;
	});

}
