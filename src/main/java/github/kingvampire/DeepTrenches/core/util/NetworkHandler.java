package github.kingvampire.DeepTrenches.core.util;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

@EventBusSubscriber
public class NetworkHandler {

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, "main"), () -> "1", "1"::equals, "1"::equals);

}
