package github.kingvampire.DeepTrenches.core.entity.goals.stasp;

import static github.kingvampire.DeepTrenches.api.capabilities.pollen.PollenProvider.POLLEN_CAPABILITY;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.AQUEAN_LEAVES;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.FUCHSITRA_LEAVES;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.MOSOIL;
import static net.minecraft.entity.SharedMonsterAttributes.FLYING_SPEED;
import static net.minecraft.util.math.BlockPos.ZERO;

import java.util.Random;

import github.kingvampire.DeepTrenches.api.capabilities.pollen.IPollen;
import github.kingvampire.DeepTrenches.api.entity.goals.HomeMoveToBlockGoal;
import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.util.LazyOptional;

public class PollinateFlowerGoal extends HomeMoveToBlockGoal {

    protected int pollinateCounter;
    protected int pollinateDelay;

    public PollinateFlowerGoal(StaspEntity stasp, int range, int height, double probability) {
	super(stasp, stasp.getAttribute(FLYING_SPEED).getBaseValue(), range, height, probability);
    }

    protected int getPollinateDelay(CreatureEntity creatureIn) {
	return 300;
    }

    @Override
    protected int getRunDelay(CreatureEntity creatureIn) {
	return 500;
    }

    @Override
    public boolean shouldContinueExecuting() {
	LazyOptional<IPollen> pollen = this.creature.getCapability(POLLEN_CAPABILITY);

	if (pollen.isPresent()) {
	    IPollen ipollen = pollen.orElseThrow(IllegalArgumentException::new);

	    if (ipollen.hasPollenCapacity() && !ipollen.hasAqueanSap() && ipollen.getTicksSincePollination() != 0)
		return super.shouldContinueExecuting();
	}

	return false;
    }

    @Override
    public boolean shouldExecute() {
	LazyOptional<IPollen> pollen = this.creature.getCapability(POLLEN_CAPABILITY);

	if (pollen.isPresent()) {
	    IPollen ipollen = pollen.orElseThrow(IllegalArgumentException::new);

	    return ipollen.hasPollenCapacity() && !ipollen.hasAqueanSap() && super.shouldExecute();
	}

	return false;
    }

    @Override
    public boolean shouldMove() {
	return true;
    }

    @Override
    protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
	BlockState state = worldIn.getBlockState(pos);
	Block block = state.getBlock();

	BlockPos down = this.creature.getPosition().down();
	BlockPos up = pos.up();

	BlockState upState = worldIn.getBlockState(up);

	if (!down.equals(pos) && upState.isAir(worldIn, up))
	    return block == AQUEAN_LEAVES || block == FUCHSITRA_LEAVES || block == MOSOIL;

	return false;
    }

    @Override
    public void tick() {
	super.tick();

	if (this.getIsAboveDestination()) {
	    StaspEntity stasp = (StaspEntity) this.creature;

	    if (this.pollinateCounter == 0)
		this.pollinateCounter = this.getPollinateDelay(stasp);

	    if (this.pollinateCounter > 0)
		--this.pollinateCounter;

	    if (this.pollinateCounter == 0) {
		LazyOptional<IPollen> pollen = this.creature.getCapability(POLLEN_CAPABILITY);

		if (pollen.isPresent()) {
		    IPollen ipollen = pollen.orElseThrow(IllegalArgumentException::new);

		    Random random = stasp.getRNG();

		    int currPollen = ipollen.getPollen();
		    int maxPollen = ipollen.getMaxPollen();

		    int newPollen = currPollen + 25 + random.nextInt(15);

		    if (newPollen >= maxPollen)
			ipollen.setPollen(maxPollen);
		    else
			ipollen.setPollen(newPollen);

		    this.destinationBlock = ZERO;
		    ipollen.setTicksSincePollination(0);
		}
	    }
	} else
	    this.creature.getNavigator().updatePath();

    }

}