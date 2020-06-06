package github.kingvampire.DeepTrenches.api.entity;

import static net.minecraft.entity.SharedMonsterAttributes.ATTACK_DAMAGE;

import github.kingvampire.DeepTrenches.api.entity.goals.AvoidPlayerGoal;
import github.kingvampire.DeepTrenches.api.entity.goals.GroupPanicGoal;
import github.kingvampire.DeepTrenches.api.entity.goals.UntameGroupSwimGoal;
import github.kingvampire.DeepTrenches.core.entity.controllers.DragonfishLookController;
import github.kingvampire.DeepTrenches.core.entity.controllers.DragonfishMovementController;
import github.kingvampire.DeepTrenches.core.entity.goals.dragonfish.BlinkGoal;
import github.kingvampire.DeepTrenches.core.entity.goals.dragonfish.DragonfishLureGoal;
import github.kingvampire.DeepTrenches.core.entity.goals.dragonfish.RespondBlinkGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class DragonfishEntity extends HatchetfishEntity {

    public static final IAttribute BLINK_INTERVAL = new RangedAttribute(null, "blink.interval", 10, 1, 16);
    public static final IAttribute BLINK_RANGE = new RangedAttribute(null, "blink.range", 14, 1, 16);
    public static final IAttribute BLINKING_DELAY = new RangedAttribute(null, "blink.delay", 100, 1, 1000);
    public static final IAttribute LURE_ATTRACTION = new RangedAttribute(null, "lure.attraction", 14, 1, 16);
    public static final IAttribute LURE_DELAY = new RangedAttribute(null, "lure.delay", 100, 1, 1000);

    public static final IAttribute LURE_MAX_LIT = new RangedAttribute(null, "lure.maxLit", 10, 1, 1000);
    public static final IAttribute LURE_MAX_UNLIT = new RangedAttribute(null, "lure.maxUnlit", 10, 1, 1000);
    public static final IAttribute LURE_MIN_LIT = new RangedAttribute(null, "lure.minLit", 10, 1, 1000);
    public static final IAttribute LURE_MIN_UNLIT = new RangedAttribute(null, "lure.minUnlit", 10, 1, 1000);
    public static final IAttribute MAX_BLINKS = new RangedAttribute(null, "blink.maxBlinks", 8, 1, 16);
    public static final IAttribute MAX_LURING = new RangedAttribute(null, "lure.maxLuring", 8, 1, 16);
    public static final IAttribute MIN_BLINKS = new RangedAttribute(null, "blink.minBlinks", 14, 1, 16);
    public static final IAttribute MIN_LURING = new RangedAttribute(null, "lure.minLuring", 14, 1, 16);
    public static final IAttribute PREY_DETECTION = new RangedAttribute(null, "preyDetection", 5, 1, 16);

    protected BlinkGoal blinkGoal;
    protected LureGoal lureGoal;
    protected RespondBlinkGoal respondBlinkGoal;

    public DragonfishEntity(EntityType<? extends DragonfishEntity> type, World worldIn) {
	super(type, worldIn);

	this.moveController = new DragonfishMovementController(this);
	this.lookController = new DragonfishLookController(this);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
	return NetworkHooks.getEntitySpawningPacket(this);
    }

    public BlinkGoal getBlinkGoal() {
	return this.blinkGoal;
    }

    public LureGoal getLureGoal() {
	return this.lureGoal;
    }

    public RespondBlinkGoal getRespondingBlinkGoal() {
	return this.respondBlinkGoal;
    }

    public boolean isBlinking() {
	return this.blinkGoal.isBlinking();
    }

    public boolean isLuring() {
	return this.lureGoal.isLuring();
    }

    public boolean isRespondingBlink() {
	return this.respondBlinkGoal.isBlinking();
    }

    @Override
    protected void registerAttributes() {
	super.registerAttributes();

	this.getAttributes().registerAttribute(ATTACK_DAMAGE);

	this.getAttributes().registerAttribute(BLINK_INTERVAL);
	this.getAttributes().registerAttribute(BLINK_RANGE);
	this.getAttributes().registerAttribute(BLINKING_DELAY);
	this.getAttributes().registerAttribute(MAX_BLINKS);
	this.getAttributes().registerAttribute(MIN_BLINKS);

	this.getAttributes().registerAttribute(LURE_MAX_LIT);
	this.getAttributes().registerAttribute(LURE_MIN_LIT);
	this.getAttributes().registerAttribute(LURE_MAX_UNLIT);
	this.getAttributes().registerAttribute(LURE_MIN_UNLIT);
	this.getAttributes().registerAttribute(LURE_ATTRACTION);
	this.getAttributes().registerAttribute(LURE_DELAY);
	this.getAttributes().registerAttribute(MAX_LURING);
	this.getAttributes().registerAttribute(MIN_LURING);
	this.getAttributes().registerAttribute(PREY_DETECTION);

	this.getAttribute(RANDOM_SWIM_CHANCE).setBaseValue(35);
    }

    @Override
    protected void registerGoals() {
	this.blinkGoal = new BlinkGoal(this);
	this.lureGoal = new DragonfishLureGoal(this);
	this.respondBlinkGoal = new RespondBlinkGoal(this);

	this.goalSelector.addGoal(0, new GroupPanicGoal(this));
	this.goalSelector.addGoal(2, new AvoidPlayerGoal(this, 8F));
	this.goalSelector.addGoal(3, this.respondBlinkGoal);
	this.goalSelector.addGoal(3, this.blinkGoal);
	this.goalSelector.addGoal(5, new UntameGroupSwimGoal(this));

	this.targetSelector.addGoal(1, this.lureGoal);
    }

}
