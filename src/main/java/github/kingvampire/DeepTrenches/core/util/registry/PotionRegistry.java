package github.kingvampire.DeepTrenches.core.util.registry;

import static github.kingvampire.DeepTrenches.core.init.ModEffects.SOFTBONES;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(bus = MOD)
public class PotionRegistry {

    @SubscribeEvent
    public static void register(Register<Potion> event) {
	Map<String, EffectInstance> effects = Maps.newHashMap();

	effects.put("long_softbones", new EffectInstance(SOFTBONES, 14400));
	effects.put("long_strong_softbones", new EffectInstance(SOFTBONES, 9600, 1));
	effects.put("softbones", new EffectInstance(SOFTBONES, 3600));
	effects.put("strong_softbones", new EffectInstance(SOFTBONES, 2400, 1));

	for (Entry<String, EffectInstance> entry : effects.entrySet()) {
	    String path = entry.getKey();
	    Potion potion = new Potion(entry.getValue());

	    potion.setRegistryName(new ResourceLocation(MODID, path));

	    event.getRegistry().register(potion);
	}
    }

}
