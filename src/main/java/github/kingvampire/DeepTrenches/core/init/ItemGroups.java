package github.kingvampire.DeepTrenches.core.init;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PIPE_ORGAN_CORAL_FAN;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroups {

	public static final ItemGroup GENERAL = new ItemGroup(MODID) {

		@Override
		public ItemStack createIcon() {
			return new ItemStack(PIPE_ORGAN_CORAL_FAN);
		}
	};

}
