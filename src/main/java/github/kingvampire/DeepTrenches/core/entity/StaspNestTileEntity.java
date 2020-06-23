package github.kingvampire.DeepTrenches.core.entity;

import static github.kingvampire.DeepTrenches.api.capabilities.group.GroupProvider.GROUP_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.pollen.PollenProvider.POLLEN_CAPABILITY;
import static github.kingvampire.DeepTrenches.core.blocks.StaspNestBlock.SAP;
import static github.kingvampire.DeepTrenches.core.init.TileEntities.STASP_NEST;

import github.kingvampire.DeepTrenches.api.capabilities.group.IGroup;
import github.kingvampire.DeepTrenches.api.capabilities.pollen.IPollen;
import github.kingvampire.DeepTrenches.core.entity.capability.group.StaspNestGroup;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.LazyOptional;

public class StaspNestTileEntity extends TileEntity implements ITickableTileEntity {

    public StaspNestTileEntity() {
	super(STASP_NEST);
    }

    public void addStasp(StaspEntity entity) {
	LazyOptional<IGroup> group = this.getCapability(GROUP_CAPABILITY);
	LazyOptional<IPollen> pollen = this.getCapability(POLLEN_CAPABILITY);

	LazyOptional<IPollen> entityPollen = entity.getCapability(POLLEN_CAPABILITY);

	if (entityPollen.isPresent() && group.isPresent() && pollen.isPresent()) {
	    IGroup igroup = group.orElseThrow(IllegalArgumentException::new);
	    IPollen ipollen = pollen.orElseThrow(IllegalArgumentException::new);

	    IPollen ientityPollen = entityPollen.orElseThrow(IllegalArgumentException::new);

	    if (igroup.getMembers().add(entity)) {
		int aqueanSap = ientityPollen.getAqueanSap();
		int maxAqueanSap = ipollen.getMaxAqueanSap();

		int newAqueanSap = ipollen.getAqueanSap() + aqueanSap;

		ientityPollen.setAqueanSap(0);
		entity.remove(true);

		if (newAqueanSap >= maxAqueanSap)
		    ipollen.setAqueanSap(maxAqueanSap);
		else
		    ipollen.setAqueanSap(newAqueanSap);

	    }
	}

    }

    @Override
    public void tick() {

	if (!this.world.isRemote()) {
	    LazyOptional<IGroup> group = this.getCapability(GROUP_CAPABILITY);
	    LazyOptional<IPollen> pollen = this.getCapability(POLLEN_CAPABILITY);

	    if (group.isPresent() && pollen.isPresent()) {
		StaspNestGroup igroup = (StaspNestGroup) group.orElseThrow(IllegalArgumentException::new);
		IPollen ipollen = pollen.orElseThrow(IllegalArgumentException::new);

		igroup.tick();

		BlockState state = this.world.getBlockState(this.pos);

		this.world.setBlockState(this.pos, state.with(SAP, !ipollen.hasAqueanSapCapacity()), 3);
	    }
	}
    }

}
