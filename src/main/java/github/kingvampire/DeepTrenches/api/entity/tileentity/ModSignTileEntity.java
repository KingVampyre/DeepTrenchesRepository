package github.kingvampire.DeepTrenches.api.entity.tileentity;

import static github.kingvampire.DeepTrenches.core.init.TileEntities.SIGN;

import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class ModSignTileEntity extends SignTileEntity {

    @Override
    public TileEntityType<?> getType() {
	return SIGN;
    }

}
