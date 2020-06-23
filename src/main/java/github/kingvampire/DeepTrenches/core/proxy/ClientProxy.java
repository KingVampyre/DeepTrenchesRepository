package github.kingvampire.DeepTrenches.core.proxy;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.ALMOND_LEAVES;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.AQUEAN_LEAVES;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.BLACK_BIRCH_LEAVES;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.EBONY_LEAVES;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.MOSOIL;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PELTOGYNE_LEAVES;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PLUM_LEAVES;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.REEBLOON;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.TEAK_LEAVES;
import static github.kingvampire.DeepTrenches.core.init.ModColorMaps.STORCEAN_FOLIAGE;
import static github.kingvampire.DeepTrenches.core.init.ModColorMaps.STORCEAN_MOSOIL;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeColors;

public class ClientProxy extends CommonProxy {

    @Override
    public void init() {
	ItemColors itemColors = Minecraft.getInstance().getItemColors();
	BlockColors blockColors = Minecraft.getInstance().getBlockColors();

	itemColors.register((stack, tintIndex) -> STORCEAN_MOSOIL.getDefaultColor(), REEBLOON);
	itemColors.register((stack, tintIndex) -> STORCEAN_MOSOIL.getDefaultColor(), MOSOIL);

	itemColors.register((stack, tintIndex) -> 5614908, ALMOND_LEAVES);
	itemColors.register((stack, tintIndex) -> 5614908, BLACK_BIRCH_LEAVES);
	itemColors.register((stack, tintIndex) -> 5614908, EBONY_LEAVES);
	itemColors.register((stack, tintIndex) -> 5614908, PELTOGYNE_LEAVES);
	itemColors.register((stack, tintIndex) -> 5614908, PLUM_LEAVES);
	itemColors.register((stack, tintIndex) -> 5614908, TEAK_LEAVES);
	
	itemColors.register((stack, tintIndex) -> STORCEAN_FOLIAGE.getDefaultColor(), AQUEAN_LEAVES);

	blockColors.register((state, world, pos, tintIndex) -> {

	    if (world != null && pos != null) {
		Biome biome = world.getBiome(pos);

		double temperature = (double) MathHelper.clamp(biome.func_225486_c(pos), 0, 1);
		double humidity = (double) MathHelper.clamp(biome.getDownfall(), 0, 1);

		return STORCEAN_MOSOIL.get(temperature, humidity);
	    }

	    return STORCEAN_MOSOIL.getDefaultColor();

	}, MOSOIL, REEBLOON);

	blockColors.register((state, world, pos, tintIndex) -> {

	    if (world != null && pos != null) {
		Biome biome = world.getBiome(pos);

		double temperature = (double) MathHelper.clamp(biome.func_225486_c(pos), 0, 1);
		double humidity = (double) MathHelper.clamp(biome.getDownfall(), 0, 1);

		return STORCEAN_FOLIAGE.get(temperature, humidity);
	    }

	    return STORCEAN_FOLIAGE.getDefaultColor();

	}, AQUEAN_LEAVES);

	blockColors.register(
		(state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos)
			: FoliageColors.getDefault(),
		ALMOND_LEAVES, BLACK_BIRCH_LEAVES, EBONY_LEAVES, PELTOGYNE_LEAVES, PLUM_LEAVES, TEAK_LEAVES);
    }

}
