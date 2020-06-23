package github.kingvampire.DeepTrenches.api.capabilities.pollen;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class PollenProvider implements ICapabilitySerializable<CompoundNBT> {

    @CapabilityInject(IPollen.class)
    public static final Capability<IPollen> POLLEN_CAPABILITY = null;

    private LazyOptional<IPollen> pollen;

    public PollenProvider(int maxAqueanSap, int maxPollen) {
	this.pollen = LazyOptional.of(() -> new Pollen(maxAqueanSap, maxPollen));
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
	POLLEN_CAPABILITY.readNBT(this.pollen.orElseThrow(IllegalArgumentException::new), null, nbt);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
	return cap == POLLEN_CAPABILITY ? this.pollen.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
	return (CompoundNBT) POLLEN_CAPABILITY.writeNBT(this.pollen.orElseThrow(IllegalArgumentException::new), null);
    }

}
