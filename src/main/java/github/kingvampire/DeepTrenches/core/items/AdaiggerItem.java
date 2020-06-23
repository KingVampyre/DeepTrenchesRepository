package github.kingvampire.DeepTrenches.core.items;

import static github.kingvampire.DeepTrenches.core.init.ModEnchantments.DRAINING;
import static net.minecraft.block.Blocks.COBWEB;
import static net.minecraft.block.material.Material.CORAL;
import static net.minecraft.block.material.Material.GOURD;
import static net.minecraft.block.material.Material.PLANTS;
import static net.minecraft.block.material.Material.TALL_PLANTS;
import static net.minecraft.enchantment.EnchantmentType.WEAPON;
import static net.minecraft.enchantment.Enchantments.LOYALTY;
import static net.minecraft.enchantment.Enchantments.SWEEPING;
import static net.minecraft.entity.SharedMonsterAttributes.ATTACK_DAMAGE;
import static net.minecraft.entity.SharedMonsterAttributes.ATTACK_SPEED;
import static net.minecraft.entity.ai.attributes.AttributeModifier.Operation.ADDITION;
import static net.minecraft.entity.projectile.AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
import static net.minecraft.inventory.EquipmentSlotType.MAINHAND;
import static net.minecraft.item.ItemTier.GOLD;
import static net.minecraft.item.UseAction.BOW;
import static net.minecraft.tags.BlockTags.LEAVES;
import static net.minecraft.util.ActionResultType.FAIL;
import static net.minecraft.util.ActionResultType.SUCCESS;
import static net.minecraft.util.SoundCategory.PLAYERS;
import static net.minecraft.util.SoundEvents.ITEM_TRIDENT_THROW;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import github.kingvampire.DeepTrenches.core.entity.AdaiggerEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AdaiggerItem extends TieredItem {

    public AdaiggerItem(Properties properties) {
	super(GOLD, properties);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {

	if (enchantment == LOYALTY || enchantment == DRAINING)
	    return true;
	else if (enchantment == SWEEPING)
	    return false;

	return enchantment.type == WEAPON;
    }

    @Override
    public boolean canHarvestBlock(BlockState blockIn) {
	return blockIn.getBlock() == COBWEB;
    }

    @Override
    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
	return !player.isCreative();
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
	Multimap<String, AttributeModifier> map = HashMultimap.create();

	String name = "Weapon Modifier";

	if (equipmentSlot == MAINHAND) {
	    map.put(ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, name, 4, ADDITION));
	    map.put(ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, name, 4, ADDITION));
	}

	return map;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
	Block block = state.getBlock();

	if (block == COBWEB)
	    return 15;

	Material material = state.getMaterial();

	return material != PLANTS && material != TALL_PLANTS && material != CORAL && !state.isIn(LEAVES)
		&& material != GOURD ? 1.0F : 1.5F;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
	return BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
	return 72000;
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
	stack.damageItem(1, attacker, living -> living.sendBreakAnimation(MAINHAND));

	return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos,
	    LivingEntity entityLiving) {

	if (state.getBlockHardness(worldIn, pos) != 0)
	    stack.damageItem(2, entityLiving, living -> living.sendBreakAnimation(MAINHAND));

	return true;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
	ItemStack stack = playerIn.getHeldItem(handIn);

	if (stack.getDamage() >= stack.getMaxDamage())
	    return new ActionResult<>(FAIL, stack);
	else
	    playerIn.setActiveHand(handIn);

	return new ActionResult<>(SUCCESS, stack);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {

	if (entityLiving instanceof PlayerEntity) {
	    PlayerEntity player = (PlayerEntity) entityLiving;
	    int duration = this.getUseDuration(stack) - timeLeft;

	    if (duration >= 10 && !worldIn.isRemote()) {
		stack.damageItem(1, player, playerIn -> playerIn.sendBreakAnimation(entityLiving.getActiveHand()));

		AdaiggerEntity adaigger = new AdaiggerEntity(worldIn, player, stack);

		adaigger.shoot(player, player.rotationPitch, player.rotationYaw, 0, 3, 1);

		if (player.abilities.isCreativeMode)
		    adaigger.pickupStatus = CREATIVE_ONLY;

		worldIn.addEntity(adaigger);
		worldIn.playMovingSound(null, adaigger, ITEM_TRIDENT_THROW, PLAYERS, 1, 1);

		if (!player.abilities.isCreativeMode)
		    player.inventory.deleteStack(stack);
	    }

	    player.addStat(Stats.ITEM_USED.get(this));
	}
    }

}
