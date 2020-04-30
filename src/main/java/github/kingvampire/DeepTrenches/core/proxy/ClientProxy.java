package github.kingvampire.DeepTrenches.core.proxy;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.ALMOND_LEAVES;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.BLACK_BIRCH_LEAVES;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PELTOGYNE_LEAVES;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PLUM_LEAVES;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.TEAK_LEAVES;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.biome.BiomeColors;

public class ClientProxy extends CommonProxy {

	@Override
	public void init() {
		BlockColors colors = Minecraft.getInstance().getBlockColors();

		colors.register(
				(state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos)
						: FoliageColors.getDefault(),
				ALMOND_LEAVES, BLACK_BIRCH_LEAVES, PELTOGYNE_LEAVES, PLUM_LEAVES, TEAK_LEAVES);
	}

}
