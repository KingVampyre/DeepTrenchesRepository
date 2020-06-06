package github.kingvampire.DeepTrenches.core.util;

import static github.kingvampire.DeepTrenches.core.init.ModEntities.BARBELED_LOOSEJAW;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.BETTA;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.BLACK_LOOSEJAW;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.DEEP_LAKE_BETTA;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.GIANT_HATCHETFISH;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.LIGHT_LOOSEJAW;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.SMALLTOOTH_DRAGONFISH;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.STASP;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import github.kingvampire.DeepTrenches.api.capabilities.age.AgeProvider;
import github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider;
import github.kingvampire.DeepTrenches.api.capabilities.breed.BreedProvider;
import github.kingvampire.DeepTrenches.api.capabilities.group.GroupProvider;
import github.kingvampire.DeepTrenches.api.capabilities.lit.LitProvider;
import github.kingvampire.DeepTrenches.api.capabilities.tame.TameProvider;
import github.kingvampire.DeepTrenches.api.capabilities.taxonomy.TaxonomyProvider;
import github.kingvampire.DeepTrenches.core.init.ModTaxonomyRanks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class AttachCapability {

    @SubscribeEvent
    public static void attachAgeCapability(AttachCapabilitiesEvent<Entity> event) {
	Entity entity = event.getObject();
	EntityType<?> type = entity.getType();

	if (!(entity instanceof CreatureEntity))
	    return;

	CreatureEntity creature = (CreatureEntity) entity;
	ResourceLocation id = new ResourceLocation(MODID, "age");

	if (type == BETTA || type == DEEP_LAKE_BETTA)
	    event.addCapability(id, new AgeProvider(creature));

	else if (type == BLACK_LOOSEJAW || type == LIGHT_LOOSEJAW || type == BARBELED_LOOSEJAW
		|| type == SMALLTOOTH_DRAGONFISH)
	    event.addCapability(id, new AgeProvider(creature));

    }

    @SubscribeEvent
    public static void attachAngerCapability(AttachCapabilitiesEvent<Entity> event) {
	Entity entity = event.getObject();
	EntityType<?> type = entity.getType();

	ResourceLocation id = new ResourceLocation(MODID, "anger");

	if (type == BETTA || type == DEEP_LAKE_BETTA)
	    event.addCapability(id, new AngerProvider(100, 100));

	else if (type == BLACK_LOOSEJAW || type == LIGHT_LOOSEJAW || type == BARBELED_LOOSEJAW
		|| type == SMALLTOOTH_DRAGONFISH)
	    event.addCapability(id, new AngerProvider(200, 200));

	else if (type == STASP)
	    event.addCapability(id, new AngerProvider(200, 100));

    }

    @SubscribeEvent
    public static void attachBreedCapability(AttachCapabilitiesEvent<Entity> event) {
	Entity entity = event.getObject();
	EntityType<?> type = entity.getType();

	if (!(entity instanceof CreatureEntity))
	    return;

	CreatureEntity creature = (CreatureEntity) entity;
	ResourceLocation id = new ResourceLocation(MODID, "breed");

	if (type == BETTA || type == DEEP_LAKE_BETTA)
	    event.addCapability(id, new BreedProvider(creature, Items.COD));

	else if (type == BLACK_LOOSEJAW || type == LIGHT_LOOSEJAW || type == BARBELED_LOOSEJAW
		|| type == SMALLTOOTH_DRAGONFISH)
	    event.addCapability(id, new BreedProvider(creature, Items.COD));

    }

    @SubscribeEvent
    public static void attachGroupCapability(AttachCapabilitiesEvent<Entity> event) {
	Entity entity = event.getObject();
	EntityType<?> type = entity.getType();

	if (!(entity instanceof CreatureEntity))
	    return;

	CreatureEntity creature = (CreatureEntity) entity;
	ResourceLocation id = new ResourceLocation(MODID, "group");

	if (type == BETTA || type == DEEP_LAKE_BETTA)
	    event.addCapability(id, new GroupProvider(creature, 4));

	else if (type == BLACK_LOOSEJAW)
	    event.addCapability(id, new GroupProvider(creature, 5));

	else if (type == GIANT_HATCHETFISH)
	    event.addCapability(id, new GroupProvider(creature, 15));

	else if (type == LIGHT_LOOSEJAW)
	    event.addCapability(id, new GroupProvider(creature, 3));

	else if (type == BARBELED_LOOSEJAW)
	    event.addCapability(id, new GroupProvider(creature, 7));

	else if (type == SMALLTOOTH_DRAGONFISH)
	    event.addCapability(id, new GroupProvider(creature, 12));

    }

    @SubscribeEvent
    public static void attachLitCapability(AttachCapabilitiesEvent<Entity> event) {
	Entity entity = event.getObject();
	EntityType<?> type = entity.getType();

	ResourceLocation id = new ResourceLocation(MODID, "lit");

	if (type == BETTA || type == DEEP_LAKE_BETTA)
	    event.addCapability(id, new LitProvider());

	else if (type == BLACK_LOOSEJAW || type == LIGHT_LOOSEJAW || type == BARBELED_LOOSEJAW
		|| type == SMALLTOOTH_DRAGONFISH)
	    event.addCapability(id, new LitProvider());

	else if (type == GIANT_HATCHETFISH)
	    event.addCapability(id, new LitProvider());

    }

    @SubscribeEvent
    public static void attachTameCapability(AttachCapabilitiesEvent<Entity> event) {
	Entity entity = event.getObject();
	EntityType<?> type = entity.getType();

	if (!(entity instanceof CreatureEntity))
	    return;

	CreatureEntity creature = (CreatureEntity) entity;
	ResourceLocation id = new ResourceLocation(MODID, "tame");

	if (type == BETTA || type == DEEP_LAKE_BETTA)
	    event.addCapability(id, new TameProvider(creature, 3, Items.COD));

	else if (type == BLACK_LOOSEJAW || type == LIGHT_LOOSEJAW || type == BARBELED_LOOSEJAW
		|| type == SMALLTOOTH_DRAGONFISH)
	    event.addCapability(id, new TameProvider(creature, 2, Items.COD));

    }

    @SubscribeEvent
    public static void attachTaxonomyCapability(AttachCapabilitiesEvent<Entity> event) {
	Entity entity = event.getObject();
	EntityType<?> type = entity.getType();

	ResourceLocation id = new ResourceLocation(MODID, "taxonomy");

	if (type == BETTA)
	    event.addCapability(id, new TaxonomyProvider(ModTaxonomyRanks.BETTA.getDefaultInstance()));

	else if (type == DEEP_LAKE_BETTA)
	    event.addCapability(id, new TaxonomyProvider(ModTaxonomyRanks.DEEP_LAKE_BETTA.getDefaultInstance()));

	else if (type == BLACK_LOOSEJAW)
	    event.addCapability(id, new TaxonomyProvider(ModTaxonomyRanks.BLACK_LOOSEJAW.getDefaultInstance()));

	else if (type == GIANT_HATCHETFISH)
	    event.addCapability(id, new TaxonomyProvider(ModTaxonomyRanks.GIANT_HATCHETFISH.getDefaultInstance()));

	else if (type == LIGHT_LOOSEJAW)
	    event.addCapability(id, new TaxonomyProvider(ModTaxonomyRanks.LIGHT_LOOSEJAW.getDefaultInstance()));

	else if (type == BARBELED_LOOSEJAW)
	    event.addCapability(id, new TaxonomyProvider(ModTaxonomyRanks.BARBELED_LOOSEJAW.getDefaultInstance()));

	else if (type == SMALLTOOTH_DRAGONFISH)
	    event.addCapability(id, new TaxonomyProvider(ModTaxonomyRanks.SMALLTOOTH_DRAGONFISH.getDefaultInstance()));

    }

}
