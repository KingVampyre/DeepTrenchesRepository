package github.kingvampire.DeepTrenches.core.init;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.util.registry.Registry.EFFECTS;

import github.kingvampire.DeepTrenches.core.potion.FlowerBeautyEffect;
import net.minecraft.potion.Effect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(MODID)
public class ModEffects {

    public static final Effect DRAINING = null;

    @SuppressWarnings("deprecation")
    public static final Effect FLOWER_BEAUTY = Registry.register(EFFECTS, new ResourceLocation(MODID, "flower_beauty"),
	    new FlowerBeautyEffect());

    public static final Effect SLEEPY = null;

}
