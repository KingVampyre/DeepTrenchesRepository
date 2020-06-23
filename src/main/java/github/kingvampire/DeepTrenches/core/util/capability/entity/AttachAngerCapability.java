package github.kingvampire.DeepTrenches.core.util.capability.entity;

import static github.kingvampire.DeepTrenches.core.init.ModEntities.BARBELED_LOOSEJAW;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.BETTA;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.BLACK_LOOSEJAW;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.DEEP_LAKE_BETTA;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.LIGHT_LOOSEJAW;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.SMALLTOOTH_DRAGONFISH;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.STASP;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class AttachAngerCapability {

    @SubscribeEvent
    public static void attach(AttachCapabilitiesEvent<Entity> event) {
	Entity entity = event.getObject();
	EntityType<?> type = entity.getType();

	ResourceLocation id = new ResourceLocation(MODID, "anger");

	if (type == BETTA || type == DEEP_LAKE_BETTA)
	    event.addCapability(id, new AngerProvider(100, 100));

	else if (type == BLACK_LOOSEJAW || type == LIGHT_LOOSEJAW || type == BARBELED_LOOSEJAW || type == SMALLTOOTH_DRAGONFISH)
	    event.addCapability(id, new AngerProvider(200, 200));

	else if (type == STASP)
	    event.addCapability(id, new AngerProvider(200, 100));

    }

}
