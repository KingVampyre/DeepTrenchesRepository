package github.kingvampire.DeepTrenches.core.entity;

import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.lit.LitProvider.LIT_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.taxonomy.TaxonomyProvider.TAXONOMY_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.enums.LitState.ALL_LIT;
import static github.kingvampire.DeepTrenches.api.enums.LitState.ALL_UNLIT;
import static github.kingvampire.DeepTrenches.api.enums.LitState.FLANK;
import static github.kingvampire.DeepTrenches.api.enums.LitState.LURE;
import static github.kingvampire.DeepTrenches.api.enums.LitState.RECOGNITION;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.LIGHT_LOOSEJAW;
import static github.kingvampire.DeepTrenches.core.init.ModItems.LIGHT_LOOSEJAW_BUCKET;
import static github.kingvampire.DeepTrenches.core.init.ModTaxons.REMARKABLE_LIGHT_LOOSEJAW;
import static net.minecraft.entity.SharedMonsterAttributes.ATTACK_DAMAGE;
import static net.minecraft.entity.SharedMonsterAttributes.FOLLOW_RANGE;
import static net.minecraft.entity.SharedMonsterAttributes.MAX_HEALTH;
import static net.minecraft.entity.SharedMonsterAttributes.MOVEMENT_SPEED;

import github.kingvampire.DeepTrenches.api.capabilities.anger.IAnger;
import github.kingvampire.DeepTrenches.api.capabilities.lit.ILit;
import github.kingvampire.DeepTrenches.api.capabilities.taxonomy.ITaxonomy;
import github.kingvampire.DeepTrenches.api.entity.TamableDragonfishEntity;
import github.kingvampire.DeepTrenches.api.entity.goals.AngerGoal;
import github.kingvampire.DeepTrenches.api.entity.goals.AngryAttackGoal;
import github.kingvampire.DeepTrenches.api.taxonomy.SubspeciesTaxon;
import github.kingvampire.DeepTrenches.core.entity.goals.dragonfish.DragonfishChaseGoal;
import github.kingvampire.DeepTrenches.core.entity.goals.loosejaw.LoosejawPreyingGoal;
import github.kingvampire.DeepTrenches.core.init.ModAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.fish.CodEntity;
import net.minecraft.entity.passive.fish.SalmonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class LightLoosejawEntity extends TamableDragonfishEntity {

    public LightLoosejawEntity(EntityType<? extends LightLoosejawEntity> type, World worldIn) {
	super(type, worldIn);
    }

    public LightLoosejawEntity(FMLPlayMessages.SpawnEntity spawnEntity, World worldIn) {
	this(LIGHT_LOOSEJAW, worldIn);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
	return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected SoundEvent getAmbientSound() {
	// TODO ambient sound
	return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
	// TODO death sound
	return null;
    }

    @Override
    protected ItemStack getFishBucket() {
	return new ItemStack(LIGHT_LOOSEJAW_BUCKET);
    }

    @Override
    protected SoundEvent getFlopSound() {
	// TODO flop sound
	return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
	// TODO hurt sound
	return null;
    }

    @Override
    protected void registerAttributes() {
	super.registerAttributes();

	this.getAttribute(ATTACK_DAMAGE).setBaseValue(7F);
	this.getAttribute(FOLLOW_RANGE).setBaseValue(8); // 16
	this.getAttribute(MAX_HEALTH).setBaseValue(16F);
	this.getAttribute(MOVEMENT_SPEED).setBaseValue(1.31F);
	this.getAttribute(ModAttributes.MOVEMENT_SPEED_BOOST).setBaseValue(2.68F); // 2.68

	LazyOptional<ITaxonomy> taxonomy = this.getCapability(TAXONOMY_CAPABILITY);

	if (taxonomy.isPresent()) {
	    ITaxonomy itaxonomy = taxonomy.orElseThrow(IllegalArgumentException::new);
	    SubspeciesTaxon subspecies = itaxonomy.getSubspecies();

	    this.getAttribute(BLINK_INTERVAL).setBaseValue(subspecies == REMARKABLE_LIGHT_LOOSEJAW ? 2 : 3);
	    this.getAttribute(BLINK_RANGE).setBaseValue(subspecies == REMARKABLE_LIGHT_LOOSEJAW ? 12 : 5);
	    this.getAttribute(BLINKING_DELAY).setBaseValue(subspecies == REMARKABLE_LIGHT_LOOSEJAW ? 70 : 115);
	    this.getAttribute(MAX_BLINKS).setBaseValue(subspecies == REMARKABLE_LIGHT_LOOSEJAW ? 16 : 4);
	    this.getAttribute(MIN_BLINKS).setBaseValue(subspecies == REMARKABLE_LIGHT_LOOSEJAW ? 7 : 2);
	}

	this.getAttribute(LURE_MAX_LIT).setBaseValue(16); // 160
	this.getAttribute(LURE_MIN_LIT).setBaseValue(12); // 120
	this.getAttribute(LURE_MAX_UNLIT).setBaseValue(22); // 220
	this.getAttribute(LURE_MIN_UNLIT).setBaseValue(20); // 200
	this.getAttribute(LURE_ATTRACTION).setBaseValue(10);
	this.getAttribute(LURE_DELAY).setBaseValue(650); // 1300
	this.getAttribute(MAX_LURING).setBaseValue(12); // 4
	this.getAttribute(MIN_LURING).setBaseValue(6); // 2

	this.getAttribute(DROP_TOOTH_CHANCE).setBaseValue(0.53F);
	this.getAttribute(PREY_DETECTION).setBaseValue(1.6); // 4
    }

    @Override
    protected void registerGoals() {
	super.registerGoals();

	this.goalSelector.addGoal(0, new AngryAttackGoal(this));

	this.targetSelector.addGoal(0, new AngerGoal(this));
	this.targetSelector.addGoal(0, new DragonfishChaseGoal(this));
	this.targetSelector.addGoal(1, new LoosejawPreyingGoal<CodEntity>(this, CodEntity.class));
	this.targetSelector.addGoal(1, new LoosejawPreyingGoal<SalmonEntity>(this, SalmonEntity.class));
    }

    @Override
    protected void updateLitState() {
	LazyOptional<IAnger> anger = this.getCapability(ANGER_CAPABILITY);
	LazyOptional<ILit> lit = this.getCapability(LIT_CAPABILITY);
	LazyOptional<ITaxonomy> taxonomy = this.getCapability(TAXONOMY_CAPABILITY);

	if (!this.world.isRemote() && anger.isPresent() && lit.isPresent() && taxonomy.isPresent()) {

	    IAnger ianger = anger.orElseThrow(IllegalArgumentException::new);
	    ILit ilit = lit.orElseThrow(IllegalArgumentException::new);
	    ITaxonomy itaxonomy = taxonomy.orElseThrow(IllegalArgumentException::new);

	    BlockPos pos = this.getPosition();
	    int light = this.world.getLight(pos);

	    if (ianger.isAngry())
		ilit.setLitState(ALL_LIT);

	    else if (light >= 3 && light <= 6)

		if (this.lureGoal.shouldLure())
		    ilit.setLitState(ALL_LIT);
		else
		    ilit.setLitState(FLANK);

	    else if (this.blinkGoal.shouldBlink() || this.respondBlinkGoal.shouldBlink())

		if (itaxonomy.getSubspecies() == REMARKABLE_LIGHT_LOOSEJAW)
		    ilit.setLitState(RECOGNITION);
		else
		    ilit.setLitState(LURE);

	    else if (this.lureGoal.shouldLure())
		ilit.setLitState(LURE);
	    else
		ilit.setLitState(ALL_UNLIT);

	}
    }

}
