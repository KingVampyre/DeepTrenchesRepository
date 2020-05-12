package github.kingvampire.DeepTrenches.api.events;

import javax.annotation.Nullable;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class BabySpawnEvent extends Event {

    private final CreatureEntity parentA;
    private final CreatureEntity parentB;
    private final PlayerEntity player;

    private CreatureEntity child;

    public BabySpawnEvent(CreatureEntity parentA, CreatureEntity parentB, @Nullable CreatureEntity child,
	    @Nullable PlayerEntity player) {

	this.parentA = parentA;
	this.parentB = parentB;
	this.player = player;

	this.child = child;
    }

    @Nullable
    public PlayerEntity getPlayer() {
	return this.player;
    }

    @Nullable
    public CreatureEntity getChild() {
	return this.child;
    }

    public CreatureEntity getParentA() {
	return this.parentA;
    }

    public CreatureEntity getParentB() {
	return this.parentB;
    }

    public void setChild(CreatureEntity child) {
	this.child = child;
    }

}
