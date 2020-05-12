package github.kingvampire.DeepTrenches.api.capabilities.anger;

import net.minecraft.entity.LivingEntity;

public class Anger implements IAnger {

    private int anger;

    private final int bonusTicks;
    private final int minTicks;

    public Anger() {
	this.bonusTicks = 200;
	this.minTicks = 100;
    }

    public Anger(int minTicks, int bonusTicks) {
	this.minTicks = minTicks;
	this.bonusTicks = bonusTicks;
    }

    @Override
    public int getAnger() {
	return this.anger;
    }

    @Override
    public boolean isAngry() {
	return this.anger > 0;
    }

    @Override
    public void livingTick() {

	if (this.isAngry()) {
	    int anger = this.getAnger();

	    this.setAnger(--anger);
	}
    }

    @Override
    public void setAnger(int anger) {
	this.anger = anger;
    }

    @Override
    public void setAnger(LivingEntity target) {
	this.setAnger(this.minTicks + target.getRNG().nextInt(this.bonusTicks));
    }

}
