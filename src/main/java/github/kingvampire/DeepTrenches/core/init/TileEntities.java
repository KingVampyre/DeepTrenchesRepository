package github.kingvampire.DeepTrenches.core.init;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import github.kingvampire.DeepTrenches.api.entity.tileentity.ModSignTileEntity;
import github.kingvampire.DeepTrenches.core.entity.StaspNestTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(MODID)
public class TileEntities {

	public static final TileEntityType<ModSignTileEntity> SIGN = null;
	public static final TileEntityType<StaspNestTileEntity> STASP_NEST = null;

}
