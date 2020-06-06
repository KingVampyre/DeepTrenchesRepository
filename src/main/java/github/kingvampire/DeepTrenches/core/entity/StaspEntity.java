package github.kingvampire.DeepTrenches.core.entity;

import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.entity.HatchetfishEntity.MOVEMENT_SPEED_BOOST;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.STASP;
import static net.minecraft.entity.SharedMonsterAttributes.ARMOR;
import static net.minecraft.entity.SharedMonsterAttributes.ATTACK_DAMAGE;
import static net.minecraft.entity.SharedMonsterAttributes.FLYING_SPEED;
import static net.minecraft.entity.SharedMonsterAttributes.FOLLOW_RANGE;
import static net.minecraft.entity.SharedMonsterAttributes.MAX_HEALTH;
import static net.minecraft.network.datasync.DataSerializers.VARINT;

import github.kingvampire.DeepTrenches.api.entity.goals.AngryAttackGoal;
import github.kingvampire.DeepTrenches.core.entity.controllers.StaspLookController;
import github.kingvampire.DeepTrenches.core.entity.controllers.StaspMovementController;
import github.kingvampire.DeepTrenches.core.entity.goals.stasp.ChargeAttackGoal;
import github.kingvampire.DeepTrenches.core.entity.goals.stasp.EnterNestGoal;
import github.kingvampire.DeepTrenches.core.entity.goals.stasp.ExtractAqueanSapGoal;
import github.kingvampire.DeepTrenches.core.entity.goals.stasp.FindFlowersGoal;
import github.kingvampire.DeepTrenches.core.entity.goals.stasp.FindLogGoal;
import github.kingvampire.DeepTrenches.core.entity.goals.stasp.FindMosoilGoal;
import github.kingvampire.DeepTrenches.core.entity.goals.stasp.FindNestGoal;
import github.kingvampire.DeepTrenches.core.entity.goals.stasp.FindSaplingGoal;
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
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class StaspEntity extends CreatureEntity {

    private static final DataParameter<Integer> AQUEAN_SAP = EntityDataManager.createKey(StaspEntity.class, VARINT);
    private static final DataParameter<Integer> POLLEN = EntityDataManager.createKey(StaspEntity.class, VARINT);
    private static final DataParameter<Integer> WING_COLOR = EntityDataManager.createKey(StaspEntity.class, VARINT);

    private int ticksSincePollination;

    public StaspEntity(EntityType<StaspEntity> type, World worldIn) {
	super(type, worldIn);

	this.lookController = new StaspLookController(this);
	this.moveController = new StaspMovementController(this);

	this.setPathPriority(PathNodeType.WATER, -1.0F);
    }

    public StaspEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
	this(STASP, world);
    }

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

    public int getAqueanSap() {
	return this.dataManager.get(AQUEAN_SAP);
    }

    @Override
    public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
	return worldIn.getBlockState(pos).isAir(worldIn, pos) ? 30F : 0F;
    }

    public int getPollen() {
	return this.dataManager.get(POLLEN);
    }

    public int getTicksSincePollination() {
	return this.ticksSincePollination;
    }

    public int getWingColor() {
	return this.dataManager.get(WING_COLOR);
    }

    public boolean hasAqueanSap() {
	return this.getAqueanSap() > 0;
    }

    public boolean hasPollen() {
	return this.getPollen() > 0;
    }

    @Override
    public void livingTick() {
	super.livingTick();

	if (!this.world.isRemote()) {
	    int ticks = this.getTicksSincePollination();

	    this.setTicksSincePollination(++ticks);
	}

	this.getCapability(ANGER_CAPABILITY).ifPresent(ianger -> ianger.livingTick());
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
	super.readAdditional(compound);

	this.setAqueanSap(compound.getInt("AqueanSap"));
	this.setPollen(compound.getInt("Pollen"));
	this.setWingColor(compound.getInt("WingColor"));

	this.setTicksSincePollination(compound.getInt("TicksSincePollination"));
    }

    @Override
    protected void registerAttributes() {
	super.registerAttributes();

	this.getAttribute(ARMOR).setBaseValue(5F);
	this.getAttribute(FOLLOW_RANGE).setBaseValue(48F);
	this.getAttribute(MAX_HEALTH).setBaseValue(10F);

	this.getAttributes().registerAttribute(ATTACK_DAMAGE).setBaseValue(3F);
	this.getAttributes().registerAttribute(FLYING_SPEED).setBaseValue(0.4F);
	this.getAttributes().registerAttribute(MOVEMENT_SPEED_BOOST).setBaseValue(0.2F);
    }

    @Override
    protected void registerData() {
	super.registerData();

	this.dataManager.register(AQUEAN_SAP, 0);
	this.dataManager.register(POLLEN, 0);
	this.dataManager.register(WING_COLOR, 0);
    }

    @Override
    protected void registerGoals() {
	FindFlowersGoal findFlowersGoal = new FindFlowersGoal(this, 0.1F, 16, 32);
	FindSaplingGoal findSaplingGoal = new FindSaplingGoal(this, 0.1F, 16, 32);
	FindLogGoal findLogGoal = new FindLogGoal(this, 0.1F, 16, 32);
	FindMosoilGoal findMosoilGoal = new FindMosoilGoal(this, 0.1F, 16, 32);

	this.goalSelector.addGoal(0, new AngryAttackGoal(this, false));
	this.goalSelector.addGoal(1, new ChargeAttackGoal(this, 0.3F));
	this.goalSelector.addGoal(2, new EnterNestGoal(this));
	this.goalSelector.addGoal(3, findLogGoal);
	this.goalSelector.addGoal(3, findFlowersGoal);
	this.goalSelector.addGoal(3, findSaplingGoal);
	this.goalSelector.addGoal(3, findMosoilGoal);
	this.goalSelector.addGoal(4, new PollinateFlowerGoal(this, findFlowersGoal));
	this.goalSelector.addGoal(4, new ExtractAqueanSapGoal(this, findLogGoal));
	this.goalSelector.addGoal(5, new PollinateSaplingGoal(this, findSaplingGoal));
	this.goalSelector.addGoal(5, new PlantSaplingGoal(this, findMosoilGoal));
	this.goalSelector.addGoal(6, new FindNestGoal(this, 0.1F, 48, 32));
	this.goalSelector.addGoal(7, new WaterAvoidingRandomFlyingGoal(this, 0.1F));

	this.targetSelector.addGoal(0, new StaspAngerGoal(this));

    }

    public void setAqueanSap(int aqueanSap) {
	this.dataManager.set(AQUEAN_SAP, aqueanSap);
    }

    public void setPollen(int pollen) {
	this.dataManager.set(POLLEN, pollen);
    }

    public void setTicksSincePollination(int ticksSincePollination) {
	this.ticksSincePollination = ticksSincePollination;
    }

    public void setWingColor(int color) {
	this.dataManager.set(WING_COLOR, color);
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {

    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
	super.writeAdditional(compound);

	compound.putInt("AqueanSap", this.getAqueanSap());
	compound.putInt("Pollen", this.getPollen());
	compound.putInt("WingColor", this.getWingColor());

	compound.putInt("TicksSincePollination", this.ticksSincePollination);
    }

}
