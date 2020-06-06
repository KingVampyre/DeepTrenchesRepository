package github.kingvampire.DeepTrenches.api.entity.goals;

import static github.kingvampire.DeepTrenches.api.capabilities.tame.TameProvider.TAME_CAPABILITY;

import github.kingvampire.DeepTrenches.api.capabilities.tame.ITame;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.util.LazyOptional;

public class AvoidPlayerGoal extends AvoidEntityGoal<PlayerEntity> {

    public AvoidPlayerGoal(CreatureEntity creature, float distance) {
	super(creature, PlayerEntity.class, distance);
    }

    @Override
    public boolean shouldExecute() {
	LazyOptional<ITame> tame = this.creature.getCapability(TAME_CAPABILITY);

	if (tame.isPresent())
	    return !tame.orElseThrow(IllegalArgumentException::new).isTamed() && super.shouldExecute();

	return super.shouldExecute();
    }

    @Override
    protected EntityPredicate getEntityPredicate() {
	return new EntityPredicate().setDistance(this.distance);
    }

}
