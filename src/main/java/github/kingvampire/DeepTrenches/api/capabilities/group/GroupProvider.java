package github.kingvampire.DeepTrenches.api.capabilities.group;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class GroupProvider implements ICapabilitySerializable<CompoundNBT> {

    @CapabilityInject(IGroup.class)
    public static final Capability<IGroup> GROUP_CAPABILITY = null;

    private final LazyOptional<IGroup> group;

    public GroupProvider(CreatureEntity entity) {
	this.group = LazyOptional.of(() -> new Group(entity));
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
	return cap == GROUP_CAPABILITY ? this.group.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
	return (CompoundNBT) GROUP_CAPABILITY.writeNBT(this.group.orElseThrow(IllegalArgumentException::new), null);
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
	GROUP_CAPABILITY.readNBT(this.group.orElseThrow(IllegalArgumentException::new), null, nbt);
    }

}
