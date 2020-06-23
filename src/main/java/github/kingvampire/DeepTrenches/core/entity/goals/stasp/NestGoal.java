package github.kingvampire.DeepTrenches.core.entity.goals.stasp;

import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.group.GroupProvider.GROUP_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.pollen.PollenProvider.POLLEN_CAPABILITY;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.STASP_NEST;
import static net.minecraft.entity.SharedMonsterAttributes.FLYING_SPEED;
import static net.minecraft.entity.ai.goal.Goal.Flag.MOVE;
import static net.minecraft.util.math.BlockPos.ZERO;

import java.util.EnumSet;

import github.kingvampire.DeepTrenches.api.capabilities.anger.IAnger;
import github.kingvampire.DeepTrenches.api.capabilities.group.IGroup;
import github.kingvampire.DeepTrenches.api.capabilities.pollen.IPollen;
import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import github.kingvampire.DeepTrenches.core.entity.StaspNestTileEntity;
import github.kingvampire.DeepTrenches.core.entity.capability.group.StaspNestGroup;
import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public class NestGoal extends MoveToBlockGoal {

    private int range;

    public NestGoal(StaspEntity stasp, int range) {
	super(stasp, stasp.getAttribute(FLYING_SPEED).getBaseValue(), range, range);

	this.range = range;

	this.setMutexFlags(EnumSet.of(MOVE));
    }

    @Override
    protected int getRunDelay(CreatureEntity creatureIn) {
	return 100;
    }

    @Override
    protected boolean searchForDestination() {
	BlockPos home = this.creature.getHomePosition();

	if (home == ZERO)
	    return super.searchForDestination();

	this.destinationBlock = home;

	return true;
    }

    @Override
    public boolean shouldContinueExecuting() {
	LazyOptional<IAnger> anger = this.creature.getCapability(ANGER_CAPABILITY);

	if (anger.isPresent())
	    return !anger.orElseThrow(IllegalArgumentException::new).isAngry() && super.shouldContinueExecuting();

	return false;
    }

    @Override
    public boolean shouldExecute() {
	LazyOptional<IAnger> anger = this.creature.getCapability(ANGER_CAPABILITY);
	LazyOptional<IPollen> pollen = this.creature.getCapability(POLLEN_CAPABILITY);

	if (anger.isPresent() && pollen.isPresent()) {
	    IAnger ianger = anger.orElseThrow(IllegalArgumentException::new);
	    IPollen ipollen = pollen.orElseThrow(IllegalArgumentException::new);

	    if (this.creature.getHomePosition() == ZERO || !ipollen.hasAqueanSapCapacity())
		return !ianger.isAngry() && super.shouldExecute();

	}

	return false;

    }

    @Override
    protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
	BlockState state = worldIn.getBlockState(pos);
	TileEntity tileEntity = worldIn.getTileEntity(pos);

	if (state.getBlock() == STASP_NEST) {
	    LazyOptional<IGroup> group = tileEntity.getCapability(GROUP_CAPABILITY);

	    if (group.isPresent()) {
		StaspNestGroup igroup = (StaspNestGroup) group.orElseThrow(IllegalArgumentException::new);

		if (igroup.getGroupSize() < igroup.getMaxGroupSize())
		    return true;

	    }
	}

	return false;
    }

    @Override
    public void startExecuting() {
	super.startExecuting();

	if (this.creature.getHomePosition() == ZERO)
	    this.creature.setHomePosAndDistance(this.destinationBlock, this.range);
    }

    @Override
    public void tick() {
	super.tick();

	LazyOptional<IPollen> pollen = this.creature.getCapability(POLLEN_CAPABILITY);

	if (pollen.isPresent()) {
	    IPollen ipollen = pollen.orElseThrow(IllegalArgumentException::new);

	    if (!ipollen.hasAqueanSapCapacity()) {
		double distance = this.getTargetDistanceSq();
		Vec3d vec = this.creature.getPositionVec();

		if (this.destinationBlock.withinDistance(vec, distance)) {
		    StaspEntity stasp = (StaspEntity) this.creature;

		    BlockPos home = stasp.getHomePosition();
		    World world = stasp.getEntityWorld();

		    TileEntity tileEntity = world.getTileEntity(home);

		    if (tileEntity instanceof StaspNestTileEntity) {
			StaspNestTileEntity staspNest = (StaspNestTileEntity) tileEntity;

			staspNest.addStasp(stasp);
		    }
		}
	    }

	}
    }

}