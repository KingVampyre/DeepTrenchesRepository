package github.kingvampire.DeepTrenches.core.util.capability.tileentity;

import static github.kingvampire.DeepTrenches.core.init.TileEntities.STASP_NEST;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import github.kingvampire.DeepTrenches.core.entity.StaspNestTileEntity;
import github.kingvampire.DeepTrenches.core.entity.capability.group.StaspNestProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class AttachGroupCapability {

    @SubscribeEvent
    public static void attach(AttachCapabilitiesEvent<TileEntity> event) {
	TileEntity tileEntity = event.getObject();
	TileEntityType<?> type = tileEntity.getType();

	ResourceLocation id = new ResourceLocation(MODID, "group");

	if (type == STASP_NEST) {
	    StaspNestTileEntity staspNest = (StaspNestTileEntity) tileEntity;

	    event.addCapability(id, new StaspNestProvider(staspNest, 3));
	}
    }

}
