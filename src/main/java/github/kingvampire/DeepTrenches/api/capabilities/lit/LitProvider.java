package github.kingvampire.DeepTrenches.api.capabilities.lit;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class LitProvider implements ICapabilitySerializable<CompoundNBT> {

    @CapabilityInject(ILit.class)
    public static final Capability<ILit> LIT_CAPABILITY = null;

    private final LazyOptional<ILit> lit;

    public LitProvider() {
	this.lit = LazyOptional.of(Lit::new);
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
	LIT_CAPABILITY.readNBT(this.lit.orElseThrow(IllegalArgumentException::new), null, nbt);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
	return cap == LIT_CAPABILITY ? this.lit.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
	return (CompoundNBT) LIT_CAPABILITY.writeNBT(this.lit.orElseThrow(IllegalArgumentException::new), null);
    }

}
