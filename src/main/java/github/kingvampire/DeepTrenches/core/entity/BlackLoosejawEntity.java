package github.kingvampire.DeepTrenches.core.entity;

import static github.kingvampire.DeepTrenches.core.init.ModEntities.BLACK_LOOSEJAW;
import static github.kingvampire.DeepTrenches.core.init.ModItems.BLACK_LOOSEJAW_BUCKET;
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

public class BlackLoosejawEntity extends BarbeledLoosejawEntity {

    public static final AttributeModifier SUBORBITAL_BOOST = new AttributeModifier("blackLoosejaw.suborbitalBoost", 0.5, MULTIPLY_BASE);

    public BlackLoosejawEntity(EntityType<? extends BlackLoosejawEntity> type, World worldIn) {
	super(type, worldIn);
    }

    public BlackLoosejawEntity(FMLPlayMessages.SpawnEntity spawnEntity, World worldIn) {
	this(BLACK_LOOSEJAW, worldIn);
    }

    @Override
    protected ItemStack getFishBucket() {
	return new ItemStack(BLACK_LOOSEJAW_BUCKET);
    }

    @Override
    public AttributeModifier getNegativeSuborbitalBoost() {
	return SUBORBITAL_BOOST;
    }

    @Override
    public AttributeModifier getSuborbitalBoost() {
	return SUBORBITAL_BOOST;
    }

    @Override
    protected void registerAttributes() {
	super.registerAttributes();

	this.getAttribute(ATTACK_DAMAGE).setBaseValue(8);
	this.getAttribute(FOLLOW_RANGE).setBaseValue(6); // 16
	this.getAttribute(MAX_HEALTH).setBaseValue(18);
	this.getAttribute(MOVEMENT_SPEED).setBaseValue(1.26F);
	this.getAttribute(MOVEMENT_SPEED_BOOST).setBaseValue(2.6F);

	this.getAttribute(BLINK_INTERVAL).setBaseValue(3);
	this.getAttribute(BLINK_RANGE).setBaseValue(14);
	this.getAttribute(BLINKING_DELAY).setBaseValue(110);
	this.getAttribute(MAX_BLINKS).setBaseValue(13);
	this.getAttribute(MIN_BLINKS).setBaseValue(5);

	this.getAttribute(LURE_MAX_LIT).setBaseValue(60); // 600
	this.getAttribute(LURE_MIN_LIT).setBaseValue(40); // 400
	this.getAttribute(LURE_MAX_UNLIT).setBaseValue(50); // 500
	this.getAttribute(LURE_MIN_UNLIT).setBaseValue(23); // 230
	this.getAttribute(LURE_ATTRACTION).setBaseValue(10);
	this.getAttribute(LURE_DELAY).setBaseValue(2025); // 4050
	this.getAttribute(MAX_LURING).setBaseValue(9); // 3
	this.getAttribute(MIN_LURING).setBaseValue(6); // 2

	this.getAttribute(PREY_DETECTION).setBaseValue(1.25F); // 3

	this.getAttribute(SUBORBITAL_DELAY).setBaseValue(300);
	this.getAttribute(SUBORBITAL_MAX_LIT).setBaseValue(240);
	this.getAttribute(SUBORBITAL_MIN_LIT).setBaseValue(100);
    }

}
