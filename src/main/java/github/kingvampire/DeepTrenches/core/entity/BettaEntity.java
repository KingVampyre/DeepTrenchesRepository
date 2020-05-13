package github.kingvampire.DeepTrenches.core.entity;

import static github.kingvampire.DeepTrenches.api.capabilities.age.AgeProvider.AGE_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.breed.BreedProvider.BREED_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.group.GroupProvider.GROUP_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.tame.TameProvider.TAME_CAPABILITY;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.BETTA;
import static github.kingvampire.DeepTrenches.core.init.ModItems.BETTA_BUCKET;
import static net.minecraft.entity.SharedMonsterAttributes.ATTACK_DAMAGE;
import static net.minecraft.entity.SharedMonsterAttributes.MAX_HEALTH;
import static net.minecraft.entity.SharedMonsterAttributes.MOVEMENT_SPEED;
import static net.minecraft.network.datasync.DataSerializers.BOOLEAN;
import static net.minecraft.network.datasync.DataSerializers.VARINT;
import static net.minecraft.util.Hand.MAIN_HAND;

import com.google.common.collect.Sets;

import github.kingvampire.DeepTrenches.api.capabilities.age.IAge;
import github.kingvampire.DeepTrenches.api.capabilities.anger.IAnger;
import github.kingvampire.DeepTrenches.api.capabilities.breed.IBreed;
import github.kingvampire.DeepTrenches.api.capabilities.group.IGroup;
import github.kingvampire.DeepTrenches.api.capabilities.tame.ITame;
import github.kingvampire.DeepTrenches.api.entity.goals.AngryAttackGoal;
import github.kingvampire.DeepTrenches.api.entity.goals.BreedGoal;
import github.kingvampire.DeepTrenches.api.entity.goals.RandomSwimGroupGoal;
import github.kingvampire.DeepTrenches.core.entity.goals.betta.BettaAngerGoal;
import github.kingvampire.DeepTrenches.core.entity.goals.betta.BettaAvoidPlayerGoal;
import github.kingvampire.DeepTrenches.core.entity.goals.betta.BettaFollowGroupLeaderGoal;
import github.kingvampire.DeepTrenches.core.entity.goals.betta.BettaFollowOwnerGoal;
import github.kingvampire.DeepTrenches.core.entity.goals.betta.BettaNearestAttackableTargetGoal;
import github.kingvampire.DeepTrenches.core.entity.goals.betta.BettaSitGoal;
import github.kingvampire.DeepTrenches.core.util.ModEventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Foods;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class BettaEntity extends AbstractFishEntity {

    public static final DataParameter<Boolean> BABY = EntityDataManager.createKey(BettaEntity.class, BOOLEAN);
    public static final DataParameter<Integer> COLOR = EntityDataManager.createKey(BettaEntity.class, VARINT);

    public BettaEntity(EntityType<? extends BettaEntity> type, World worldIn) {
	super(type, worldIn);
    }

    public BettaEntity(FMLPlayMessages.SpawnEntity spawnEntity, World worldIn) {
	this(BETTA, worldIn);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
	LazyOptional<IBreed> breed = this.getCapability(BREED_CAPABILITY);

	if (breed.isPresent()) {
	    IBreed ibreed = breed.orElseThrow(IllegalArgumentException::new);

	    if (ibreed.shouldAttackEntityFrom(source, amount))
		return super.attackEntityFrom(source, amount);
	}

	return false;
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
    public IPacket<?> createSpawnPacket() {
	return NetworkHooks.getEntitySpawningPacket(this);
    }

    protected SoundEvent getAmbientSound() {
	// TODO ambient sound
	return null;
    }

    public int getColor() {
	return this.dataManager.get(COLOR);
    }

    protected SoundEvent getDeathSound() {
	// TODO death sound
	return null;
    }

    @Override
    protected ItemStack getFishBucket() {
	return new ItemStack(BETTA_BUCKET);
    }

    @Override
    protected SoundEvent getFlopSound() {
	// TODO flop sound
	return null;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
	// TODO hurt sound
	return null;
    }

    @Override
    public Team getTeam() {
	LazyOptional<ITame> tame = this.getCapability(TAME_CAPABILITY);

	return tame.isPresent() ? tame.orElseThrow(IllegalArgumentException::new).getTeam() : super.getTeam();
    }

    @Override
    public void handleStatusUpdate(byte id) {
	super.handleStatusUpdate(id);

	this.getCapability(BREED_CAPABILITY).ifPresent(ibreed -> ibreed.handleStatusUpdate(id));
	this.getCapability(TAME_CAPABILITY).ifPresent(itame -> itame.handleStatusUpdate(id));
    }

    @Override
    public boolean isChild() {
	return this.dataManager.get(BABY);
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

	this.getCapability(AGE_CAPABILITY).ifPresent(iage -> {
	    iage.livingTick();

	    this.getCapability(BREED_CAPABILITY).ifPresent(ibreed -> ibreed.livingTick(iage));
	});

	this.getCapability(ANGER_CAPABILITY).ifPresent(ianger -> ianger.livingTick());
    }

    @Override
    public void notifyDataManagerChange(DataParameter<?> key) {
	super.notifyDataManagerChange(key);

	this.getCapability(AGE_CAPABILITY).ifPresent(age -> age.notifyDataManagerChange(key));
    }

    @Override
    public void onDeath(DamageSource cause) {
	super.onDeath(cause);

	this.getCapability(GROUP_CAPABILITY).ifPresent(group -> group.onDeath(cause));
	this.getCapability(TAME_CAPABILITY).ifPresent(tame -> tame.onDeath(cause));
    }

    @Override
    protected boolean processInteract(PlayerEntity player, Hand hand) {
	LazyOptional<IAnger> anger = this.getCapability(ANGER_CAPABILITY);
	LazyOptional<IAge> age = this.getCapability(AGE_CAPABILITY);
	LazyOptional<IGroup> group = this.getCapability(GROUP_CAPABILITY);
	LazyOptional<IBreed> breed = this.getCapability(BREED_CAPABILITY);
	LazyOptional<ITame> tame = this.getCapability(TAME_CAPABILITY);

	if (!this.world.isRemote() && hand == MAIN_HAND) {
	    ItemStack stack = player.getHeldItem(hand);
	    Food food = stack.getItem().getFood();

	    if (anger.isPresent() && group.isPresent() && breed.isPresent() && tame.isPresent()) {
		IAnger ianger = anger.orElseThrow(IllegalArgumentException::new);
		IGroup igroup = group.orElseThrow(IllegalArgumentException::new);
		ITame itame = tame.orElseThrow(IllegalArgumentException::new);

		if (itame.isTamed()) {

		    if (food == Foods.COD && this.getHealth() != this.getMaxHealth()) {

			if (!player.abilities.isCreativeMode)
			    stack.shrink(1);

			this.heal(food.getHealing());

			return true;
		    }

		    if (itame.isOwner(player) && stack.isEmpty()) {
			this.navigator.clearPath();
			this.setAttackTarget(null);

			itame.setSitting(!itame.isSitting());
		    }

		} else if (food == Foods.COD && !ianger.isAngry()) {

		    if (!player.abilities.isCreativeMode)
			stack.shrink(1);

		    if (this.rand.nextInt(3) == 0 && !ModEventFactory.onTame(this, player)) {
			igroup.setGroupLeader(null);
			igroup.setGroup(Sets.newHashSet());

			this.setAttackTarget(null);

			itame.setTamedBy(player);
			itame.setSitting(false);

			this.navigator.clearPath();
			this.world.setEntityState(this, (byte) 7);
		    } else
			this.world.setEntityState(this, (byte) 6);

		    return true;
		}
	    }

	    if (age.isPresent() && breed.isPresent() && tame.isPresent()) {
		IAge iage = age.orElseThrow(IllegalArgumentException::new);
		ITame itame = tame.orElseThrow(IllegalArgumentException::new);

		if (iage.processInteract(player, hand))
		    return true;

		IBreed ibreed = breed.orElseThrow(IllegalArgumentException::new);

		if (itame.isTamed() && ibreed.processInteract(iage, player, hand))
		    return true;
	    }
	}

	return super.processInteract(player, hand);
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
	super.readAdditional(compound);

	this.setColor(compound.getInt("Color"));
    }

    @Override
    protected void registerAttributes() {
	super.registerAttributes();

	this.getAttribute(MAX_HEALTH).setBaseValue(4F);
	this.getAttribute(MOVEMENT_SPEED).setBaseValue(0.65F);

	this.getAttributes().registerAttribute(ATTACK_DAMAGE).setBaseValue(3F);
    }

    @Override
    protected void registerData() {
	super.registerData();

	this.dataManager.register(BABY, false);
	this.dataManager.register(COLOR, 0);
    }

    @Override
    protected void registerGoals() {
	this.goalSelector.addGoal(0, new PanicGoal(this, 1.25F));
	this.goalSelector.addGoal(1, new AngryAttackGoal(this, 0.7F, false));
	this.goalSelector.addGoal(2, new BettaSitGoal(this));
	this.goalSelector.addGoal(2, new BettaAvoidPlayerGoal(this, 8F, 2F, 1.8F));
	this.goalSelector.addGoal(3, new BettaFollowGroupLeaderGoal(this, 0.7F));
	this.goalSelector.addGoal(3, new BettaFollowOwnerGoal(this, 1F, 10F, 2F, 12F));
	this.goalSelector.addGoal(3, new BreedGoal(this, 3, 0.7F));
	this.goalSelector.addGoal(4, new RandomSwimGroupGoal(this, 0.7F, 10));

	this.targetSelector.addGoal(0, new BettaAngerGoal(this));
	this.targetSelector.addGoal(1, new BettaNearestAttackableTargetGoal(this, 3F));
    }

    @Override
    protected void setBucketData(ItemStack bucket) {
	super.setBucketData(bucket);

	bucket.setTagInfo("Entity", this.serializeNBT());
    }

    public void setColor(int color) {
	this.dataManager.set(COLOR, color);
    }

    @Override
    protected void updateAITasks() {
	LazyOptional<IAge> age = this.getCapability(AGE_CAPABILITY);
	LazyOptional<IBreed> breed = this.getCapability(BREED_CAPABILITY);

	age.ifPresent(iage -> breed.ifPresent(ibreed -> ibreed.updateAITasks(iage)));
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
	super.writeAdditional(compound);

	compound.putInt("Color", this.getColor());
    }

}
