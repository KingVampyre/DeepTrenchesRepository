package github.kingvampire.DeepTrenches.core.util.capability;

import static github.kingvampire.DeepTrenches.core.init.ModItems.BARBELED_LOOSEJAW_BUCKET;
import static github.kingvampire.DeepTrenches.core.init.ModItems.BETTA_BUCKET;
import static github.kingvampire.DeepTrenches.core.init.ModItems.BLACK_LOOSEJAW_BUCKET;
import static github.kingvampire.DeepTrenches.core.init.ModItems.DEEP_LAKE_BETTA_BUCKET;
import static github.kingvampire.DeepTrenches.core.init.ModItems.GIANT_HATCHETFISH_BUCKET;
import static github.kingvampire.DeepTrenches.core.init.ModItems.LIGHT_LOOSEJAW_BUCKET;
import static github.kingvampire.DeepTrenches.core.init.ModItems.SMALLTOOTH_DRAGONFISH_BUCKET;
import static github.kingvampire.DeepTrenches.core.init.ModTaxonomyRanks.BARBELED_LOOSEJAW;
import static github.kingvampire.DeepTrenches.core.init.ModTaxonomyRanks.BETTA;
import static github.kingvampire.DeepTrenches.core.init.ModTaxonomyRanks.BLACK_LOOSEJAW;
import static github.kingvampire.DeepTrenches.core.init.ModTaxonomyRanks.DEEP_LAKE_BETTA;
import static github.kingvampire.DeepTrenches.core.init.ModTaxonomyRanks.GIANT_HATCHETFISH;
import static github.kingvampire.DeepTrenches.core.init.ModTaxonomyRanks.LIGHT_LOOSEJAW;
import static github.kingvampire.DeepTrenches.core.init.ModTaxonomyRanks.SMALLTOOTH_DRAGONFISH;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import github.kingvampire.DeepTrenches.api.capabilities.taxonomy.TaxonomyProvider;
import github.kingvampire.DeepTrenches.api.taxonomy.RankInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class AttachTaxonomyToStack {

    @SubscribeEvent
    public static void attachTaxonomyToBucket(AttachCapabilitiesEvent<ItemStack> event) {
	ItemStack stack = event.getObject();
	Item item = stack.getItem();

	ResourceLocation id = new ResourceLocation(MODID, "taxonomy");

	if (item == BETTA_BUCKET)
	    event.addCapability(id, new TaxonomyProvider(new RankInstance(BETTA)));

	else if (item == DEEP_LAKE_BETTA_BUCKET)
	    event.addCapability(id, new TaxonomyProvider(DEEP_LAKE_BETTA.getDefaultInstance()));

	else if (item == BLACK_LOOSEJAW_BUCKET)
	    event.addCapability(id, new TaxonomyProvider(new RankInstance(BLACK_LOOSEJAW)));

	else if (item == GIANT_HATCHETFISH_BUCKET)
	    event.addCapability(id, new TaxonomyProvider(GIANT_HATCHETFISH.getDefaultInstance()));

	else if (item == LIGHT_LOOSEJAW_BUCKET)
	    event.addCapability(id, new TaxonomyProvider(new RankInstance(LIGHT_LOOSEJAW)));

	else if (item == BARBELED_LOOSEJAW_BUCKET)
	    event.addCapability(id, new TaxonomyProvider(new RankInstance(BARBELED_LOOSEJAW)));

	else if (item == SMALLTOOTH_DRAGONFISH_BUCKET)
	    event.addCapability(id, new TaxonomyProvider(SMALLTOOTH_DRAGONFISH.getDefaultInstance()));

    }

}
