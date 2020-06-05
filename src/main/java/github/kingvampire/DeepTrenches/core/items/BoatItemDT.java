package github.kingvampire.DeepTrenches.core.items;

import static github.kingvampire.DeepTrenches.core.init.ModEntities.BOAT;
import static net.minecraft.stats.Stats.ITEM_USED;
import static net.minecraft.util.ActionResultType.FAIL;
import static net.minecraft.util.ActionResultType.PASS;
import static net.minecraft.util.ActionResultType.SUCCESS;
import static net.minecraft.util.EntityPredicates.NOT_SPECTATING;
import static net.minecraft.util.math.RayTraceContext.FluidMode.ANY;
import static net.minecraft.util.math.RayTraceResult.Type.BLOCK;
import static net.minecraft.util.math.RayTraceResult.Type.MISS;

import java.util.List;
import java.util.function.Predicate;

import github.kingvampire.DeepTrenches.api.entity.ModBoatEntity;
import github.kingvampire.DeepTrenches.api.enums.WoodType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BoatItemDT extends Item {

	private static final Predicate<Entity> PREDICATE = NOT_SPECTATING.and(Entity::canBeCollidedWith);

	private WoodType type;

	public BoatItemDT(WoodType type, Properties properties) {
		super(properties);

		this.type = type;

	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		RayTraceResult ray = rayTrace(worldIn, playerIn, ANY);

		if (ray.getType() == MISS)
			return new ActionResult<>(PASS, itemstack);

		Vec3d vec3d = playerIn.getLook(1.0F);
		AxisAlignedBB box = playerIn.getBoundingBox().expand(vec3d.scale(5)).grow(1);
		List<Entity> entities = worldIn.getEntitiesInAABBexcluding(playerIn, box, PREDICATE);

		if (!entities.isEmpty()) {
			Vec3d vec = playerIn.getEyePosition(1);

			for (Entity entity : entities) {
				AxisAlignedBB aabb = entity.getBoundingBox().grow(entity.getCollisionBorderSize());

				if (aabb.contains(vec))
					return new ActionResult<>(PASS, itemstack);
			}
		}

		if (ray.getType() == BLOCK) {
			ModBoatEntity boat = new ModBoatEntity(BOAT, worldIn);
			Vec3d vec = ray.getHitVec();

			boat.setWoodType(this.type);
			boat.setRotationYawHead(playerIn.rotationYaw);
			boat.setPosition(vec.getX(), vec.getY(), vec.getZ());

			if (!worldIn.isCollisionBoxesEmpty(boat, boat.getBoundingBox().grow(-0.1)))
				return new ActionResult<>(FAIL, itemstack);
			else {
				if (!worldIn.isRemote())
					worldIn.addEntity(boat);

				if (!playerIn.abilities.isCreativeMode)
					itemstack.shrink(1);

				playerIn.addStat(ITEM_USED.get(this));

				return new ActionResult<>(SUCCESS, itemstack);
			}
		} else
			return new ActionResult<>(PASS, itemstack);

	}

}
