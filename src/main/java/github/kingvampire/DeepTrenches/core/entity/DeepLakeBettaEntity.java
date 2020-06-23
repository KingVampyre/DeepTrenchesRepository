package github.kingvampire.DeepTrenches.core.entity;

import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.lit.LitProvider.LIT_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.entity.DragonfishEntity.LURE_ATTRACTION;
import static github.kingvampire.DeepTrenches.api.entity.DragonfishEntity.LURE_DELAY;
import static github.kingvampire.DeepTrenches.api.entity.DragonfishEntity.LURE_MAX_LIT;
import static github.kingvampire.DeepTrenches.api.entity.DragonfishEntity.LURE_MAX_UNLIT;
import static github.kingvampire.DeepTrenches.api.entity.DragonfishEntity.LURE_MIN_LIT;
import static github.kingvampire.DeepTrenches.api.entity.DragonfishEntity.LURE_MIN_UNLIT;
import static github.kingvampire.DeepTrenches.api.entity.DragonfishEntity.MAX_LURING;
import static github.kingvampire.DeepTrenches.api.entity.DragonfishEntity.MIN_LURING;
import static github.kingvampire.DeepTrenches.api.entity.DragonfishEntity.PREY_DETECTION;
import static github.kingvampire.DeepTrenches.api.enums.LitState.ALL_LIT;
import static github.kingvampire.DeepTrenches.api.enums.LitState.ALL_UNLIT;
import static github.kingvampire.DeepTrenches.api.enums.LitState.BODY;
import static github.kingvampire.DeepTrenches.api.enums.LitState.LURE;
import static github.kingvampire.DeepTrenches.core.init.ModAttributes.MOVEMENT_SPEED_BOOST;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.DEEP_LAKE_BETTA;
import static github.kingvampire.DeepTrenches.core.init.ModItems.DEEP_LAKE_BETTA_BUCKET;
import static net.minecraft.entity.SharedMonsterAttributes.ATTACK_DAMAGE;
import static net.minecraft.entity.SharedMonsterAttributes.FOLLOW_RANGE;
import static net.minecraft.entity.SharedMonsterAttributes.MAX_HEALTH;
import static net.minecraft.entity.SharedMonsterAttributes.MOVEMENT_SPEED;

import github.kingvampire.DeepTrenches.api.capabilities.anger.IAnger;
import github.kingvampire.DeepTrenches.api.capabilities.lit.ILit;
import github.kingvampire.DeepTrenches.api.entity.goals.LureGoal;
import github.kingvampire.DeepTrenches.core.entity.controllers.DeepLakeBettaMovementController;
import github.kingvampire.DeepTrenches.core.entity.goals.betta.DeepLakeBettaChaseGoal;
import github.kingvampire.DeepTrenches.core.entity.goals.betta.DeepLakeBettaLureGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class DeepLakeBettaEntity extends BettaEntity {

    private LureGoal lureGoal;

    public DeepLakeBettaEntity(EntityType<? extends BettaEntity> type, World worldIn) {
	super(type, worldIn);

	this.moveController = new DeepLakeBettaMovementController(this);
    }

    public DeepLakeBettaEntity(FMLPlayMessages.SpawnEntity spawnEntity, World worldIn) {
	this(DEEP_LAKE_BETTA, worldIn);
    }

    @Override
    protected ItemStack getFishBucket() {
	return new ItemStack(DEEP_LAKE_BETTA_BUCKET);
    }

    public boolean isLuring() {
	return this.lureGoal.isLuring();
    }

    @Override
    public void livingTick() {
	super.livingTick();

	LazyOptional<IAnger> anger = this.getCapability(ANGER_CAPABILITY);
	LazyOptional<ILit> lit = this.getCapability(LIT_CAPABILITY);

	if (!this.world.isRemote() && anger.isPresent() && lit.isPresent()) {
	    IAnger ianger = anger.orElseThrow(IllegalArgumentException::new);
	    ILit ilit = lit.orElseThrow(IllegalArgumentException::new);

	    BlockPos pos = this.getPosition();
	    int light = this.world.getLight(pos);

	    if (ianger.isAngry())
		ilit.setLitState(ALL_LIT);

	    if (this.lureGoal.isLuring())
		ilit.setLitState(LURE);

	    else if (light >= 3 && light <= 6)
		ilit.setLitState(BODY);

	    else
		ilit.setLitState(ALL_UNLIT);

	}
    }

    @Override
    protected void registerAttributes() {
	super.registerAttributes();

	this.getAttribute(ATTACK_DAMAGE).setBaseValue(4F);
	this.getAttribute(FOLLOW_RANGE).setBaseValue(6F);
	this.getAttribute(MAX_HEALTH).setBaseValue(5F);
	this.getAttribute(MOVEMENT_SPEED).setBaseValue(1.2F);
	this.getAttribute(MOVEMENT_SPEED_BOOST).setBaseValue(3.15F);

	this.getAttributes().registerAttribute(LURE_MAX_LIT).setBaseValue(16); // 160
	this.getAttributes().registerAttribute(LURE_MIN_LIT).setBaseValue(12); // 120
	this.getAttributes().registerAttribute(LURE_MAX_UNLIT).setBaseValue(22); // 220
	this.getAttributes().registerAttribute(LURE_MIN_UNLIT).setBaseValue(20); // 200
	this.getAttributes().registerAttribute(LURE_ATTRACTION).setBaseValue(6);
	this.getAttributes().registerAttribute(LURE_DELAY).setBaseValue(650); // 1300
	this.getAttributes().registerAttribute(MAX_LURING).setBaseValue(12); // 4
	this.getAttributes().registerAttribute(MIN_LURING).setBaseValue(6); // 2

	this.getAttributes().registerAttribute(PREY_DETECTION).setBaseValue(1.6); // 4
    }

    @Override
    protected void registerGoals() {
	super.registerGoals();

	this.lureGoal = new DeepLakeBettaLureGoal(this);

	this.targetSelector.addGoal(0, new DeepLakeBettaChaseGoal(this));
    }

}
