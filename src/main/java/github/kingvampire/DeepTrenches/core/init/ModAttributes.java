package github.kingvampire.DeepTrenches.core.init;

import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;

public class ModAttributes {

    public static final IAttribute MOVEMENT_SPEED_BOOST = new RangedAttribute(null, "generic.movementSpeedBoost", 0.7F, 0, 1024).setShouldWatch(true);
    public static final IAttribute RANDOM_SWIM_CHANCE = new RangedAttribute(null, "generic.randomSwimChance", 0, 0, 100);

}
