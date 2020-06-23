package github.kingvampire.DeepTrenches.core.util.capability.tileentity;

import static github.kingvampire.DeepTrenches.core.init.TileEntities.STASP_NEST;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import github.kingvampire.DeepTrenches.api.capabilities.pollen.PollenProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class AttachPollenCapability {

    @SubscribeEvent
    public static void attach(AttachCapabilitiesEvent<TileEntity> event) {
	TileEntity creature = event.getObject();
	TileEntityType<?> type = creature.getType();

	ResourceLocation id = new ResourceLocation(MODID, "pollen");

	if (type == STASP_NEST)
	    event.addCapability(id, new PollenProvider(500, 0));
    }

}
