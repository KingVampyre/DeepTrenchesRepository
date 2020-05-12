package github.kingvampire.DeepTrenches.api.entity.goals;

import static github.kingvampire.DeepTrenches.api.capabilities.tame.TameProvider.TAME_CAPABILITY;
import static net.minecraft.entity.ai.goal.Goal.Flag.LOOK;
import static net.minecraft.entity.ai.goal.Goal.Flag.MOVE;
import static net.minecraft.pathfinding.PathNodeType.WATER;

import java.util.EnumSet;

import github.kingvampire.DeepTrenches.api.capabilities.tame.ITame;
import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class FollowOwnerGoal extends Goal {

    protected final CreatureEntity creature;
    protected final float maxDist;
    protected final float minDist;
    protected final PathNavigator navigator;
    protected float oldWaterCost;
    protected final double speed;

    protected ITame itame;
    protected final float teleportDist;
    protected int timeToRecalcPath;

    public FollowOwnerGoal(CreatureEntity creature, double speed, float minDist, float maxDist,
	    float teleportDist) {

	this.creature = creature;
	this.maxDist = maxDist;
	this.minDist = minDist;
	this.speed = speed;
	this.teleportDist = teleportDist;

	this.itame = creature.getCapability(TAME_CAPABILITY).orElseThrow(IllegalArgumentException::new);
	this.navigator = creature.getNavigator();

	this.setMutexFlags(EnumSet.of(MOVE, LOOK));
    }

    protected boolean canTeleportToBlock(BlockPos pos) {
	World world = this.creature.getEntityWorld();
	BlockState state = world.getBlockState(pos);

	if (state.canEntitySpawn(world, pos, this.creature.getType()))
	    return world.isAirBlock(pos.up()) && world.isAirBlock(pos.up(2));

	return false;
    }

    @Override
    public void resetTask() {
	this.navigator.clearPath();
	this.creature.setPathPriority(WATER, this.oldWaterCost);
    }

    @Override
    public boolean shouldContinueExecuting() {
	PlayerEntity owner = this.itame.getOwner();

	if (!this.itame.isSitting() && !this.navigator.noPath())
	    return this.creature.getDistanceSq(owner) > this.minDist * this.minDist;

	return false;
    }

    @Override
    public boolean shouldExecute() {
	PlayerEntity player = this.itame.getOwner();

	if (player == null)
	    return false;
	else if (player.isSpectator())
	    return false;
	else if (this.itame.isSitting())
	    return false;
	else if (this.creature.getDistanceSq(player) < this.maxDist * this.maxDist)
	    return false;

	return true;

    }

    protected boolean shouldTeleport(PlayerEntity owner) {
	this.navigator.tryMoveToEntityLiving(owner, this.speed);

	if (!this.navigator.tryMoveToEntityLiving(owner, this.speed)) {
	    double distance = this.creature.getDistanceSq(owner);

	    if (!this.creature.getLeashed() && !this.creature.isPassenger())
		return !(distance < this.teleportDist * this.teleportDist);
	}

	return false;
    }

    @Override
    public void startExecuting() {
	this.creature.setPathPriority(WATER, 0F);
	this.oldWaterCost = this.creature.getPathPriority(WATER);
	this.timeToRecalcPath = 0;
    }

    @Override
    public void tick() {
	PlayerEntity owner = this.itame.getOwner();
	int speed = this.creature.getVerticalFaceSpeed();

	this.creature.getLookController().setLookPositionWithEntity(owner, 10F, speed);

	if (!this.itame.isSitting() && --this.timeToRecalcPath <= 0) {
	    this.timeToRecalcPath = 10;

	    if (this.shouldTeleport(owner)) {
		int x = MathHelper.floor(owner.posX) - 2;
		int y = MathHelper.floor(owner.getBoundingBox().minY);
		int z = MathHelper.floor(owner.posZ) - 2;

		for (int xOffset = 0; xOffset <= 4; ++xOffset) {
		    for (int zOffset = 0; zOffset <= 4; ++zOffset) {

			float rotationPitch = this.creature.rotationPitch;
			float rotationYaw = this.creature.rotationYaw;

			int dx = x + xOffset;
			int dy = y - 1;
			int dz = z + zOffset;

			if (xOffset < 1 || zOffset < 1 || xOffset > 3 || zOffset > 3) {
			    BlockPos pos = new BlockPos(dx, dy, dz);

			    if (this.canTeleportToBlock(pos)) {

				this.creature.setLocationAndAngles(dx + 0.5F, y, dz + 0.5F, rotationYaw, rotationPitch);
				this.navigator.clearPath();

				return;
			    }
			}
		    }
		}
	    }
	}
    }

}
