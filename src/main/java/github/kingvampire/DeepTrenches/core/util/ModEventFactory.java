package github.kingvampire.DeepTrenches.core.util;

import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

import javax.annotation.Nullable;

import github.kingvampire.DeepTrenches.api.events.BabySpawnEvent;
import github.kingvampire.DeepTrenches.api.events.TameEvent;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.player.PlayerEntity;

public class ModEventFactory {

    public static boolean onTame(CreatureEntity creature, PlayerEntity tamer) {
	return EVENT_BUS.post(new TameEvent(creature, tamer));
    }

}
