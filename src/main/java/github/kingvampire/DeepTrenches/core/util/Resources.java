package github.kingvampire.DeepTrenches.core.util;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class Resources {

	public static Block attach(String path, Block block) {
		return block.setRegistryName(of(path));
	}

	public static Block attach(String path, Block.Properties props) {
		return new Block(props).setRegistryName(of(path));
	}

	public static Item attach(String path, Item item) {
		return item.setRegistryName(of(path));
	}

	public static Item attach(String path, Item.Properties props) {
		return new Item(props).setRegistryName(of(path));
	}

	public static ResourceLocation of(String path) {
		return new ResourceLocation(MODID, path);
	}

}
