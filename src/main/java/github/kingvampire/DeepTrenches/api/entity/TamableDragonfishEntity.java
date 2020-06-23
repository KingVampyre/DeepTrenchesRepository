package github.kingvampire.DeepTrenches.api.entity;

import static github.kingvampire.DeepTrenches.api.capabilities.age.AgeProvider.AGE_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.breed.BreedProvider.BREED_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.tame.TameProvider.TAME_CAPABILITY;
import static github.kingvampire.DeepTrenches.core.init.ModItems.LOOSEJAW_TOOTH;
import static net.minecraft.entity.EntityType.ITEM;
import static net.minecraft.entity.SpawnReason.TRIGGERED;

import github.kingvampire.DeepTrenches.api.capabilities.age.IAge;
import github.kingvampire.DeepTrenches.api.capabilities.breed.IBreed;
import github.kingvampire.DeepTrenches.api.capabilities.tame.ITame;
import github.kingvampire.DeepTrenches.api.entity.goals.UnderwaterFollowOwnerGoal;
import github.kingvampire.DeepTrenches.api.entity.goals.UnderwaterSitGoal;
import github.kingvampire.DeepTrenches.core.entity.BettaEntity;
import github.kingvampire.DeepTrenches.core.entity.goals.dragonfish.GivePreyToOwnerGoal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public abstract class TamableDragonfishEntity extends GroupDragonfishEntity {

    public static final IAttribute DROP_TOOTH_CHANCE = new RangedAttribute(null, "loosejaw.dropToothChance", 0, 0, 1);

    public TamableDragonfishEntity(EntityType<? extends TamableDragonfishEntity> type, World worldIn) {
	super(type, worldIn);
    }

    @Override
    public boolean canAttack(LivingEntity target) {
	LazyOptional<ITame> tame = this.getCapability(TAME_CAPABILITY);

	if (tame.isPresent()) {
	    ITame itame = tame.orElseThrow(IllegalArgumentException::new);

	    return itame.canAttack(target) && target instanceof BettaEntity;
	}

	return false;
    }

    @Override
    public Team getTeam() {
	LazyOptional<ITame> tame = this.getCapability(TAME_CAPABILITY);

	return tame.isPresent() ? tame.orElseThrow(IllegalArgumentException::new).getTeam() : super.getTeam();
    }

    @Override
    public void handleStatusUpdate(byte id) {
	super.handleStatusUpdate(id);

	this.getCapability(TAME_CAPABILITY).ifPresent(itame -> itame.handleStatusUpdate(id));
    }

    @Override
    public boolean isOnSameTeam(Entity entityIn) {
	LazyOptional<ITame> tame = this.getCapability(TAME_CAPABILITY);

	if (tame.isPresent())
	    return tame.orElseThrow(IllegalArgumentException::new).isOnSameTeam(entityIn);

	return super.isOnSameTeam(entityIn);
    }

    @Override
    public void livingTick() {
	super.livingTick();

	LazyOptional<IAge> age = this.getCapability(AGE_CAPABILITY);
	LazyOptional<IBreed> breed = this.getCapability(BREED_CAPABILITY);

	if (age.isPresent() && breed.isPresent()) {
	    IAge iage = age.orElseThrow(IllegalArgumentException::new);
	    IBreed ibreed = breed.orElseThrow(IllegalArgumentException::new);

	    iage.livingTick();
	    ibreed.livingTick(iage);

	    if (!this.world.isRemote()) {
		this.world.setEntityState(this, (byte) 8);

		iage.sendPacket(this);
		ibreed.sendPacket(this);
	    }
	}

    }

    @Override
    public void onDeath(DamageSource cause) {
	super.onDeath(cause);

	this.getCapability(TAME_CAPABILITY).ifPresent(tame -> tame.onDeath(cause));
    }

    @Override
    protected boolean processInteract(PlayerEntity player, Hand hand) {
	LazyOptional<IBreed> breed = this.getCapability(BREED_CAPABILITY);
	LazyOptional<ITame> tame = this.getCapability(TAME_CAPABILITY);

	if (tame.isPresent()) {
	    IBreed ibreed = breed.orElseThrow(IllegalArgumentException::new);
	    ITame itame = tame.orElseThrow(IllegalArgumentException::new);

	    if (itame.isTamed()) {
		BlockPos pos = this.getPosition();
		ItemStack stack = player.getHeldItem(hand);

		float health = this.getHealth();
		float maxHealth = this.getMaxHealth();

		if (ibreed.isBreedingItem(stack) && health != maxHealth) {
		    double chance = this.getAttribute(DROP_TOOTH_CHANCE).getBaseValue();

		    if (chance < this.rand.nextDouble()) {
			ItemEntity entity = ITEM.spawn(this.world, null, null, player, pos, TRIGGERED, false, false);

			entity.setItem(new ItemStack(LOOSEJAW_TOOTH, 1 + this.rand.nextInt(1)));
		    }
		}

	    }

	    return itame.processInteract(player, hand) || super.processInteract(player, hand);
	}

	return super.processInteract(player, hand);
    }

    @Override
    public boolean isChild() {
	LazyOptional<IAge> age = this.getCapability(AGE_CAPABILITY);

	if (age.isPresent())
	    return age.orElseThrow(IllegalArgumentException::new).isChild();

	return false;
    }

    @Override
    protected void registerAttributes() {
	super.registerAttributes();

	this.getAttributes().registerAttribute(DROP_TOOTH_CHANCE);
    }

    @Override
    protected void registerGoals() {
	super.registerGoals();

	this.goalSelector.addGoal(2, new UnderwaterSitGoal(this));
	this.goalSelector.addGoal(3, new UnderwaterFollowOwnerGoal(this, 1F, 12F));
	this.goalSelector.addGoal(3, new GivePreyToOwnerGoal(this, 3.5F));
    }

}
