package github.kingvampire.DeepTrenches.api.capabilities.anger;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class AngerStorage implements IStorage<IAnger> {

    @Override
    public INBT writeNBT(Capability<IAnger> capability, IAnger instance, Direction side) {
	CompoundNBT compound = new CompoundNBT();

	compound.putInt("Anger", instance.getAnger());

	return compound;
    }

    @Override
    public void readNBT(Capability<IAnger> capability, IAnger instance, Direction side, INBT nbt) {
	CompoundNBT compound = (CompoundNBT) nbt;

	instance.setAnger(compound.getInt("Anger"));
    }

}
