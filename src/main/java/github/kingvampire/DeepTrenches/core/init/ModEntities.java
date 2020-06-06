package github.kingvampire.DeepTrenches.core.init;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.entity.EntityClassification.CREATURE;
import static net.minecraft.entity.EntityClassification.WATER_CREATURE;
import static net.minecraft.util.registry.Registry.ENTITY_TYPE;

import github.kingvampire.DeepTrenches.api.entity.ModBoatEntity;
import github.kingvampire.DeepTrenches.core.entity.AdaiggerEntity;
import github.kingvampire.DeepTrenches.core.entity.BettaEntity;
import github.kingvampire.DeepTrenches.core.entity.BoatEntityDT;
import github.kingvampire.DeepTrenches.core.entity.DeepLakeBettaEntity;
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

    public static final EntityType<ModBoatEntity> BOAT = null;
    
    @SuppressWarnings("deprecation")
    public static final EntityType<DeepLakeBettaEntity> DEEP_LAKE_BETTA = Registry.register(ENTITY_TYPE,
	    MODID + ":deep_lake_betta", EntityType.Builder
	    .<DeepLakeBettaEntity>create(DeepLakeBettaEntity::new, WATER_CREATURE)
	    .setCustomClientFactory(DeepLakeBettaEntity::new)
	    .size(0.6F, 0.4F)
	    .build(MODID + ":deep_lake_betta"));

    @SuppressWarnings("deprecation")
    public static final EntityType<StaspEntity> STASP = Registry.register(ENTITY_TYPE, MODID + ":stasp",
	    EntityType.Builder
	    .<StaspEntity>create(StaspEntity::new, CREATURE)
	    .setCustomClientFactory(StaspEntity::new)
	    .size(0.7F, 0.6F)
	    .build(MODID + ":stasp"));

}
