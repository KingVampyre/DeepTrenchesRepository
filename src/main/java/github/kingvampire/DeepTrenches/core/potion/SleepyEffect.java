package github.kingvampire.DeepTrenches.core.potion;

import static github.kingvampire.DeepTrenches.core.init.ModEffects.SLEEPY;
import static net.minecraft.potion.EffectType.NEUTRAL;
import static net.minecraftforge.eventbus.api.Event.Result.ALLOW;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.SleepingTimeCheckEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

public class SleepyEffect extends Effect {

	public SleepyEffect() {
		super(NEUTRAL, 3549040);
	}

	@Override
	public void performEffect(LivingEntity entityLiving, int amplifier) {
		World world = entityLiving.getEntityWorld();

		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entityLiving;
			AxisAlignedBB aabb = player.getBoundingBox().grow(40, 40, 40);

			world.getEntitiesWithinAABB(PhantomEntity.class, aabb)
				.stream()
				.filter(phantom -> phantom.getAttackingEntity() == player)
				.forEach(phantom -> phantom.setFire(45));
		}
	}

	@EventBusSubscriber
	public static class Subscriber {

		@SubscribeEvent
		public static void sleepAtDay(SleepingTimeCheckEvent event) {
			PlayerEntity player = event.getPlayer();

			if (player.isPotionActive(SLEEPY))
				event.setResult(ALLOW);
		}

	}

}
