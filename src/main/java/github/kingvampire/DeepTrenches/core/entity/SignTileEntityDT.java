package github.kingvampire.DeepTrenches.core.entity;

import static github.kingvampire.DeepTrenches.core.init.TileEntities.SIGN;

import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class SignTileEntityDT extends SignTileEntity {

	@Override
	public TileEntityType<?> getType() {
		return SIGN;
	}

}
