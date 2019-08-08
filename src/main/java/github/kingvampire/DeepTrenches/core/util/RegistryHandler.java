package github.kingvampire.DeepTrenches.core.util;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class RegistryHandler {

	@SubscribeEvent
	public static void registerBlocks(Register<Block> event) {

	}

	@SubscribeEvent
	public static void registerEntities(Register<EntityType<?>> event) {

	}

	@SubscribeEvent
	public static void registerItems(Register<Item> event) {

	}

}
