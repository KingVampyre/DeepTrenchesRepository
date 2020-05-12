package github.kingvampire.DeepTrenches.core.entity.goals.stasp;

import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;
import static net.minecraft.util.math.BlockPos.ZERO;

import github.kingvampire.DeepTrenches.api.capabilities.anger.IAnger;
import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import github.kingvampire.DeepTrenches.core.entity.StaspNestTileEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public class EnterNestGoal extends Goal {

    private StaspEntity staspEntity;

    public EnterNestGoal(StaspEntity staspEntity) {
	this.staspEntity = staspEntity;
    }

    @Override
    public boolean shouldContinueExecuting() {
	return false;
    }

    @Override
    public boolean shouldExecute() {
	LazyOptional<IAnger> anger = this.staspEntity.getCapability(ANGER_CAPABILITY);

	BlockPos home = this.staspEntity.getHomePosition();
	BlockPos pos = this.staspEntity.getPosition();
	World world = this.staspEntity.getEntityWorld();

	int aqueanSap = this.staspEntity.getAqueanSap();

	if (anger.isPresent()) {
	    IAnger ianger = anger.orElseThrow(IllegalArgumentException::new);

	    if (home != ZERO && !ianger.isAngry() && aqueanSap == 100 && home.withinDistance(pos, 1.5F)) {
		TileEntity tileEntity = world.getTileEntity(home);

		if (tileEntity instanceof StaspNestTileEntity) {
		    StaspNestTileEntity staspNest = (StaspNestTileEntity) tileEntity;

		    if (staspNest.canAddStasp())
			return true;

		    this.staspEntity.setHomePosAndDistance(ZERO, -1);
		}
	    }
	}

	return false;
    }

    @Override
    public void startExecuting() {
	BlockPos home = this.staspEntity.getHomePosition();
	World world = this.staspEntity.getEntityWorld();

	TileEntity tileEntity = world.getTileEntity(home);

	if (tileEntity instanceof StaspNestTileEntity) {
	    StaspNestTileEntity staspNest = (StaspNestTileEntity) tileEntity;

	    staspNest.addStasp(this.staspEntity);
	}
    }

}