package github.kingvampire.DeepTrenches.api.capabilities.age;

import static github.kingvampire.DeepTrenches.api.capabilities.age.AgeProvider.AGE_CAPABILITY;
import static net.minecraft.particles.ParticleTypes.HAPPY_VILLAGER;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public interface IAge {

    default void addGrowth(int growth) {
	this.ageUp(growth, false);
    }

    default void ageUp(int growthSeconds, boolean updateForcedAge) {
	int growingAge = this.getGrowingAge();
	int age = growingAge + growthSeconds * 20;

	if (age > 0)
	    age = 0;

	this.setGrowingAge(age);

	if (updateForcedAge) {
	    this.setForcedAge(this.getForcedAge() + age - growingAge);

	    if (this.getForcedAgeTimer() == 0)
		this.setForcedAgeTimer(40);
	}

	if (this.getGrowingAge() == 0)
	    this.setGrowingAge(this.getForcedAge());

    }

    @Nullable
    CreatureEntity createChild(CreatureEntity creature);

    CreatureEntity getCreatureEntity();

    int getForcedAge();

    int getForcedAgeTimer();

    int getGrowingAge();

    default boolean isChild() {
	return this.getGrowingAge() < 0;
    }

    /**
     * Override CreatureEntity::livingTick
     */
    default void livingTick() {
	CreatureEntity creature = this.getCreatureEntity();
	World world = creature.getEntityWorld();

	if (world.isRemote) {
	    int forcedAgeTimer = this.getForcedAgeTimer();

	    if (forcedAgeTimer > 0) {
		if (forcedAgeTimer % 4 == 0) {
		    Random rand = creature.getRNG();

		    double height = creature.getHeight();
		    double width = creature.getWidth();

		    double x = creature.posX + (double) (rand.nextFloat() * width * 2F) - width;
		    double y = creature.posY + 0.5 + (double) (rand.nextFloat() * height);
		    double z = creature.posZ + (double) (rand.nextFloat() * width * 2F) - width;

		    world.addParticle(HAPPY_VILLAGER, x, y, z, 0, 0, 0);
		}

		this.setForcedAgeTimer(--forcedAgeTimer);
	    }
	} else if (creature.isAlive()) {
	    int growingAge = this.getGrowingAge();

	    if (growingAge < 0)
		this.setGrowingAge(++growingAge);
	    else if (growingAge > 0)
		this.setGrowingAge(--growingAge);
	}

    }

    /**
     * Override CreatureEntity::notifyDataManagerChange
     */
    void notifyDataManagerChange(DataParameter<?> key);

    void onChildSpawnFromEgg(PlayerEntity playerIn, CreatureEntity child);

    void onGrowingAdult();

    /**
     * Override CreatureEntity::processInteract
     */
    default boolean processInteract(PlayerEntity player, Hand hand) {
	CreatureEntity creature = this.getCreatureEntity();
	World world = creature.getEntityWorld();

	ItemStack stack = player.getHeldItem(hand);
	Item item = stack.getItem();

	if (item instanceof SpawnEggItem) {
	    SpawnEggItem eggItem = (SpawnEggItem) item;

	    CompoundNBT compound = stack.getTag();
	    EntityType<?> entityType = creature.getType();

	    if (!world.isRemote && eggItem.hasType(compound, entityType)) {
		CreatureEntity child = this.createChild(creature);

		if (child != null) {
		    LazyOptional<IAge> lazyOptional = child.getCapability(AGE_CAPABILITY);

		    if (lazyOptional.isPresent()) {
			IAge ageable = lazyOptional.orElse(null);

			double x = creature.posX;
			double y = creature.posY;
			double z = creature.posZ;

			ageable.setGrowingAge(-24000);
			child.setLocationAndAngles(x, y, z, 0, 0);

			world.addEntity(child);

			if (stack.hasDisplayName())
			    child.setCustomName(stack.getDisplayName());

			this.onChildSpawnFromEgg(player, child);

			if (!player.abilities.isCreativeMode)
			    stack.shrink(1);
		    }
		}
	    }

	    return true;
	}

	return false;
    }

    void setForcedAge(int forcedAge);

    void setForcedAgeTimer(int forcedAgeTimer);

    void setGrowingAge(int growingAge);
}
