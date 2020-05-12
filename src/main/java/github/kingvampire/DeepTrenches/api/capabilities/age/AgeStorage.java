package github.kingvampire.DeepTrenches.api.capabilities.age;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class AgeStorage implements IStorage<IAge> {

    @Override
    public void readNBT(Capability<IAge> capability, IAge instance, Direction side, INBT nbt) {
	CompoundNBT compound = (CompoundNBT) nbt;

	instance.setGrowingAge(compound.getInt("Age"));
	instance.setForcedAge(compound.getInt("ForcedAge"));
    }

    @Override
    public INBT writeNBT(Capability<IAge> capability, IAge instance, Direction side) {
	CompoundNBT compound = new CompoundNBT();

	compound.putInt("Age", instance.getGrowingAge());
	compound.putInt("ForcedAge", instance.getForcedAge());

	return compound;
    }

}
