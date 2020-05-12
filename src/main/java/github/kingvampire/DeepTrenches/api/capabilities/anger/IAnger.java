package github.kingvampire.DeepTrenches.api.capabilities.anger;

import net.minecraft.entity.LivingEntity;

public interface IAnger {

    int getAnger();

    boolean isAngry();

    void livingTick();

    void setAnger(int anger);

    void setAnger(LivingEntity target);

}
