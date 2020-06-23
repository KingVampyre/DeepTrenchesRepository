package github.kingvampire.DeepTrenches.core.util.registry;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import github.kingvampire.DeepTrenches.core.enchantments.DrainingEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(bus = MOD)
public class EnchantmentRegistry {

    @SubscribeEvent
    public static void registerEnchantments(Register<Enchantment> event) {
	Map<String, Enchantment> enchantments = Maps.newHashMap();

	enchantments.put("draining", new DrainingEnchantment());

	for (Entry<String, Enchantment> entry : enchantments.entrySet()) {
	    Enchantment effect = entry.getValue();
	    String path = entry.getKey();

	    effect.setRegistryName(new ResourceLocation(MODID, path));

	    event.getRegistry().register(effect);
	}
    }

}
