package github.kingvampire.DeepTrenches.core.entity.goals.stasp;

import static github.kingvampire.DeepTrenches.api.capabilities.pollen.PollenProvider.POLLEN_CAPABILITY;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.AQUEAN_SAPLING;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.FUCHSITRA_SAPLING;
import static net.minecraft.entity.SharedMonsterAttributes.FLYING_SPEED;
import static net.minecraft.util.math.BlockPos.ZERO;

import github.kingvampire.DeepTrenches.api.capabilities.pollen.IPollen;
import github.kingvampire.DeepTrenches.api.entity.goals.HomeMoveToBlockGoal;
import github.kingvampire.DeepTrenches.core.blocks.base.ModSaplingBlock;
import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public class PollinateSaplingGoal extends HomeMoveToBlockGoal {

    public PollinateSaplingGoal(StaspEntity stasp, int range, int height, double probability) {
	super(stasp, stasp.getAttribute(FLYING_SPEED).getBaseValue(), range, height, probability);
    }

    @Override
    protected int getRunDelay(CreatureEntity creatureIn) {
	return 1200;
    }

    @Override
    public boolean shouldContinueExecuting() {
	LazyOptional<IPollen> pollen = this.creature.getCapability(POLLEN_CAPABILITY);

	if (pollen.isPresent()) {
	    IPollen ipollen = pollen.orElseThrow(IllegalArgumentException::new);

	    return !ipollen.hasPollenCapacity() && !ipollen.hasAqueanSap() && super.shouldContinueExecuting();
	}

	return false;
    }

    @Override
    public boolean shouldExecute() {
	LazyOptional<IPollen> pollen = this.creature.getCapability(POLLEN_CAPABILITY);

	if (pollen.isPresent()) {
	    IPollen ipollen = pollen.orElseThrow(IllegalArgumentException::new);

	    return !ipollen.hasPollenCapacity() && !ipollen.hasAqueanSap() && super.shouldExecute();
	}

	return false;
    }

    @Override
    protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
	BlockState state = worldIn.getBlockState(pos);
	Block block = state.getBlock();

	return block == AQUEAN_SAPLING || block == FUCHSITRA_SAPLING;
    }

    @Override
    public void tick() {
	super.tick();

	if (this.getIsAboveDestination()) {
	    StaspEntity stasp = (StaspEntity) this.creature;

	    BlockPos pos = this.destinationBlock;
	    World world = stasp.getEntityWorld();

	    BlockState state = world.getBlockState(pos);
	    Block block = state.getBlock();

	    if (block instanceof ModSaplingBlock) {
		LazyOptional<IPollen> pollen = this.creature.getCapability(POLLEN_CAPABILITY);

		if (pollen.isPresent()) {
		    IPollen ipollen = pollen.orElseThrow(IllegalArgumentException::new);
		    ModSaplingBlock sapling = (ModSaplingBlock) block;

		    sapling.grow(world, pos, state, world.rand);
		    world.playEvent(2005, pos, 0);
		    ipollen.setPollen(0);

		    this.destinationBlock = ZERO;
		}
	    }
	}
    }

}
