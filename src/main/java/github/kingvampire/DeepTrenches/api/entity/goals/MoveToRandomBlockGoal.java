package github.kingvampire.DeepTrenches.api.entity.goals;

import static net.minecraft.entity.ai.goal.Goal.Flag.MOVE;
import static net.minecraft.util.math.BlockPos.ZERO;

import java.util.EnumSet;
import java.util.Random;

import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;

public abstract class MoveToRandomBlockGoal extends MoveToBlockGoal {

	protected int height;
	protected int probability;
	protected int range;

	public MoveToRandomBlockGoal(StaspEntity staspEntity, double speedIn, int range, int height, int probability) {
		super(staspEntity, speedIn, range, height);

		this.height = height;
		this.probability = probability;
		this.range = range;

		this.setMutexFlags(EnumSet.of(MOVE));
	}

	protected void func_220725_g() {
		int x = this.destinationBlock.getX();
		int y = this.destinationBlock.getY();
		int z = this.destinationBlock.getZ();

		this.creature.getNavigator().tryMoveToXYZ(x, y + 2.5, z, this.movementSpeed);
	}

	public BlockPos getDestination() {
		return this.destinationBlock;
	}

	protected boolean getIsAboveDestination() {
		BlockPos pos = this.creature.getPosition();
		double distance = this.getTargetDistanceSq();

		return this.destinationBlock.up().withinDistance(pos, distance);
	}

	@Override
	public double getTargetDistanceSq() {
		return 1.5;
	}

	@Override
	protected boolean searchForDestination() {
		BlockPos pos = this.creature.getPosition();
		World world = this.creature.getEntityWorld();

		MutableBlockPos mutable = new MutableBlockPos();

		for (int y = 0; y <= this.height; y = y > 0 ? -y : 1 - y) {
			for (int radius = 0; radius < this.range; ++radius) {
				for (int x = 0; x <= radius; x = x > 0 ? -x : 1 - x) {
					for (int z = x < radius && x > -radius ? radius : 0; z <= radius; z = z > 0 ? -z : 1 - z) {

						mutable.setPos(pos).move(x, y - 1, z);

						if (this.creature.isWithinHomeDistanceFromPosition(mutable)) {
							Random random = this.creature.getRNG();

							if (this.shouldMoveTo(world, mutable)) {
								if (this.probability == 0 || random.nextInt(this.probability) == 0) {
									this.runDelay = this.getRunDelay(this.creature);
									this.destinationBlock = mutable;

									return true;
								}
							}
						}
					}
				}
			}
		}

		return false;
	}

	@Override
	public boolean shouldContinueExecuting() {
		StaspEntity staspEntity = (StaspEntity) this.creature;

		if (!staspEntity.isAngry() && !this.getIsAboveDestination())
			return this.timeoutCounter <= 1200 && this.shouldMoveTo(this.creature.world, this.destinationBlock);

		return false;
	}

	@Override
	public boolean shouldExecute() {
		StaspEntity staspEntity = (StaspEntity) this.creature;

		if (!staspEntity.isAngry()) {

			if (this.destinationBlock == ZERO)
				return this.searchForDestination();

			int ticks = staspEntity.getTicksSincePollination();

			if (this.getIsAboveDestination())
				if (ticks < 200)
					return this.searchForDestination();
				else if (ticks > 200)
					return false;

			if (this.runDelay > 0) {
				--this.runDelay;

				return false;
			}

			return this.searchForDestination();
		}

		return false;
	}

	public void tick() {
		BlockPos pos = this.creature.getPosition();
		double distance = this.getTargetDistanceSq();

		if (!this.destinationBlock.withinDistance(pos, distance)) {
			++this.timeoutCounter;

			if (this.shouldMove())
				this.func_220725_g();

		} else
			--this.timeoutCounter;

	}

}
