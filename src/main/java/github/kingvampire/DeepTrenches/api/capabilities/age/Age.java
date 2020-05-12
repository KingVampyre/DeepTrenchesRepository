package github.kingvampire.DeepTrenches.api.capabilities.age;

import java.util.function.Function;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class Age implements IAge {

    protected final Function<CreatureEntity, CreatureEntity> createChild;
    protected final CreatureEntity creature;
    protected final DataParameter<Boolean> parameter;

    protected int forcedAge;
    protected int forcedAgeTimer;
    protected int growingAge;

    public Age() {
	this.creature = null;
	this.createChild = null;
	this.parameter = null;
    }

    public Age(CreatureEntity creature, Function<CreatureEntity, CreatureEntity> createChild,
	    DataParameter<Boolean> parameter) {

	this.creature = creature;
	this.createChild = createChild;
	this.parameter = parameter;
    }

    @Override
    public CreatureEntity createChild(CreatureEntity creature) {
	return this.createChild.apply(creature);
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
	CreatureEntity creature = this.getCreatureEntity();
	EntityDataManager dataManager = creature.getDataManager();
	World world = creature.getEntityWorld();

	if (world.isRemote())
	    return dataManager.get(this.parameter) ? -1 : 1;
	else
	    return this.growingAge;
    }

    @Override
    public void notifyDataManagerChange(DataParameter<?> key) {

	if (this.parameter.equals(key))
	    this.creature.recalculateSize();
    }

    @Override
    public void onChildSpawnFromEgg(PlayerEntity playerIn, CreatureEntity child) {

    }

    @Override
    public void onGrowingAdult() {

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
	CreatureEntity creature = this.getCreatureEntity();
	EntityDataManager dataManager = creature.getDataManager();

	if (this.growingAge < 0 && growingAge >= 0 || this.growingAge >= 0 && growingAge < 0) {
	    dataManager.set(this.parameter, growingAge < 0);

	    this.onGrowingAdult();
	}

	this.growingAge = growingAge;
    }

}
