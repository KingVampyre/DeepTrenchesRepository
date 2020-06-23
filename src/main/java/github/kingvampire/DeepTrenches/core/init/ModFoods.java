package github.kingvampire.DeepTrenches.core.init;

import static net.minecraft.potion.Effects.HUNGER;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;

public class ModFoods {

    public static final Food COOKED_GIANT_HATCHETFISH = new Food.Builder().hunger(6).saturation(0.6F).build();

    public static final Food GIANT_HATCHETFISH = new Food.Builder().hunger(4).saturation(0).build();

    public static final Food STORCEAN_FISH = new Food.Builder()
	    .hunger(2)
	    .saturation(0)
	    .effect(new EffectInstance(HUNGER, 600), 0.35F)
	    .build();

}
