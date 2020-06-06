package github.kingvampire.DeepTrenches.api.capabilities.tame;

import java.util.function.Predicate;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class TameProvider implements ICapabilitySerializable<CompoundNBT> {

    @CapabilityInject(ITame.class)
    public static final Capability<ITame> TAME_CAPABILITY = null;

    private final LazyOptional<ITame> tame;

    public TameProvider(CreatureEntity creature, int tameChance, Item breedingItem) {
	this.tame = LazyOptional.of(() -> new Tame(creature, tameChance, stack -> stack.getItem() == breedingItem));
    }

    public TameProvider(CreatureEntity creature, int tameChance, Predicate<ItemStack> predicate) {
	this.tame = LazyOptional.of(() -> new Tame(creature, tameChance, predicate));
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
	TAME_CAPABILITY.readNBT(this.tame.orElseThrow(IllegalArgumentException::new), null, nbt);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
	return cap == TAME_CAPABILITY ? this.tame.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
	return (CompoundNBT) TAME_CAPABILITY.writeNBT(this.tame.orElseThrow(IllegalArgumentException::new), null);
    }

}
