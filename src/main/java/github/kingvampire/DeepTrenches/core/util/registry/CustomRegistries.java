package github.kingvampire.DeepTrenches.core.util.registry;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

import github.kingvampire.DeepTrenches.api.taxonomy.Taxon;
import github.kingvampire.DeepTrenches.api.taxonomy.Rank;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryBuilder;

@EventBusSubscriber(bus = MOD)
public class CustomRegistries {

    @SubscribeEvent
    public static void register(RegistryEvent.NewRegistry event) {

	RegistryBuilder<Rank> taxonomyRank = new RegistryBuilder<Rank>();

	taxonomyRank.setName(new ResourceLocation(MODID, "taxonomy_rank"));
	taxonomyRank.setType(Rank.class);
	taxonomyRank.create();

	RegistryBuilder<Taxon> taxon = new RegistryBuilder<Taxon>();

	taxon.setName(new ResourceLocation(MODID, "taxon"));
	taxon.setType(Taxon.class);
	taxon.create();

    }

}
