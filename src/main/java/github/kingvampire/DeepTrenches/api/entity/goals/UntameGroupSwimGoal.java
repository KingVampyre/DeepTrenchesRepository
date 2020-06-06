package github.kingvampire.DeepTrenches.api.entity.goals;

import static github.kingvampire.DeepTrenches.api.capabilities.tame.TameProvider.TAME_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.entity.HatchetfishEntity.RANDOM_SWIM_CHANCE;

import github.kingvampire.DeepTrenches.api.capabilities.tame.ITame;
import net.minecraft.entity.CreatureEntity;

public class UntameGroupSwimGoal extends RandomGroupSwimGoal {

    protected final ITame itame;

    public UntameGroupSwimGoal(CreatureEntity creature) {
	this(creature, (int) creature.getAttribute(RANDOM_SWIM_CHANCE).getBaseValue());
    }

    public UntameGroupSwimGoal(CreatureEntity creature, int chance) {
	super(creature, chance);

	this.itame = creature.getCapability(TAME_CAPABILITY).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public boolean shouldExecute() {

	if (this.itame.isTamed() && this.itame.isSitting())
	    return !this.creature.canPickUpLoot() && super.shouldExecute();

	else if (!this.itame.isTamed())
	    return super.shouldExecute();

	return false;
    }

}
