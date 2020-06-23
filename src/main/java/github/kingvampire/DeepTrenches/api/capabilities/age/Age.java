package github.kingvampire.DeepTrenches.api.capabilities.age;

import net.minecraft.entity.CreatureEntity;

public class Age implements IAge {

    protected CreatureEntity creature;
    protected int forcedAge;
    protected int forcedAgeTimer;
    protected int growingAge;

    public Age() {
	// Default Constructor
    }

    public Age(CreatureEntity creature) {
	this.creature = creature;
    }

    @Override
    public CreatureEntity getCreatureEntity() {
	return this.creature;
    }

    @Override
    public int getForcedAge() {
	return this.forcedAge;
    }

    @Override
    public int getForcedAgeTimer() {
	return this.forcedAgeTimer;
    }

    @Override
    public int getGrowingAge() {
	return this.growingAge;
    }

    @Override
    public void setForcedAge(int forcedAge) {
	this.forcedAge = forcedAge;
    }

    @Override
    public void setForcedAgeTimer(int forcedAgeTimer) {
	this.forcedAgeTimer = forcedAgeTimer;
    }

    @Override
    public void setGrowingAge(int growingAge) {

	if (this.growingAge < 0 && growingAge >= 0 || this.growingAge >= 0 && growingAge < 0)
	    this.onGrowingAdult();

	this.growingAge = growingAge;
    }

}
