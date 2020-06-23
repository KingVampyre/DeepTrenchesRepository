package github.kingvampire.DeepTrenches.core.util.registry;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.entity.EntityClassification.MISC;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

import java.util.ArrayList;
import java.util.List;

import github.kingvampire.DeepTrenches.api.entity.tileentity.ModBoatEntity;
import github.kingvampire.DeepTrenches.core.entity.AdaiggerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityType.Builder;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(bus = MOD)
public class EntityRegistry {

    @SubscribeEvent
    public static void registerEntities(Register<EntityType<?>> event) {
	List<EntityType<?>> types = new ArrayList<>();

	EntityType<AdaiggerEntity> adaigger = Builder.<AdaiggerEntity>create(AdaiggerEntity::new, MISC)
		.size(0.5F, 0.5F)
		.setCustomClientFactory(AdaiggerEntity::new)
		.build(MODID + ".adaigger");

	EntityType<ModBoatEntity> boat = Builder.<ModBoatEntity>create(ModBoatEntity::new, MISC)
		.size(1.375F, 0.5625F)
		.setCustomClientFactory(ModBoatEntity::new)
		.build(MODID + ".boat");

	types.add(adaigger.setRegistryName(new ResourceLocation(MODID, "adaigger")));
	types.add(boat.setRegistryName(new ResourceLocation(MODID, "boat")));

	types.forEach(event.getRegistry()::register);
    }

}
