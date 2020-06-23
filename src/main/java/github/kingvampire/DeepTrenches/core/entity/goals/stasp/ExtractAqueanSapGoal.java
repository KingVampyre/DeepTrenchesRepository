package github.kingvampire.DeepTrenches.core.entity.goals.stasp;

import static github.kingvampire.DeepTrenches.api.capabilities.pollen.PollenProvider.POLLEN_CAPABILITY;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.AQUEAN_LOG;
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public class ExtractAqueanSapGoal extends HomeMoveToBlockGoal {

    protected int pollinateCounter;
    protected int pollinateDelay;

    public ExtractAqueanSapGoal(StaspEntity stasp, int range, int height, double probability) {
	super(stasp, stasp.getAttribute(FLYING_SPEED).getBaseValue(), range, height, probability);
    }

    protected int getPollinateDelay(CreatureEntity creatureIn) {
	return 300;
    }

    @Override
    protected int getRunDelay(CreatureEntity creatureIn) {
	return 700;
    }

    @Override
    public double getTargetDistanceSq() {
	return 1.15;
    }

    @Override
    public boolean shouldContinueExecuting() {
	LazyOptional<IPollen> pollen = this.creature.getCapability(POLLEN_CAPABILITY);

	if (pollen.isPresent()) {
	    IPollen ipollen = pollen.orElseThrow(IllegalArgumentException::new);

	    return ipollen.hasAqueanSapCapacity() && !ipollen.hasPollen() && super.shouldContinueExecuting();
	}

	return false;
    }

    @Override
    public boolean shouldExecute() {
	LazyOptional<IPollen> pollen = this.creature.getCapability(POLLEN_CAPABILITY);

	if (pollen.isPresent()) {
	    IPollen ipollen = pollen.orElseThrow(IllegalArgumentException::new);

	    return ipollen.hasAqueanSapCapacity() && !ipollen.hasPollen() && super.shouldExecute();
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

	return block == AQUEAN_LOG;
    }

    @Override
    public void tick() {
	super.tick();

	StaspEntity stasp = (StaspEntity) this.creature;

	double distance = this.getTargetDistanceSq();
	BlockPos pos = stasp.getPosition();
	World world = stasp.getEntityWorld();

	if (this.destinationBlock.withinDistance(pos, distance)) {

	    stasp.posY = (double) MathHelper.floor(stasp.posY) + 1 - (double) stasp.getHeight();

	    if (this.pollinateCounter == 0) {
		this.pollinateCounter = this.getPollinateDelay(this.creature);

		stasp.setIsHanging(true);
		world.playEvent(null, 1025, pos, 0);
	    }

	    if (this.pollinateCounter > 0)
		--this.pollinateCounter;

	    if (this.pollinateCounter == 0) {
		LazyOptional<IPollen> pollen = this.creature.getCapability(POLLEN_CAPABILITY);

		if (pollen.isPresent()) {
		    IPollen ipollen = pollen.orElseThrow(IllegalArgumentException::new);

		    Random random = this.creature.getRNG();

		    int aqueanSap = ipollen.getAqueanSap();
		    int maxAqueanSap = ipollen.getMaxAqueanSap();

		    int sap = aqueanSap + 25 + random.nextInt(15);

		    if (sap >= maxAqueanSap)
			ipollen.setAqueanSap(maxAqueanSap);
		    else
			ipollen.setAqueanSap(sap);

		    this.destinationBlock = ZERO;

		    ipollen.setTicksSincePollination(0);
		    stasp.setIsHanging(false);
		    world.playEvent(null, 1025, pos, 0);
		}
	    }
	} else
	    stasp.setIsHanging(false);
    }

}
