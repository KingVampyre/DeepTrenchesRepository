package github.kingvampire.DeepTrenches.api.events;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent;

public class TameEvent extends LivingEvent {

    private final CreatureEntity creature;
    private final PlayerEntity tamer;

    public TameEvent(CreatureEntity creature, PlayerEntity tamer) {
	super(creature);

	this.creature = creature;
	this.tamer = tamer;
    }

    public CreatureEntity getCreature() {
	return this.creature;
    }

    public PlayerEntity getTamer() {
	return this.tamer;
    }

}
