package github.kingvampire.DeepTrenches.api.capabilities.lit;

import github.kingvampire.DeepTrenches.api.enums.LitState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class LitStorage implements IStorage<ILit> {

    @Override
    public void readNBT(Capability<ILit> capability, ILit instance, Direction side, INBT nbt) {
	CompoundNBT compound = (CompoundNBT) nbt;

	instance.setLitState(LitState.getState(compound.getString("LitState")));
    }

    @Override
    public INBT writeNBT(Capability<ILit> capability, ILit instance, Direction side) {
	CompoundNBT compound = new CompoundNBT();

	compound.putString("LitState", instance.getLitState().getName());

	return compound;
    }

}
