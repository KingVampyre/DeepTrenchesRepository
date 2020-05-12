package github.kingvampire.DeepTrenches.api.capabilities.tame;

import java.util.UUID;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class TameStorage implements IStorage<ITame> {

    @Override
    public void readNBT(Capability<ITame> capability, ITame instance, Direction side, INBT nbt) {
	CompoundNBT compound = (CompoundNBT) nbt;

	if (compound.contains("OwnerUUID", 8)) {
	    try {
		UUID uuid = UUID.fromString(compound.getString("OwnerUUID"));

		instance.setOwnerId(uuid);
		instance.setTamed(true);
	    } catch (IllegalArgumentException e) {

		instance.setOwnerId(null);
		instance.setTamed(false);
	    }
	}

	instance.setSitting(compound.getBoolean("Sitting"));

    }

    @Override
    public INBT writeNBT(Capability<ITame> capability, ITame instance, Direction side) {
	CompoundNBT compound = new CompoundNBT();
	UUID uuid = instance.getOwnerId();

	if (uuid != null)
	    compound.putString("OwnerUUID", uuid.toString());

	compound.putBoolean("Sitting", instance.isSitting());
	compound.putBoolean("Tamed", instance.isTamed());

	return compound;
    }

}
