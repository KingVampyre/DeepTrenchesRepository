package github.kingvampire.DeepTrenches.core.entity;

import static github.kingvampire.DeepTrenches.api.capabilities.age.AgeProvider.AGE_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.breed.BreedProvider.BREED_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.group.GroupProvider.GROUP_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.tame.TameProvider.TAME_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.taxonomy.TaxonomyProvider.TAXONOMY_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.entity.HatchetfishEntity.MOVEMENT_SPEED_BOOST;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.BETTA;
import static github.kingvampire.DeepTrenches.core.init.ModItems.BETTA_BUCKET;
import static net.minecraft.entity.SharedMonsterAttributes.ATTACK_DAMAGE;
import static net.minecraft.entity.SharedMonsterAttributes.MAX_HEALTH;
import static net.minecraft.entity.SharedMonsterAttributes.MOVEMENT_SPEED;

import github.kingvampire.DeepTrenches.api.capabilities.age.IAge;
import github.kingvampire.DeepTrenches.api.capabilities.breed.IBreed;
import github.kingvampire.DeepTrenches.api.capabilities.tame.ITame;
import github.kingvampire.DeepTrenches.api.entity.goals.AngryAttackGoal;
import github.kingvampire.DeepTrenches.api.entity.goals.AvoidPlayerGoal;
import github.kingvampire.DeepTrenches.api.entity.goals.BreedGoal;
import github.kingvampire.DeepTrenches.api.entity.goals.GroupPanicGoal;
import github.kingvampire.DeepTrenches.api.entity.goals.UnderwaterFollowOwnerGoal;
import github.kingvampire.DeepTrenches.api.entity.goals.UnderwaterSitGoal;
import github.kingvampire.DeepTrenches.api.entity.goals.UntameGroupSwimGoal;
import github.kingvampire.DeepTrenches.api.entity.goals.UntamedFollowGroupLeaderGoal;
import github.kingvampire.DeepTrenches.api.taxonomy.RankInstance;
import github.kingvampire.DeepTrenches.core.entity.goals.betta.BettaAngerGoal;
import github.kingvampire.DeepTrenches.core.entity.goals.betta.BettaPreyingGoal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class BettaEntity extends AbstractFishEntity {

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
	LazyOptional<IAge> age = this.getCapability(AGE_CAPABILITY);

	if (age.isPresent())
	    return age.orElseThrow(IllegalArgumentException::new).isChild();

	return false;
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

	    this.getCapability(BREED_CAPABILITY).ifPresent(ibreed -> {
		ibreed.livingTick(iage);

		if (!this.world.isRemote())
		    this.world.setEntityState(this, (byte) 8);

	    });
	});

	this.getCapability(ANGER_CAPABILITY).ifPresent(ianger -> {
	    ianger.livingTick();

	    if (!this.world.isRemote && this.getAttackTarget() == null && ianger.isAngry())
		ianger.setAnger(0);

	});
    }

    @Override
    public void onDeath(DamageSource cause) {
	super.onDeath(cause);

	this.getCapability(GROUP_CAPABILITY).ifPresent(group -> group.onDeath(cause));
	this.getCapability(TAME_CAPABILITY).ifPresent(tame -> tame.onDeath(cause));
    }

    @Override
    protected boolean processInteract(PlayerEntity player, Hand hand) {
	LazyOptional<ITame> tame = this.getCapability(TAME_CAPABILITY);

	if (tame.isPresent()) {
	    ITame itame = tame.orElseThrow(IllegalArgumentException::new);

	    return itame.processInteract(player, hand) || super.processInteract(player, hand);
	}

	return super.processInteract(player, hand);
    }

    @Override
    protected void registerAttributes() {
	super.registerAttributes();

	this.getAttribute(MAX_HEALTH).setBaseValue(4F);
	this.getAttribute(MOVEMENT_SPEED).setBaseValue(0.9F);

	this.getAttributes().registerAttribute(ATTACK_DAMAGE).setBaseValue(3F);
	this.getAttributes().registerAttribute(MOVEMENT_SPEED_BOOST).setBaseValue(2.13F);
    }

    @Override
    protected void registerGoals() {
	this.goalSelector.addGoal(0, new GroupPanicGoal(this));
	this.goalSelector.addGoal(1, new AngryAttackGoal(this, false));
	this.goalSelector.addGoal(2, new UnderwaterSitGoal(this));
	this.goalSelector.addGoal(2, new AvoidPlayerGoal(this, 8F));
	this.goalSelector.addGoal(3, new UntamedFollowGroupLeaderGoal(this));
	this.goalSelector.addGoal(3, new UnderwaterFollowOwnerGoal(this, 1F, 12F));
	this.goalSelector.addGoal(3, new BreedGoal(this, 3));
	this.goalSelector.addGoal(4, new UntameGroupSwimGoal(this, 10));

	this.targetSelector.addGoal(0, new BettaAngerGoal(this));
	this.targetSelector.addGoal(1, new BettaPreyingGoal(this));

    }

    @Override
    protected void setBucketData(ItemStack bucket) {
	super.setBucketData(bucket);

	this.getCapability(TAXONOMY_CAPABILITY).ifPresent(itaxonomy -> {
	    RankInstance rank = itaxonomy.getTaxonomyInstance();

	    bucket.getCapability(TAXONOMY_CAPABILITY).ifPresent(itax -> itax.setTaxonomyInstance(rank));
	});
    }

    @Override
    protected void updateAITasks() {
	LazyOptional<IAge> age = this.getCapability(AGE_CAPABILITY);
	LazyOptional<IBreed> breed = this.getCapability(BREED_CAPABILITY);

	age.ifPresent(iage -> breed.ifPresent(ibreed -> ibreed.updateAITasks(iage)));
    }

}
