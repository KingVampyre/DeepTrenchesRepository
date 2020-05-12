package github.kingvampire.DeepTrenches.api.capabilities.breed;

import java.util.UUID;
import java.util.function.Predicate;

import com.google.common.base.Predicates;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.item.ItemStack;

public class Breed implements IBreed {

    protected final CreatureEntity creature;

    protected int inLove;
    protected UUID playerInLove;
    protected Predicate<ItemStack> predicate;

    public Breed() {
	this.creature = null;
	this.predicate = Predicates.alwaysTrue();
    }

    public Breed(CreatureEntity creature, Predicate<ItemStack> predicate) {
	this.creature = creature;
	this.predicate = predicate;
    }

    @Override
    public CreatureEntity getCreatureEntity() {
	return this.creature;
    }

    @Override
    public int getInLove() {
	return this.inLove;
    }

    @Override
    public UUID getPlayerInLove() {
	return this.playerInLove;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
	return this.predicate.test(stack);
    }

    @Override
    public void setInLove(int inLove) {
	this.inLove = inLove;
    }

    @Override
    public void setPlayerInLove(UUID playerInLove) {
	this.playerInLove = playerInLove;
    }

}
