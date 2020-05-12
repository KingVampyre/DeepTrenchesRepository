package github.kingvampire.DeepTrenches.core.util;

import static github.kingvampire.DeepTrenches.core.entity.BettaEntity.BABY;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.BETTA;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;

import github.kingvampire.DeepTrenches.api.capabilities.ageable.AgeableProvider;
import github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider;
import github.kingvampire.DeepTrenches.api.capabilities.group.GroupProvider;
import github.kingvampire.DeepTrenches.api.capabilities.mateable.MateableProvider;
import github.kingvampire.DeepTrenches.api.capabilities.tameable.TameableProvider;
import github.kingvampire.DeepTrenches.core.entity.BettaEntity;
import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class AttachCapability {

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
	Entity entity = event.getObject();

	if (entity instanceof StaspEntity)
	    event.addCapability(new ResourceLocation(MODID, "anger"), new AngerProvider(200, 100));

    }

}