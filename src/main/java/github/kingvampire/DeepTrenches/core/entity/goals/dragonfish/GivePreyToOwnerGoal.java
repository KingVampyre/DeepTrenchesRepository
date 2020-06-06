package github.kingvampire.DeepTrenches.core.entity.goals.dragonfish;

import static github.kingvampire.DeepTrenches.api.capabilities.breed.BreedProvider.BREED_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.tame.TameProvider.TAME_CAPABILITY;
import static java.lang.Math.PI;
import static net.minecraft.entity.SharedMonsterAttributes.FOLLOW_RANGE;
import static net.minecraft.entity.SharedMonsterAttributes.MOVEMENT_SPEED;
import static net.minecraft.inventory.EquipmentSlotType.MAINHAND;
import static net.minecraft.inventory.EquipmentSlotType.OFFHAND;
import static net.minecraft.item.ItemStack.EMPTY;
import static net.minecraft.item.Items.COD;
import static net.minecraft.item.Items.SALMON;
import static net.minecraft.util.Hand.MAIN_HAND;

import java.util.List;
import java.util.function.Predicate;

import github.kingvampire.DeepTrenches.api.capabilities.breed.IBreed;
import github.kingvampire.DeepTrenches.api.capabilities.tame.ITame;
import github.kingvampire.DeepTrenches.api.entity.DragonfishEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public class GivePreyToOwnerGoal extends Goal {

    private DragonfishEntity dragonfish;
    private double throwDist;

    public GivePreyToOwnerGoal(DragonfishEntity dragonfish, double throwDist) {
	this.dragonfish = dragonfish;
	this.throwDist = throwDist;
    }

    protected ItemEntity getClosestItemEntity() {
	double distance = this.getTargetDistance();

	World world = this.dragonfish.getEntityWorld();
	AxisAlignedBB box = this.getTargetableArea(distance);

	Predicate<ItemEntity> predicate = entity -> {

	    if (!entity.cannotPickup() && entity.isAlive() && entity.isInWater()) {
		ItemStack stack = entity.getItem();
		Item item = stack.getItem();

		return item == COD || item == SALMON;
	    }

	    return false;
	};

	List<ItemEntity> itemEntities = world.getEntitiesWithinAABB(ItemEntity.class, box, predicate);

	if (!itemEntities.isEmpty())
	    return itemEntities.get(0);
	else
	    return null;
    }

    protected AxisAlignedBB getTargetableArea(double distance) {
	return this.dragonfish.getBoundingBox().grow(distance);
    }

    protected double getTargetDistance() {
	return this.dragonfish.getAttribute(FOLLOW_RANGE).getBaseValue();
    }

    @Override
    public void resetTask() {
	ItemStack mainHandStack = this.dragonfish.getItemStackFromSlot(MAINHAND);
	ItemStack offHandStack = this.dragonfish.getItemStackFromSlot(OFFHAND);

	if (!mainHandStack.isEmpty() && !offHandStack.isEmpty()) {
	    this.dragonfish.setItemStackToSlot(MAINHAND, EMPTY);
	    this.dragonfish.setItemStackToSlot(OFFHAND, EMPTY);
	}

	this.dragonfish.setCanPickUpLoot(false);
    }

    @Override
    public boolean shouldContinueExecuting() {
	LazyOptional<ITame> tame = this.dragonfish.getCapability(TAME_CAPABILITY);

	if (tame.isPresent()) {
	    ITame itame = tame.orElseThrow(IllegalArgumentException::new);
	    PlayerEntity player = itame.getOwner();

	    if (player == null)
		return false;

	    if (this.dragonfish.getDistanceSq(player) <= Math.pow(this.throwDist, 2)) {
		ItemStack mainHandStack = this.dragonfish.getHeldItemMainhand();
		ItemStack offfHandStack = this.dragonfish.getHeldItemOffhand();

		if (!this.throwItemStack(mainHandStack) && !this.throwItemStack(offfHandStack)) {
		    this.dragonfish.setCanPickUpLoot(false);

		    return true;
		}
	    }

	}

	return true;
    }

    @Override
    public boolean shouldExecute() {
	LazyOptional<ITame> tame = this.dragonfish.getCapability(TAME_CAPABILITY);

	if (tame.isPresent()) {
	    ITame itame = tame.orElseThrow(IllegalArgumentException::new);

	    if (itame.isTamed() && itame.isSitting()) {
		ItemStack stack = this.dragonfish.getItemStackFromSlot(MAINHAND);

		if (stack.isEmpty())
		    return this.getClosestItemEntity() != null;
		else
		    return true;
	    }
	}

	return false;
    }

    @Override
    public void startExecuting() {
	ItemEntity itemEntity = this.getClosestItemEntity();

	if (itemEntity != null)
	    this.dragonfish.getNavigator().tryMoveToEntityLiving(itemEntity, 1.2F);

	this.dragonfish.setCanPickUpLoot(true);
    }

    private boolean throwItemStack(ItemStack stack) {

	if (!stack.isEmpty()) {
	    World world = this.dragonfish.getEntityWorld();

	    double x = this.dragonfish.posX;
	    double y = this.dragonfish.posY - 0.3F + this.dragonfish.getEyeHeight();
	    double z = this.dragonfish.posZ;

	    ItemEntity itemEntity = new ItemEntity(world, x, y, z, stack);

	    itemEntity.setPickupDelay(40);
	    itemEntity.setThrowerId(this.dragonfish.getUniqueID());

	    float f1 = this.dragonfish.getRNG().nextFloat() * ((float) PI * 2F);
	    float f2 = 0.02F * this.dragonfish.getRNG().nextFloat();

	    float pitch = this.dragonfish.rotationPitch;
	    float yaw = this.dragonfish.rotationYaw;

	    itemEntity.setMotion(
		    (double) (0.1F * -MathHelper.sin(yaw * ((float) PI / 180))
			    * MathHelper.cos(pitch * ((float) PI / 180)) + MathHelper.cos(f1) * f2),
		    (double) (0.1F * MathHelper.sin(pitch * ((float) PI / 180)) * 0.6F),
		    (double) (0.1F * MathHelper.cos(yaw * ((float) PI / 180))
			    * MathHelper.cos(pitch * ((float) PI / 180)) + MathHelper.sin(f1) * f2));

	    return world.addEntity(itemEntity);
	}

	return false;
    }

    @Override
    public void tick() {
	ItemStack stack = this.dragonfish.getItemStackFromSlot(MAINHAND);
	double speed = this.dragonfish.getAttribute(MOVEMENT_SPEED).getBaseValue();

	if (!stack.isEmpty()) {
	    LazyOptional<ITame> tame = this.dragonfish.getCapability(TAME_CAPABILITY);
	    LazyOptional<IBreed> breed = this.dragonfish.getCapability(BREED_CAPABILITY);

	    if (breed.isPresent() && tame.isPresent()) {
		IBreed ibreed = breed.orElseThrow(IllegalArgumentException::new);
		ITame itame = tame.orElseThrow(IllegalArgumentException::new);

		float health = this.dragonfish.getHealth();
		float maxHealth = this.dragonfish.getMaxHealth();

		Food food = stack.getItem().getFood();

		if (ibreed.isBreedingItem(stack) && health != maxHealth) {
		    float healing = food.getHealing();

		    this.dragonfish.heal(healing);
		} else {
		    PlayerEntity player = itame.getOwner();

		    double x = player.posX;
		    double y = player.posY;
		    double z = player.posZ;

		    this.dragonfish.getNavigator().tryMoveToXYZ(x, y, z, speed);
		    this.dragonfish.getLookController().setLookPositionWithEntity(player, 0, 0);
		}
	    }

	} else {
	    ItemEntity itemEntity = this.getClosestItemEntity();

	    if (itemEntity != null) {
		this.dragonfish.getNavigator().tryMoveToEntityLiving(itemEntity, speed);

		if (this.dragonfish.getDistanceSq(itemEntity) <= Math.pow(this.throwDist, 2)) {
		    itemEntity.remove();

		    this.dragonfish.setHeldItem(MAIN_HAND, itemEntity.getItem());
		}
	    }
	}

    }

}
