package github.kingvampire.DeepTrenches.core.potion;

import static github.kingvampire.DeepTrenches.core.init.ModEffects.DRAINING;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

public class DrainingEffectInstance extends EffectInstance {

	private int duration;
	private LivingEntity livingEntity;

	public LivingEntity getLivingEntity() {
		return livingEntity;
	}

	public DrainingEffectInstance(LivingEntity livingEntity, int durationIn, int amplifierIn) {
		super(DRAINING, 0, amplifierIn);

		this.duration = durationIn;
		this.livingEntity = livingEntity;
	}

	@Override
	public boolean combine(EffectInstance other) {
		boolean flag = super.combine(other);

		float amplifier = this.getAmplifier();
		int duration = other.getDuration();

		if (other.getAmplifier() > amplifier) {
			this.duration = duration;
			flag = true;
		} else if (other.getAmplifier() == amplifier && this.duration < duration) {
			this.duration = duration;
			flag = true;
		}

		return flag;
	}

	@Override
	public void performEffect(LivingEntity entityIn) {

		if (this.duration > 0)
			this.getPotion().performEffect(entityIn, this.getAmplifier());
	}

	private int deincrementDuration() {
		return --this.duration;
	}

	public String toString() {
		int amplifier = this.getAmplifier() + 1;
		String name = this.getEffectName();

		String effectName = amplifier > 0 ? name + " x " + amplifier : name;

		return effectName + ", Duration: " + this.duration + ", Splash: false, Particles: true, Show Icon: true";
	}

	public boolean equals(Object object) {

		if (this == object)
			return true;
		else if (!(object instanceof EffectInstance))
			return false;

		EffectInstance effect = (EffectInstance) object;

		float amplifier = effect.getAmplifier();
		Effect potion = effect.getPotion();

		return this.duration == effect.getDuration() && this.getAmplifier() == amplifier
				&& this.getPotion().equals(potion) && this.isAmbient() == effect.isAmbient()
				&& this.doesShowParticles() == effect.doesShowParticles() && this.isShowIcon() == effect.isShowIcon();
	}

	public int hashCode() {
		int i = this.getPotion().hashCode();
		i = 31 * i + this.duration;
		i = 31 * i + this.getAmplifier();
		i = 31 * i;
		i = 31 * i + (this.isAmbient() ? 1 : 0);

		return i;
	}

	@Override
	public boolean tick(LivingEntity entityIn) {
		Effect effect = this.getPotion();
		int amplifier = this.getAmplifier();

		if (this.duration > 0) {

			if (effect.isReady(this.duration, amplifier))
				effect.affectEntity(this.livingEntity, null, entityIn, amplifier, entityIn.getHealth());

			this.deincrementDuration();
		}

		return this.getDuration() > 0;
	}

	@Override
	public int getDuration() {
		return this.duration;
	}

}
