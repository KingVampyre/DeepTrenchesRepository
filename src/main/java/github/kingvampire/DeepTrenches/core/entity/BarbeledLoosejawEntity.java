package github.kingvampire.DeepTrenches.core.entity;

import static github.kingvampire.DeepTrenches.api.capabilities.lit.LitProvider.LIT_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.enums.LitState.ALL_LIT;
import static github.kingvampire.DeepTrenches.api.enums.LitState.ALL_UNLIT;
import static github.kingvampire.DeepTrenches.api.enums.LitState.FLANK;
import static github.kingvampire.DeepTrenches.api.enums.LitState.FLANK_AND_LURE;
import static github.kingvampire.DeepTrenches.api.enums.LitState.FLANK_AND_SUBORBITAL;
import static github.kingvampire.DeepTrenches.api.enums.LitState.LURE;
import static github.kingvampire.DeepTrenches.api.enums.LitState.SUBORBITAL;
import static github.kingvampire.DeepTrenches.api.enums.LitState.SUBORBITAL_AND_LURE;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.BARBELED_LOOSEJAW;
import static github.kingvampire.DeepTrenches.core.init.ModItems.BARBELED_LOOSEJAW_BUCKET;
import static net.minecraft.entity.SharedMonsterAttributes.ATTACK_DAMAGE;
import static net.minecraft.entity.SharedMonsterAttributes.FOLLOW_RANGE;
import static net.minecraft.entity.SharedMonsterAttributes.MAX_HEALTH;
import static net.minecraft.entity.SharedMonsterAttributes.MOVEMENT_SPEED;
import static net.minecraft.entity.ai.attributes.AttributeModifier.Operation.MULTIPLY_BASE;

import github.kingvampire.DeepTrenches.api.capabilities.lit.ILit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class BarbeledLoosejawEntity extends LightLoosejawEntity {

    public static final IAttribute SUBORBITAL_DELAY = new RangedAttribute(null, "suborbital.delay", 100, 1, 1000);
    public static final IAttribute SUBORBITAL_MAX_LIT = new RangedAttribute(null, "suborbital.maxLit", 1, 1, 200);
    public static final IAttribute SUBORBITAL_MIN_LIT = new RangedAttribute(null, "suborbital.minLit", 1, 1, 100);

    public static final AttributeModifier NEG_SUBORBITAL_BOOST = new AttributeModifier("barbeledLoosejaw.negSuborbitalBoost", 0.75, MULTIPLY_BASE);
    public static final AttributeModifier SUBORBITAL_BOOST = new AttributeModifier("barbeledLoosejaw.suborbitalBoost", 0.25, MULTIPLY_BASE);

    private int suborbitalDelay;
    private int suborbitalLit;

    public BarbeledLoosejawEntity(EntityType<? extends BarbeledLoosejawEntity> type, World worldIn) {
	super(type, worldIn);
    }

    public BarbeledLoosejawEntity(FMLPlayMessages.SpawnEntity spawnEntity, World worldIn) {
	this(BARBELED_LOOSEJAW, worldIn);
    }

    @Override
    protected ItemStack getFishBucket() {
	return new ItemStack(BARBELED_LOOSEJAW_BUCKET);
    }

    public int getSuborbitalDelay() {
	return suborbitalDelay;
    }

    public int getSuborbitalLit() {
	return suborbitalLit;
    }

    public AttributeModifier getSuborbitalBoost() {
	return SUBORBITAL_BOOST;

    }

    public AttributeModifier getNegativeSuborbitalBoost() {
	return NEG_SUBORBITAL_BOOST;
    }

    @Override
    public void livingTick() {
	super.livingTick();

	int maxLit = (int) this.getAttribute(SUBORBITAL_MAX_LIT).getBaseValue();
	int minLit = (int) this.getAttribute(SUBORBITAL_MIN_LIT).getBaseValue();

	if (!this.world.isRemote()) {

	    if (this.suborbitalDelay > 0) {
		this.suborbitalDelay--;

		if (this.suborbitalDelay == 0) {
		    this.suborbitalLit = minLit + this.rand.nextInt(maxLit - minLit + 1);

		    this.getAttribute(FOLLOW_RANGE).applyModifier(this.getSuborbitalBoost());
		    this.getAttribute(PREY_DETECTION).applyModifier(this.getNegativeSuborbitalBoost());
		}

	    } else if (this.suborbitalLit > 0) {
		this.suborbitalLit--;

		if (this.suborbitalLit == 0) {
		    this.suborbitalDelay = (int) this.getAttribute(SUBORBITAL_DELAY).getBaseValue();

		    this.getAttribute(FOLLOW_RANGE).removeModifier(this.getSuborbitalBoost());
		    this.getAttribute(PREY_DETECTION).removeModifier(this.getNegativeSuborbitalBoost());
		}

	    }

	}

    }

    @Override
    public void onAddedToWorld() {
	super.onAddedToWorld();

	int maxLit = (int) this.getAttribute(SUBORBITAL_MAX_LIT).getBaseValue();
	int minLit = (int) this.getAttribute(SUBORBITAL_MIN_LIT).getBaseValue();

	this.suborbitalLit = minLit + this.rand.nextInt(maxLit - minLit + 1);

	this.getAttribute(FOLLOW_RANGE).applyModifier(this.getSuborbitalBoost());
	this.getAttribute(PREY_DETECTION).applyModifier(this.getNegativeSuborbitalBoost());
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
	super.readAdditional(compound);

	this.setSuborbitalDelay(compound.getInt("SuborbitalDelay"));
	this.setSuborbitalLit(compound.getInt("SuborbitalLit"));
    }

    @Override
    protected void registerAttributes() {
	super.registerAttributes();

	this.getAttribute(ATTACK_DAMAGE).setBaseValue(6);
	this.getAttribute(FOLLOW_RANGE).setBaseValue(4); // 16
	this.getAttribute(MAX_HEALTH).setBaseValue(16);
	this.getAttribute(MOVEMENT_SPEED).setBaseValue(1.24F);
	this.getAttribute(MOVEMENT_SPEED_BOOST).setBaseValue(2.58F);

	this.getAttribute(BLINK_INTERVAL).setBaseValue(6); // 3
	this.getAttribute(BLINK_RANGE).setBaseValue(12);
	this.getAttribute(BLINKING_DELAY).setBaseValue(440); // 60
	this.getAttribute(MAX_BLINKS).setBaseValue(9);
	this.getAttribute(MIN_BLINKS).setBaseValue(3);

	this.getAttribute(LURE_MAX_LIT).setBaseValue(20); // 200
	this.getAttribute(LURE_MIN_LIT).setBaseValue(12); // 120
	this.getAttribute(LURE_MAX_UNLIT).setBaseValue(7); // 70
	this.getAttribute(LURE_MIN_UNLIT).setBaseValue(6); // 60
	this.getAttribute(LURE_ATTRACTION).setBaseValue(16);
	this.getAttribute(LURE_DELAY).setBaseValue(640);
	this.getAttribute(MAX_LURING).setBaseValue(18); // 6
	this.getAttribute(MIN_LURING).setBaseValue(9); // 3
	this.getAttribute(PREY_DETECTION).setBaseValue(1.25F); // 2

//	this.getAttributes().registerAttribute(SUBORBITAL_BOOST).setBaseValue(0.25);
	this.getAttributes().registerAttribute(SUBORBITAL_DELAY).setBaseValue(230);
	this.getAttributes().registerAttribute(SUBORBITAL_MAX_LIT).setBaseValue(140);
	this.getAttributes().registerAttribute(SUBORBITAL_MIN_LIT).setBaseValue(40);
    }

    public void setSuborbitalDelay(int suborbitalDelay) {
	this.suborbitalDelay = suborbitalDelay;
    }

    public void setSuborbitalLit(int suborbitalLit) {
	this.suborbitalLit = suborbitalLit;
    }

    @Override
    protected void updateLitState() {

	LazyOptional<ILit> lit = this.getCapability(LIT_CAPABILITY);

	if (!this.world.isRemote() && lit.isPresent()) {
	    ILit ilit = lit.orElseThrow(IllegalArgumentException::new);

	    LivingEntity entity = this.getAttackingEntity();
	    BlockPos pos = this.getPosition();

	    int light = this.world.getLight(pos);

	    if (light >= 3 && light <= 6) {

		if (this.lureGoal.shouldLure())
		    ilit.setLitState(FLANK_AND_LURE);

		else if (this.suborbitalLit > 0)
		    ilit.setLitState(FLANK_AND_SUBORBITAL);

		else if (this.blinkGoal.shouldBlink() || this.respondBlinkGoal.shouldBlink())
		    ilit.setLitState(FLANK_AND_SUBORBITAL);

		else
		    ilit.setLitState(FLANK);

	    } else if (this.blinkGoal.isBlinking() || this.respondBlinkGoal.isBlinking()) {

		if (this.blinkGoal.shouldBlink() || this.respondBlinkGoal.shouldBlink())
		    if (this.lureGoal.shouldLure())
			ilit.setLitState(SUBORBITAL_AND_LURE);
		    else
			ilit.setLitState(SUBORBITAL);

		else if (this.lureGoal.shouldLure())
		    ilit.setLitState(LURE);
		else
		    ilit.setLitState(ALL_UNLIT);

	    } else if (this.suborbitalLit > 0) {

		if (this.lureGoal.shouldLure())
		    ilit.setLitState(SUBORBITAL_AND_LURE);
		else
		    ilit.setLitState(SUBORBITAL);

	    } else if (this.lureGoal.shouldLure())
		ilit.setLitState(LURE);
	    else if (entity != null)
		ilit.setLitState(ALL_LIT);
	    else
		ilit.setLitState(ALL_UNLIT);

	    ilit.sendPacket(this);
	}
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
	super.writeAdditional(compound);

	compound.putInt("SuborbitalDelay", this.getSuborbitalDelay());
	compound.putInt("SuborbitalLit", this.getSuborbitalLit());
    }

}
