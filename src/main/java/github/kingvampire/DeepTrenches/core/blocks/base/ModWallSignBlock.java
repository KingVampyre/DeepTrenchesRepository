package github.kingvampire.DeepTrenches.core.blocks.base;

import github.kingvampire.DeepTrenches.api.entity.tileentity.ModSignTileEntity;
import net.minecraft.block.WallSignBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class ModWallSignBlock extends WallSignBlock {

	public ModWallSignBlock(Properties properties) {
		super(properties);
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new ModSignTileEntity();
	}

}
