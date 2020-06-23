package github.kingvampire.DeepTrenches.core.util.registry;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

import github.kingvampire.DeepTrenches.core.potion.DrainingEffect;
import github.kingvampire.DeepTrenches.core.potion.SleepyEffect;
import github.kingvampire.DeepTrenches.core.potion.SoftbonesEffect;
import net.minecraft.potion.Effect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(bus = MOD)
public class EffectRegistry {

    @SubscribeEvent
    public static void registerEffects(Register<Effect> event) {
	IForgeRegistry<Effect> registry = event.getRegistry();

	registry.register(new DrainingEffect().setRegistryName(new ResourceLocation(MODID, "draining")));
	registry.register(new SleepyEffect().setRegistryName(new ResourceLocation(MODID, "sleepy")));
	registry.register(new SoftbonesEffect().setRegistryName(new ResourceLocation(MODID, "softbones")));
    }

}
