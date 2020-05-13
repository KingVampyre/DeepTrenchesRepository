package github.kingvampire.DeepTrenches.core.entity.goals.betta;

import static github.kingvampire.DeepTrenches.api.capabilities.tame.TameProvider.TAME_CAPABILITY;

import github.kingvampire.DeepTrenches.api.capabilities.tame.ITame;
import github.kingvampire.DeepTrenches.core.entity.BettaEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.util.LazyOptional;

public class BettaAvoidPlayerGoal extends AvoidEntityGoal<PlayerEntity> {

    public BettaAvoidPlayerGoal(BettaEntity bettaEntity, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {
	super(bettaEntity, PlayerEntity.class, avoidDistanceIn, farSpeedIn, nearSpeedIn);
    }

    @Override
    public boolean shouldExecute() {
	LazyOptional<ITame> tame = this.entity.getCapability(TAME_CAPABILITY);

	if (tame.isPresent() && !tame.orElse(null).isTamed()) {
	    double x = this.entity.posX;
	    double y = this.entity.posY;
	    double z = this.entity.posZ;

	    this.field_75376_d = this.entity.world.getClosestPlayer(this.entity, this.avoidDistance);

	    if (this.field_75376_d == null)
		return false;

	    Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.entity, 16, 7, new Vec3d(x, y + 0.5, z));

	    if (vec3d == null)
		return false;
	    else if (this.field_75376_d.getDistanceSq(vec3d) < this.field_75376_d.getDistanceSq(this.entity))
		return false;

	    this.path = this.navigation.func_225466_a(x, y, z, 0);

	    return this.path != null;
	}

	return false;
    }

}
