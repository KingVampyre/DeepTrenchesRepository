package github.kingvampire.DeepTrenches.core.entity.capability.group;

import github.kingvampire.DeepTrenches.api.capabilities.group.GroupProvider;
import github.kingvampire.DeepTrenches.core.entity.StaspNestTileEntity;
import net.minecraftforge.common.util.LazyOptional;

public class StaspNestProvider extends GroupProvider {

    public StaspNestProvider(StaspNestTileEntity tileEntity, int maxGroupSize) {
	super(null, maxGroupSize);

	this.group = LazyOptional.of(() -> new StaspNestGroup(tileEntity, maxGroupSize));
    }

}
