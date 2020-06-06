package github.kingvampire.DeepTrenches.api.entity.goals;

import static net.minecraft.entity.SharedMonsterAttributes.MOVEMENT_SPEED;
import static net.minecraft.entity.ai.goal.Goal.Flag.MOVE;

import java.util.EnumSet;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AvoidEntityGoal<T extends LivingEntity> extends Goal {

    protected final Class<T> avoidClass;
    protected final CreatureEntity creature;
    protected final float distance;
    protected final double farSpeed;
    protected final PathNavigator navigation;
    protected final double nearSpeed;

    protected Path path;
    protected T target;

    public AvoidEntityGoal(CreatureEntity creature, Class<T> avoidClass, float distance) {
	this.creature = creature;
	this.avoidClass = avoidClass;
	this.distance = distance;
	this.navigation = creature.getNavigator();

	// TODO use movement speed boost
	this.farSpeed = creature.getAttribute(MOVEMENT_SPEED).getValue();
	this.nearSpeed = creature.getAttribute(MOVEMENT_SPEED).getValue();

	this.setMutexFlags(EnumSet.of(MOVE));
    }

    protected EntityPredicate getEntityPredicate() {
	return new EntityPredicate();
    }

    @Override
    public void resetTask() {
	this.target = null;
    }

    @Override
    public boolean shouldContinueExecuting() {
	return !this.navigation.noPath();
    }

    @Override
    public boolean shouldExecute() {
	AxisAlignedBB aabb = this.creature.getBoundingBox().grow(this.distance);
	World world = this.creature.getEntityWorld();

	double x = this.creature.posX;
	double y = this.creature.posY;
	double z = this.creature.posZ;

	this.target = world.func_225318_b(this.avoidClass, this.getEntityPredicate(), this.creature, x, y, z, aabb);

	if (this.target == null)
	    return false;
	else {
	    Vec3d vec = this.target.getPositionVec();
	    int distance = (int) this.distance;

	    Vec3d pos = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.creature, distance, distance, vec);

	    if (pos == null)
		return false;
	    else if (this.target.getDistanceSq(pos) < this.target.getDistanceSq(this.creature))
		return false;

	    this.path = this.navigation.func_225466_a(pos.x, pos.y, pos.z, 0);

	    return this.path != null;
	}
    }

    @Override
    public void startExecuting() {
	this.navigation.setPath(this.path, this.farSpeed);
    }

    @Override
    public void tick() {

	if (this.creature.getDistanceSq(this.target) < this.distance)
	    this.navigation.setSpeed(this.nearSpeed);
	else
	    this.navigation.setSpeed(this.farSpeed);

    }
}
