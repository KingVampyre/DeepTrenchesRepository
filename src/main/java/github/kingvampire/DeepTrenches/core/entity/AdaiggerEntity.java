package github.kingvampire.DeepTrenches.core.entity;

import static github.kingvampire.DeepTrenches.core.init.ModEntities.ADAIGGER;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class AdaiggerEntity extends TridentEntity {

	public static ItemStack getItemStack(AbstractArrowEntity arrow) {
		CompoundNBT compound = new CompoundNBT();

		arrow.writeAdditional(compound);

		return ItemStack.read(compound.getCompound("Trident"));
	}

	public AdaiggerEntity(EntityType<? extends AdaiggerEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public AdaiggerEntity(FMLPlayMessages.SpawnEntity spawnEntity, World worldIn) {
		super(ADAIGGER, worldIn);
	}

	public AdaiggerEntity(World world, LivingEntity entity, ItemStack stack) {
		super(world, entity, stack);
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	public EntityType<?> getType() {
		return ADAIGGER;
	}

}
