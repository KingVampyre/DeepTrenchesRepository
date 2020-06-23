package github.kingvampire.DeepTrenches.core.potion;

import static net.minecraft.potion.EffectType.BENEFICIAL;
import static net.minecraft.potion.Effects.POISON;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.world.World;

public class CosmosBeautyEffect extends Effect {

    public CosmosBeautyEffect() {
	super(BENEFICIAL, 1969163);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
	int ticks = 50 >> amplifier;

	if (ticks > 0)
	    return duration % ticks == 0;
	else
	    return true;

    }

    @Override
    public void performEffect(LivingEntity entityLiving, int amplifier) {
	World world = entityLiving.getEntityWorld();

	if (!world.isRemote()) {
	    entityLiving.removePotionEffect(POISON);

	    if (entityLiving.getHealth() < entityLiving.getMaxHealth())
		entityLiving.heal(1.0F);

	}
    }
}
