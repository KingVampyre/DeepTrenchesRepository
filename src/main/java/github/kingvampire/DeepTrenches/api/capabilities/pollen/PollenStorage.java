package github.kingvampire.DeepTrenches.api.capabilities.pollen;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class PollenStorage implements IStorage<IPollen> {

    @Override
    public INBT writeNBT(Capability<IPollen> capability, IPollen instance, Direction side) {
	CompoundNBT compound = new CompoundNBT();

	compound.putInt("AqueanSap", instance.getAqueanSap());
	compound.putInt("Pollen", instance.getPollen());
	compound.putInt("TicksSincePollination", instance.getTicksSincePollination());

	return compound;
    }

    @Override
    public void readNBT(Capability<IPollen> capability, IPollen instance, Direction side, INBT nbt) {
	CompoundNBT compound = (CompoundNBT) nbt;

	instance.setAqueanSap(compound.getInt("AqueanSap"));
	instance.setPollen(compound.getInt("Pollen"));
	instance.setTicksSincePollination(compound.getInt("TicksSincePollination"));
    }

}
