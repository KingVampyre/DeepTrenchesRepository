package github.kingvampire.DeepTrenches.core.init;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.entity.EntityClassification.CREATURE;
import static net.minecraft.entity.EntityClassification.WATER_CREATURE;
import static net.minecraft.util.registry.Registry.ENTITY_TYPE;

import github.kingvampire.DeepTrenches.api.entity.tileentity.ModBoatEntity;
import github.kingvampire.DeepTrenches.core.entity.AdaiggerEntity;
import github.kingvampire.DeepTrenches.core.entity.BettaEntity;
import github.kingvampire.DeepTrenches.core.entity.BlackLoosejawEntity;
import github.kingvampire.DeepTrenches.core.entity.DeepLakeBettaEntity;
import github.kingvampire.DeepTrenches.core.entity.GiantHatchetfishEntity;
import github.kingvampire.DeepTrenches.core.entity.LightLoosejawEntity;
import github.kingvampire.DeepTrenches.core.entity.BarbeledLoosejawEntity;
import github.kingvampire.DeepTrenches.core.entity.SmalltoothDragonfishEntity;
import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(MODID)
public class ModEntities {
    
    public static final EntityType<AdaiggerEntity> ADAIGGER = null;
    
    @SuppressWarnings("deprecation")
    public static final EntityType<BettaEntity> BETTA = Registry.register(ENTITY_TYPE, MODID + ":betta",
	    EntityType.Builder
	    .<BettaEntity>create(BettaEntity::new, WATER_CREATURE)
	    .setCustomClientFactory(BettaEntity::new)
	    .size(0.55F, 0.5F)
	    .build(MODID + ":betta"));

    @SuppressWarnings("deprecation")
    public static final EntityType<BlackLoosejawEntity> BLACK_LOOSEJAW = Registry.register(ENTITY_TYPE, MODID + ":black_loosejaw",
	    EntityType.Builder
	    .<BlackLoosejawEntity>create(BlackLoosejawEntity::new, WATER_CREATURE)
	    .setCustomClientFactory(BlackLoosejawEntity::new)
	    .size(0.35F, 0.4255F)
	    .build(MODID + ":black_loosejaw"));
    
    public static final EntityType<ModBoatEntity> BOAT = null;
    
    @SuppressWarnings("deprecation")
    public static final EntityType<DeepLakeBettaEntity> DEEP_LAKE_BETTA = Registry.register(ENTITY_TYPE, MODID + ":deep_lake_betta",
	    EntityType.Builder
	    .<DeepLakeBettaEntity>create(DeepLakeBettaEntity::new, WATER_CREATURE)
	    .setCustomClientFactory(DeepLakeBettaEntity::new)
	    .size(0.6F, 0.4F)
	    .build(MODID + ":deep_lake_betta"));
    
    @SuppressWarnings("deprecation")
    public static final EntityType<GiantHatchetfishEntity> GIANT_HATCHETFISH = Registry.register(ENTITY_TYPE, MODID + ":giant_hatchetfish",
	    EntityType.Builder
	    .<GiantHatchetfishEntity>create(GiantHatchetfishEntity::new, WATER_CREATURE)
	    .setCustomClientFactory(GiantHatchetfishEntity::new)
	    .size(0.2F, 0.4F)
	    .build(MODID + ":giant_hatchetfish"));

    @SuppressWarnings("deprecation")
    public static final EntityType<LightLoosejawEntity> LIGHT_LOOSEJAW = Registry.register(ENTITY_TYPE, MODID + ":light_loosejaw",
	    EntityType.Builder
	    .<LightLoosejawEntity>create(LightLoosejawEntity::new, WATER_CREATURE)
	    .setCustomClientFactory(LightLoosejawEntity::new)
	    .size(0.35F, 0.4255F)
	    .build(MODID + ":light_loosejaw"));

    @SuppressWarnings("deprecation")
    public static final EntityType<BarbeledLoosejawEntity> BARBELED_LOOSEJAW = Registry.register(ENTITY_TYPE, MODID + ":barbeled_loosejaw",
	    EntityType.Builder
	    .<BarbeledLoosejawEntity>create(BarbeledLoosejawEntity::new, CREATURE)
	    .setCustomClientFactory(BarbeledLoosejawEntity::new)
	    .size(0.35F, 0.4255F)
	    .build(MODID + ":barbeled_loosejaw"));
    
    @SuppressWarnings("deprecation")
    public static final EntityType<SmalltoothDragonfishEntity> SMALLTOOTH_DRAGONFISH = Registry.register(ENTITY_TYPE, MODID + ":smalltooth_dragonfish",
	    EntityType.Builder
	    .<SmalltoothDragonfishEntity>create(SmalltoothDragonfishEntity::new, CREATURE)
	    .setCustomClientFactory(SmalltoothDragonfishEntity::new)
	    .size(0.35F, 0.4255F)
	    .build(MODID + ":smalltooth_dragonfish"));
    
    @SuppressWarnings("deprecation")
    public static final EntityType<StaspEntity> STASP = Registry.register(ENTITY_TYPE, MODID + ":stasp",
	    EntityType.Builder
	    .<StaspEntity>create(StaspEntity::new, CREATURE)
	    .setCustomClientFactory(StaspEntity::new)
	    .size(0.7F, 0.6F)
	    .build(MODID + ":stasp"));

}
