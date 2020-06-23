package github.kingvampire.DeepTrenches.api.entity.goals;

import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;
import static net.minecraft.entity.ai.goal.Goal.Flag.MOVE;
import static net.minecraft.util.math.BlockPos.ZERO;

import java.util.EnumSet;
import java.util.Random;

import github.kingvampire.DeepTrenches.api.capabilities.anger.IAnger;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public abstract class HomeMoveToBlockGoal extends MoveToBlockGoal {

    protected final int height;
    protected final double probability;
    protected final int range;

    public HomeMoveToBlockGoal(CreatureEntity creature, double speedIn, int range, int height, double probability) {
	super(creature, speedIn, range, height);

	this.height = height;
	this.range = range;

	this.probability = probability;

	this.setMutexFlags(EnumSet.of(MOVE));
    }

    protected boolean isValidPosition(IWorldReader worldIn, BlockPos pos) {
	Random random = new Random();

	if (this.shouldMoveTo(worldIn, pos))
	    return random.nextDouble() < this.probability;

	return false;
    }

    @Override
    protected boolean searchForDestination() {
	BlockPos home = this.creature.getHomePosition();
	World world = this.creature.getEntityWorld();

	MutableBlockPos mutable = new MutableBlockPos();

	for (int y = this.field_203112_e; y <= this.height; y = y > 0 ? -y : 1 - y) {
	    for (int range = 0; range < this.range; ++range) {
		for (int x = 0; x <= range; x = x > 0 ? -x : 1 - x) {
		    for (int z = x < range && x > -range ? range : 0; z <= range; z = z > 0 ? -z : 1 - z) {

			mutable.setPos(home).move(x, y - 1, z);

			if (this.isValidPosition(world, mutable)) {
			    this.destinationBlock = mutable;

			    return true;
			}

		    }
		}
	    }
	}

	return false;
    }

    @Override
    public boolean shouldContinueExecuting() {
	LazyOptional<IAnger> anger = this.creature.getCapability(ANGER_CAPABILITY);

	if (anger.isPresent()) {
	    BlockPos home = this.creature.getHomePosition();
	    IAnger ianger = anger.orElseThrow(IllegalArgumentException::new);

	    if (!ianger.isAngry() && home != ZERO)
		return super.shouldContinueExecuting();

	}

	return false;
    }

    @Override
    public boolean shouldExecute() {
	LazyOptional<IAnger> anger = this.creature.getCapability(ANGER_CAPABILITY);

	if (anger.isPresent()) {
	    BlockPos home = this.creature.getHomePosition();
	    IAnger ianger = anger.orElseThrow(IllegalArgumentException::new);

	    if (!ianger.isAngry() && home != ZERO) {

		if (this.destinationBlock == ZERO)
		    return super.shouldExecute();

		return true;
	    }

	}

	return false;
    }

}
