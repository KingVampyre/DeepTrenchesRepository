package github.kingvampire.DeepTrenches.core.blocks.base;

import github.kingvampire.DeepTrenches.api.entity.ModSignTileEntity;
import net.minecraft.block.StandingSignBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class ModStandingSignBlock extends StandingSignBlock {

	public ModStandingSignBlock(Properties properties) {
		super(properties);
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new ModSignTileEntity();
	}

}
