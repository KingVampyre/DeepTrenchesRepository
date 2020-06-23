package github.kingvampire.DeepTrenches.core.blocks;

import static github.kingvampire.DeepTrenches.api.capabilities.group.GroupProvider.GROUP_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.pollen.PollenProvider.POLLEN_CAPABILITY;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.STASP_NEST;
import static github.kingvampire.DeepTrenches.core.init.ModItems.BOTTLE_OF_AQUEAN_SAP;
import static net.minecraft.item.Items.GLASS_BOTTLE;

import github.kingvampire.DeepTrenches.api.capabilities.group.IGroup;
import github.kingvampire.DeepTrenches.api.capabilities.pollen.IPollen;
import github.kingvampire.DeepTrenches.core.entity.StaspNestTileEntity;
import github.kingvampire.DeepTrenches.core.entity.capability.group.StaspNestGroup;
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
import net.minecraftforge.common.util.LazyOptional;
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

		    if (tileEntity != null) {
			LazyOptional<IGroup> group = tileEntity.getCapability(GROUP_CAPABILITY);

			if (group.isPresent()) {
			    StaspNestGroup igroup = (StaspNestGroup) group.orElseThrow(IllegalArgumentException::new);

			    igroup.onDisturb(event.getPlayer());
			}
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

		LazyOptional<IGroup> group = staspNest.getCapability(GROUP_CAPABILITY);
		LazyOptional<IPollen> pollen = staspNest.getCapability(POLLEN_CAPABILITY);

		if (group.isPresent() && pollen.isPresent()) {
		    StaspNestGroup igroup = (StaspNestGroup) group.orElseThrow(IllegalArgumentException::new);
		    IPollen ipollen = pollen.orElseThrow(IllegalArgumentException::new);

		    int aqueanSap = ipollen.getAqueanSap();

		    if (!ipollen.hasAqueanSapCapacity()) {
			ipollen.setAqueanSap(aqueanSap - 100);

			ItemStack bottle = new ItemStack(BOTTLE_OF_AQUEAN_SAP);

			if (stack.getCount() < 1)
			    player.addItemStackToInventory(bottle);
			else
			    player.setHeldItem(handIn, bottle);

			igroup.onDisturb(player);

			return true;
		    }
		}
	    }
	}

	return false;
    }

}
