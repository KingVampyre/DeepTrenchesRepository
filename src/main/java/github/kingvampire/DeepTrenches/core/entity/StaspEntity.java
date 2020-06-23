package github.kingvampire.DeepTrenches.core.entity;

import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.pollen.PollenProvider.POLLEN_CAPABILITY;
import static github.kingvampire.DeepTrenches.core.init.ModAttributes.MOVEMENT_SPEED_BOOST;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.STASP;
import static net.minecraft.entity.SharedMonsterAttributes.ARMOR;
import static net.minecraft.entity.SharedMonsterAttributes.ATTACK_DAMAGE;
import static net.minecraft.entity.SharedMonsterAttributes.FLYING_SPEED;
import static net.minecraft.entity.SharedMonsterAttributes.FOLLOW_RANGE;
import static net.minecraft.entity.SharedMonsterAttributes.MAX_HEALTH;
import static net.minecraft.network.datasync.DataSerializers.BOOLEAN;
import static net.minecraft.pathfinding.PathNodeType.WATER;
import static net.minecraft.util.math.Vec3d.ZERO;

import github.kingvampire.DeepTrenches.api.capabilities.anger.IAnger;
import github.kingvampire.DeepTrenches.api.capabilities.pollen.IPollen;
import github.kingvampire.DeepTrenches.api.entity.goals.AngryAttackGoal;
import github.kingvampire.DeepTrenches.api.entity.goals.ChargeAttackGoal;
import github.kingvampire.DeepTrenches.core.entity.controllers.StaspLookController;
import github.kingvampire.DeepTrenches.core.entity.controllers.StaspMovementController;
import github.kingvampire.DeepTrenches.core.entity.goals.stasp.ExtractAqueanSapGoal;
import github.kingvampire.DeepTrenches.core.entity.goals.stasp.NestGoal;
import github.kingvampire.DeepTrenches.core.entity.goals.stasp.PlantSaplingGoal;
import github.kingvampire.DeepTrenches.core.entity.goals.stasp.PollinateFlowerGoal;
import github.kingvampire.DeepTrenches.core.entity.goals.stasp.PollinateSaplingGoal;
import github.kingvampire.DeepTrenches.core.entity.goals.stasp.StaspAngerGoal;
import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class StaspEntity extends CreatureEntity {

    private static final DataParameter<Boolean> HANGING = EntityDataManager.createKey(StaspEntity.class, BOOLEAN);

    public StaspEntity(EntityType<StaspEntity> type, World worldIn) {
	super(type, worldIn);

	this.lookController = new StaspLookController(this);
	this.moveController = new StaspMovementController(this);

	this.setPathPriority(WATER, -1F);
    }

    public StaspEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
	this(STASP, world);
    }

    @Override
    protected PathNavigator createNavigator(World worldIn) {
	return new FlyingPathNavigator(this, worldIn);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
	return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void fall(float distance, float damageMultiplier) {

    }

    @Override
    public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
	return worldIn.getBlockState(pos).isAir(worldIn, pos) ? 30F : 0F;
    }

    public boolean getIsHanging() {
	return this.dataManager.get(HANGING);
    }

    @Override
    public void livingTick() {
	super.livingTick();

	LazyOptional<IAnger> anger = this.getCapability(ANGER_CAPABILITY);
	LazyOptional<IPollen> pollen = this.getCapability(POLLEN_CAPABILITY);

	if (anger.isPresent() && pollen.isPresent()) {
	    IAnger ianger = anger.orElseThrow(IllegalArgumentException::new);
	    IPollen ipollen = pollen.orElseThrow(IllegalArgumentException::new);

	    int ticks = ipollen.getTicksSincePollination();

	    ianger.livingTick();
	    ipollen.setTicksSincePollination(++ticks);

	    if (!this.world.isRemote()) {
		ianger.sendPacket(this);
		ipollen.sendPacket(this);
	    }
	}

	if (this.getIsHanging())
	    this.setMotion(ZERO);

    }

    @Override
    public void readAdditional(CompoundNBT compound) {
	super.readAdditional(compound);

	this.setIsHanging(compound.getBoolean("Hanging"));
    }

    @Override
    protected void registerAttributes() {
	super.registerAttributes();

	this.getAttribute(ARMOR).setBaseValue(5F);
	this.getAttribute(FOLLOW_RANGE).setBaseValue(48F);
	this.getAttribute(MAX_HEALTH).setBaseValue(10F);

	this.getAttributes().registerAttribute(ATTACK_DAMAGE).setBaseValue(3F);
	this.getAttributes().registerAttribute(FLYING_SPEED).setBaseValue(0.175F);
	this.getAttributes().registerAttribute(MOVEMENT_SPEED_BOOST).setBaseValue(0.2F);
    }

    @Override
    protected void registerData() {
	super.registerData();

	this.dataManager.register(HANGING, false);
    }

    @Override
    protected void registerGoals() {
	this.goalSelector.addGoal(0, new AngryAttackGoal(this));
	this.goalSelector.addGoal(1, new ChargeAttackGoal(this, 5, 10));
	this.goalSelector.addGoal(6, new NestGoal(this, 32));
	this.goalSelector.addGoal(4, new PollinateFlowerGoal(this, 16, 32, 0.25F));
	this.goalSelector.addGoal(4, new ExtractAqueanSapGoal(this, 16, 32, 0.35));
	this.goalSelector.addGoal(5, new PollinateSaplingGoal(this, 32, 10, 0.65));
	this.goalSelector.addGoal(5, new PlantSaplingGoal(this, 32, 10, 0.12));
	this.goalSelector.addGoal(7, new WaterAvoidingRandomFlyingGoal(this, 0.85F));

	this.targetSelector.addGoal(0, new StaspAngerGoal(this));
    }

    public void setIsHanging(boolean isHanging) {
	this.dataManager.set(HANGING, isHanging);
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {

    }

    public void writeAdditional(CompoundNBT compound) {
	super.writeAdditional(compound);

	compound.putBoolean("Hanging", this.getIsHanging());
    }

}
