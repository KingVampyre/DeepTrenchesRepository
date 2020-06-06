package github.kingvampire.DeepTrenches.core.entity.goals.hatchetfish;

import github.kingvampire.DeepTrenches.api.entity.DragonfishEntity;
import github.kingvampire.DeepTrenches.api.entity.goals.AvoidEntityGoal;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityPredicate;

public class AvoidLoosejawGoal extends AvoidEntityGoal<DragonfishEntity> {

    public AvoidLoosejawGoal(CreatureEntity creature, float distance) {
	super(creature, DragonfishEntity.class, distance);
    }

    @Override
    protected EntityPredicate getEntityPredicate() {
	return new EntityPredicate().setDistance(this.distance).setCustomPredicate(living -> {

	    if (living instanceof DragonfishEntity) {
		DragonfishEntity dragonfish = (DragonfishEntity) living;

		return !dragonfish.isLuring();
	    }

	    return true;
	});
    }

}
