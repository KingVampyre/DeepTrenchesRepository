package github.kingvampire.DeepTrenches.core.init;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import github.kingvampire.DeepTrenches.api.enums.WoodType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootParameter;

public class ModLootParameters {

    public static final LootParameter<WoodType> WOOD_TYPE = new LootParameter<>(new ResourceLocation(MODID, "wood_type"));

}
