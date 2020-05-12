package github.kingvampire.DeepTrenches.api.capabilities.anger;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class AngerProvider implements ICapabilitySerializable<CompoundNBT> {

    @CapabilityInject(IAnger.class)
    public static final Capability<IAnger> ANGER_CAPABILITY = null;

    private final LazyOptional<IAnger> anger;

    public AngerProvider(int minTicks, int bonusTicks) {
	this.anger = LazyOptional.of(() -> new Anger(minTicks, bonusTicks));
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
	return cap == ANGER_CAPABILITY ? this.anger.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
	return (CompoundNBT) ANGER_CAPABILITY.writeNBT(this.anger.orElseThrow(IllegalArgumentException::new), null);
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
	ANGER_CAPABILITY.readNBT(this.anger.orElseThrow(IllegalArgumentException::new), null, nbt);
    }

}
