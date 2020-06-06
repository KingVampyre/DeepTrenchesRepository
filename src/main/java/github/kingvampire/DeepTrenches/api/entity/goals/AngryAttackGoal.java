package github.kingvampire.DeepTrenches.api.entity.goals;

import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.entity.HatchetfishEntity.MOVEMENT_SPEED_BOOST;

import github.kingvampire.DeepTrenches.api.capabilities.anger.IAnger;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraftforge.common.util.LazyOptional;

public class AngryAttackGoal extends MeleeAttackGoal {

    public AngryAttackGoal(CreatureEntity creature) {
	this(creature, true);
    }

    public AngryAttackGoal(CreatureEntity creature, boolean useLongMemory) {
	super(creature, creature.getAttribute(MOVEMENT_SPEED_BOOST).getBaseValue(), useLongMemory);
    }

    @Override
    public boolean shouldContinueExecuting() {
	LazyOptional<IAnger> anger = this.attacker.getCapability(ANGER_CAPABILITY);

	if (anger.isPresent())
	    return anger.orElseThrow(IllegalArgumentException::new).isAngry() && super.shouldContinueExecuting();

	return false;
    }

    @Override
    public boolean shouldExecute() {
	LazyOptional<IAnger> anger = this.attacker.getCapability(ANGER_CAPABILITY);

	if (anger.isPresent())
	    return anger.orElseThrow(IllegalArgumentException::new).isAngry() && super.shouldExecute();

	return false;
    }

}
