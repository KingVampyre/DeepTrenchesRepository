package github.kingvampire.DeepTrenches.api.capabilities.tame;

import java.util.UUID;
import java.util.function.Predicate;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Tame implements ITame {

    protected final CreatureEntity creature;

    protected boolean isSitting;
    protected boolean isTamed;
    protected UUID ownerId;
    protected Predicate<ItemStack> predicate;
    protected int tameChance;

    public Tame() {
	this.creature = null;
    }

    public Tame(CreatureEntity creature, int tameChance, Predicate<ItemStack> predicate) {
	this.creature = creature;
	this.predicate = predicate;
	this.tameChance = tameChance;
    }

    @Override
    public CreatureEntity getCreatureEntity() {
	return this.creature;
    }

    @Override
    public PlayerEntity getOwner() {
	World world = this.creature.getEntityWorld();

	if (this.ownerId == null)
	    return null;

	return world.getPlayerByUuid(this.ownerId);
    }

    @Override
    public UUID getOwnerId() {
	return this.ownerId;
    }

    @Override
    public boolean isSitting() {
	return this.isSitting;
    }

    @Override
    public boolean isTamed() {
	return this.isTamed;
    }

    @Override
    public void setOwnerId(UUID ownerId) {
	this.ownerId = ownerId;
    }

    @Override
    public void setSitting(boolean isSitting) {
	this.isSitting = isSitting;
    }

    @Override
    public void setTamed(boolean isTamed) {
	this.isTamed = isTamed;
    }

    @Override
    public int getTameChance() {
	return this.tameChance;
    }

    @Override
    public boolean isTameItem(ItemStack stack) {
	return this.predicate.test(stack);
    }

}
