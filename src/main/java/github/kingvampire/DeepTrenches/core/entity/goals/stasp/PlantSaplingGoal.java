package github.kingvampire.DeepTrenches.core.entity.goals.stasp;

import static github.kingvampire.DeepTrenches.api.capabilities.pollen.PollenProvider.POLLEN_CAPABILITY;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.AQUEAN_SAPLING;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.FUCHSITRA_SAPLING;
import static net.minecraft.entity.SharedMonsterAttributes.FLYING_SPEED;
import static net.minecraft.util.math.BlockPos.ZERO;

import github.kingvampire.DeepTrenches.api.capabilities.pollen.IPollen;
import github.kingvampire.DeepTrenches.api.entity.goals.HomeMoveToBlockGoal;
import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public class PlantSaplingGoal extends HomeMoveToBlockGoal {

    public PlantSaplingGoal(StaspEntity stasp, int range, int height, double probability) {
	super(stasp, stasp.getAttribute(FLYING_SPEED).getBaseValue(), range, height, probability);
    }

    @Override
    protected int getRunDelay(CreatureEntity creatureIn) {
	return 900;
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

	// TODO mosoil
	return block == Blocks.GRASS_BLOCK;
    }

    @Override
    public void tick() {
	super.tick();

	if (this.getIsAboveDestination()) {
	    BlockPos pos = this.destinationBlock.up();
	    World worldIn = this.creature.getEntityWorld();

	    Block block = worldIn.rand.nextBoolean() ? AQUEAN_SAPLING : FUCHSITRA_SAPLING;
	    BlockState state = block.getDefaultState();

	    LazyOptional<IPollen> pollen = this.creature.getCapability(POLLEN_CAPABILITY);

	    if (pollen.isPresent()) {
		IPollen ipollen = pollen.orElseThrow(IllegalArgumentException::new);

		if (!worldIn.isRemote()) {
		    worldIn.playEvent(null, 2001, pos, Block.getStateId(state));
		    ipollen.setPollen(0);
		    worldIn.setBlockState(pos, state);

		    this.destinationBlock = ZERO;
		}
	    }
	}

    }

}
