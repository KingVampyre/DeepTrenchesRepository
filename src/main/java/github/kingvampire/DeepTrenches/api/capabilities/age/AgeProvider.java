package github.kingvampire.DeepTrenches.api.capabilities.age;

import java.util.function.Function;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class AgeProvider implements ICapabilitySerializable<CompoundNBT> {

    @CapabilityInject(IAge.class)
    public static final Capability<IAge> AGE_CAPABILITY = null;

    private final LazyOptional<IAge> age;

    public AgeProvider(CreatureEntity creature, Function<CreatureEntity, CreatureEntity> createChild,
	    DataParameter<Boolean> parameter) {

	this.age = LazyOptional.of(() -> new Age(creature, createChild, parameter));
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
	return cap == AGE_CAPABILITY ? this.age.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
	return (CompoundNBT) AGE_CAPABILITY.writeNBT(this.age.orElseThrow(IllegalArgumentException::new), null);
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
	AGE_CAPABILITY.readNBT(this.age.orElseThrow(IllegalArgumentException::new), null, nbt);
    }

}
