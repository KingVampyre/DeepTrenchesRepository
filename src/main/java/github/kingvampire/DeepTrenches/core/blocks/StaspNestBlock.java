package github.kingvampire.DeepTrenches.core.blocks;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.STASP_NEST;
import static github.kingvampire.DeepTrenches.core.init.ModItems.BOTTLE_OF_AQUEAN_SAP;
import static net.minecraft.item.Items.GLASS_BOTTLE;

import github.kingvampire.DeepTrenches.core.entity.StaspNestTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

public class StaspNestBlock extends Block {

	@EventBusSubscriber
	public static class Subscriber {

		@SubscribeEvent
		public static void onStaspNestDestroy(BreakEvent event) {
			World world = (World) event.getWorld();

			if (!world.isRemote()) {
				BlockState state = event.getState();

				if (state.getBlock() == STASP_NEST) {
					BlockPos pos = event.getPos();

					TileEntity tileEntity = world.getTileEntity(pos);

					if (tileEntity != null && tileEntity instanceof StaspNestTileEntity) {
						StaspNestTileEntity staspNest = (StaspNestTileEntity) tileEntity;

						staspNest.spawnAllStasps(event.getPlayer());
					}
				}
			}
		}

	}

	public static final BooleanProperty SAP = BooleanProperty.create("sap");

	public StaspNestBlock(Properties properties) {
		super(properties);

		this.setDefaultState(this.stateContainer.getBaseState().with(SAP, false));
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new StaspNestTileEntity();
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);

		builder.add(SAP);
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
			BlockRayTraceResult hit) {

		if (!worldIn.isRemote()) {
			ItemStack stack = player.getHeldItem(handIn);

			if (stack.getItem() == GLASS_BOTTLE && state.get(SAP)) {
				StaspNestTileEntity staspNest = (StaspNestTileEntity) worldIn.getTileEntity(pos);

				ItemStack bottle = new ItemStack(BOTTLE_OF_AQUEAN_SAP);
				int pollen = staspNest.geAqueanSap();

				if (pollen >= 100) {
					staspNest.setAqueanSap(pollen - 100);

					if (stack.getCount() < 1)
						player.addItemStackToInventory(bottle);
					else
						player.setHeldItem(handIn, bottle);

					staspNest.spawnAllStasps(player);

					return true;
				}
			}
		}

		return false;
	}

}
