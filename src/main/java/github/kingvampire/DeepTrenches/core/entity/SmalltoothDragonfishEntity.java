package github.kingvampire.DeepTrenches.core.entity;

import static github.kingvampire.DeepTrenches.core.init.ModEntities.SMALLTOOTH_DRAGONFISH;
import static github.kingvampire.DeepTrenches.core.init.ModItems.SMALLTOOTH_DRAGONFISH_BUCKET;
import static net.minecraft.entity.SharedMonsterAttributes.ATTACK_DAMAGE;
import static net.minecraft.entity.SharedMonsterAttributes.FOLLOW_RANGE;
import static net.minecraft.entity.SharedMonsterAttributes.MAX_HEALTH;
import static net.minecraft.entity.SharedMonsterAttributes.MOVEMENT_SPEED;
import static net.minecraft.entity.ai.attributes.AttributeModifier.Operation.MULTIPLY_BASE;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class SmalltoothDragonfishEntity extends BlackLoosejawEntity {

    public static final AttributeModifier NEG_SUBORBITAL_BOOST = new AttributeModifier("smalltoothDragonfish.negSuborbitalBoost", 0.55, MULTIPLY_BASE);
    public static final AttributeModifier SUBORBITAL_BOOST = new AttributeModifier("smalltoothDragonfish.suborbitalBoost", 0.45, MULTIPLY_BASE);

    public SmalltoothDragonfishEntity(EntityType<? extends SmalltoothDragonfishEntity> type, World worldIn) {
	super(type, worldIn);
    }

    public SmalltoothDragonfishEntity(FMLPlayMessages.SpawnEntity spawnEntity, World worldIn) {
	this(SMALLTOOTH_DRAGONFISH, worldIn);
    }

    @Override
    protected ItemStack getFishBucket() {
	return new ItemStack(SMALLTOOTH_DRAGONFISH_BUCKET);
    }

    @Override
    public AttributeModifier getNegativeSuborbitalBoost() {
	return NEG_SUBORBITAL_BOOST;
    }

    @Override
    public AttributeModifier getSuborbitalBoost() {
	return SUBORBITAL_BOOST;
    }

    @Override
    protected void registerAttributes() {
	super.registerAttributes();

	this.getAttribute(ATTACK_DAMAGE).setBaseValue(4);
	this.getAttribute(FOLLOW_RANGE).setBaseValue(6); // 16
	this.getAttribute(MAX_HEALTH).setBaseValue(18);
	this.getAttribute(MOVEMENT_SPEED).setBaseValue(1.24F);
	this.getAttribute(MOVEMENT_SPEED_BOOST).setBaseValue(2.58F);

	this.getAttribute(BLINK_INTERVAL).setBaseValue(3);
	this.getAttribute(BLINK_RANGE).setBaseValue(12);
	this.getAttribute(BLINKING_DELAY).setBaseValue(120);
	this.getAttribute(MAX_BLINKS).setBaseValue(9);
	this.getAttribute(MIN_BLINKS).setBaseValue(3);

	this.getAttribute(LURE_MAX_LIT).setBaseValue(24); // 240
	this.getAttribute(LURE_MIN_LIT).setBaseValue(14); // 140
	this.getAttribute(LURE_MAX_UNLIT).setBaseValue(8); // 80
	this.getAttribute(LURE_MIN_UNLIT).setBaseValue(7); // 70
	this.getAttribute(LURE_ATTRACTION).setBaseValue(8);
	this.getAttribute(LURE_DELAY).setBaseValue(720);
	this.getAttribute(MAX_LURING).setBaseValue(2); // 6
	this.getAttribute(MIN_LURING).setBaseValue(1); // 3

	this.getAttribute(PREY_DETECTION).setBaseValue(1.25F); // 3

	this.getAttribute(SUBORBITAL_DELAY).setBaseValue(270);
	this.getAttribute(SUBORBITAL_MAX_LIT).setBaseValue(100);
	this.getAttribute(SUBORBITAL_MIN_LIT).setBaseValue(20);
    }

}
