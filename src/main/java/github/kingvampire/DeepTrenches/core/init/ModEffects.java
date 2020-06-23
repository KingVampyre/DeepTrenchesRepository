package github.kingvampire.DeepTrenches.core.init;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.util.registry.Registry.EFFECTS;

import github.kingvampire.DeepTrenches.core.potion.BraitorBeautyEffect;
import github.kingvampire.DeepTrenches.core.potion.CosmosBeautyEffect;
import github.kingvampire.DeepTrenches.core.potion.CycawlerBeautyEffect;
import github.kingvampire.DeepTrenches.core.potion.FlowerBeautyEffect;
import net.minecraft.potion.Effect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(MODID)
public class ModEffects {

    @SuppressWarnings("deprecation")
    public static final Effect BRAITOR_BEAUTY = Registry.register(EFFECTS, new ResourceLocation(MODID, "braitor_beauty"), new BraitorBeautyEffect());
    @SuppressWarnings("deprecation")
    public static final Effect COSMOS_BEAUTY = Registry.register(EFFECTS, new ResourceLocation(MODID, "cosmos_beauty"), new CosmosBeautyEffect()); 
    @SuppressWarnings("deprecation")
    public static final Effect CYCAWLER_BEAUTY = Registry.register(EFFECTS, new ResourceLocation(MODID, "cycawler_beauty"), new CycawlerBeautyEffect());
    public static final Effect DRAINING = null;
    @SuppressWarnings("deprecation")
    public static final Effect FLOWER_BEAUTY = Registry.register(EFFECTS, new ResourceLocation(MODID, "flower_beauty"), new FlowerBeautyEffect());
    public static final Effect SLEEPY = null;
    public static final Effect SOFTBONES = null;

}
