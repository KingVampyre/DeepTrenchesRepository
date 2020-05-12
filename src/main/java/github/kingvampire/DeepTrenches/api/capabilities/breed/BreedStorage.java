package github.kingvampire.DeepTrenches.api.capabilities.breed;

import java.util.UUID;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class BreedStorage implements IStorage<IBreed> {

    @Override
    public void readNBT(Capability<IBreed> capability, IBreed instance, Direction side, INBT nbt) {
	CompoundNBT compound = (CompoundNBT) nbt;

	instance.setInLove(compound.getInt("InLove"));

	if (compound.contains("LoveCause", 8))
	    instance.setPlayerInLove(compound.getUniqueId("LoveCause"));

    }

    @Override
    public INBT writeNBT(Capability<IBreed> capability, IBreed instance, Direction side) {
	CompoundNBT compound = new CompoundNBT();

	compound.putInt("InLove", instance.getInLove());

	UUID uuid = instance.getPlayerInLove();

	if (uuid != null)
	    compound.putUniqueId("LoveCause", uuid);

	return compound;
    }

}
