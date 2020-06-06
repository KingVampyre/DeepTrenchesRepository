package github.kingvampire.DeepTrenches.api.capabilities.breed;

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

public class BreedProvider implements ICapabilitySerializable<CompoundNBT> {

    @CapabilityInject(IBreed.class)
    public static final Capability<IBreed> BREED_CAPABILITY = null;

    private final LazyOptional<IBreed> breed;

    public BreedProvider(CreatureEntity creature, Item breedingItem) {
	this.breed = LazyOptional.of(() -> new Breed(creature, stack -> stack.getItem() == breedingItem));
    }

    public BreedProvider(CreatureEntity creature, Predicate<ItemStack> predicate) {
	this.breed = LazyOptional.of(() -> new Breed(creature, predicate));
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
	BREED_CAPABILITY.readNBT(this.breed.orElseThrow(IllegalArgumentException::new), null, nbt);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
	return cap == BREED_CAPABILITY ? this.breed.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
	return (CompoundNBT) BREED_CAPABILITY.writeNBT(this.breed.orElseThrow(IllegalArgumentException::new), null);
    }

}
