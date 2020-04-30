package github.kingvampire.DeepTrenches.core.init;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;

public class DamageSources {

	public static final DamageSource DRAINING = new DamageSource("draining");

	public static final DamageSource SPROOM_SPIKE = new DamageSource("sproom_spike");

	public static DamageSource causeAdaiggerDamage(Entity source, @Nullable Entity entity) {
		IndirectEntityDamageSource damageSource = new IndirectEntityDamageSource("adaigger", source, entity);

		return damageSource.setProjectile();
	}
}
