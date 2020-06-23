package github.kingvampire.DeepTrenches.core.potion;

import static net.minecraft.entity.SharedMonsterAttributes.MOVEMENT_SPEED;
import static net.minecraft.entity.ai.attributes.AttributeModifier.Operation.MULTIPLY_TOTAL;
import static net.minecraft.potion.EffectType.NEUTRAL;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.world.World;

public class FlowerBeautyEffect extends Effect {

    private static final String UUID = "91AEAA56-376B-4498-935B-2F7F68070635";

    public FlowerBeautyEffect() {
	super(NEUTRAL, 12001933);

	this.addAttributesModifier(MOVEMENT_SPEED, UUID, 0.2, MULTIPLY_TOTAL);
    }

    @Override
    public void performEffect(LivingEntity entityLiving, int amplifier) {
	World world = entityLiving.getEntityWorld();

	if (entityLiving instanceof PlayerEntity) {
	    PlayerEntity player = (PlayerEntity) entityLiving;

	    if (!world.isRemote())
		player.getFoodStats().addStats(amplifier + 1, 1);
	}
    }

}
