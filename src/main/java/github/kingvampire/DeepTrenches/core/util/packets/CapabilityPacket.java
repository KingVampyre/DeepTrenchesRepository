package github.kingvampire.DeepTrenches.core.util.packets;

import static github.kingvampire.DeepTrenches.api.capabilities.age.AgeProvider.AGE_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.breed.BreedProvider.BREED_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.group.GroupProvider.GROUP_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.lit.LitProvider.LIT_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.pollen.PollenProvider.POLLEN_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.tame.TameProvider.TAME_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.taxonomy.TaxonomyProvider.TAXONOMY_CAPABILITY;

import java.util.function.Supplier;

import github.kingvampire.DeepTrenches.api.capabilities.age.IAge;
import github.kingvampire.DeepTrenches.api.capabilities.anger.IAnger;
import github.kingvampire.DeepTrenches.api.capabilities.breed.IBreed;
import github.kingvampire.DeepTrenches.api.capabilities.group.IGroup;
import github.kingvampire.DeepTrenches.api.capabilities.lit.ILit;
import github.kingvampire.DeepTrenches.api.capabilities.pollen.IPollen;
import github.kingvampire.DeepTrenches.api.capabilities.tame.ITame;
import github.kingvampire.DeepTrenches.api.capabilities.taxonomy.ITaxonomy;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class CapabilityPacket {

    public static CapabilityPacket decode(PacketBuffer buffer) {

	try {
	    Class<?> provider = Class.forName(buffer.readString());
	    Class<?> cap = Class.forName(buffer.readString());

	    World world = Minecraft.getInstance().world;

	    if (provider == Entity.class) {
		Entity entity = world.getEntityByID(buffer.readInt());
		CompoundNBT compound = buffer.readCompoundTag();

		if (cap == IAge.class)
		    return new CapabilityPacket(AGE_CAPABILITY, entity, compound);

		else if (cap == IAnger.class)
		    return new CapabilityPacket(ANGER_CAPABILITY, entity, compound);

		else if (cap == IBreed.class)
		    return new CapabilityPacket(BREED_CAPABILITY, entity, compound);

		else if (cap == IGroup.class)
		    return new CapabilityPacket(GROUP_CAPABILITY, entity, compound);

		else if (cap == ILit.class)
		    return new CapabilityPacket(LIT_CAPABILITY, entity, compound);

		else if (cap == IPollen.class)
		    return new CapabilityPacket(POLLEN_CAPABILITY, entity, compound);

		else if (cap == ITame.class)
		    return new CapabilityPacket(TAME_CAPABILITY, entity, compound);

		else if (cap == ITaxonomy.class)
		    return new CapabilityPacket(TAXONOMY_CAPABILITY, entity, compound);

	    }

	    if (provider == TileEntity.class) {
		TileEntity tileentity = world.getTileEntity(buffer.readBlockPos());
		CompoundNBT compound = buffer.readCompoundTag();

		if (cap == IAge.class)
		    return new CapabilityPacket(AGE_CAPABILITY, tileentity, compound);

		else if (cap == IAnger.class)
		    return new CapabilityPacket(ANGER_CAPABILITY, tileentity, compound);

		else if (cap == IBreed.class)
		    return new CapabilityPacket(BREED_CAPABILITY, tileentity, compound);

		else if (cap == IGroup.class)
		    return new CapabilityPacket(GROUP_CAPABILITY, tileentity, compound);

		else if (cap == ILit.class)
		    return new CapabilityPacket(LIT_CAPABILITY, tileentity, compound);

		else if (cap == IPollen.class)
		    return new CapabilityPacket(POLLEN_CAPABILITY, tileentity, compound);

		else if (cap == ITame.class)
		    return new CapabilityPacket(TAME_CAPABILITY, tileentity, compound);

		else if (cap == ITaxonomy.class)
		    return new CapabilityPacket(TAXONOMY_CAPABILITY, tileentity, compound);

	    }

	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}

	return null;
    }

    public static void encode(CapabilityPacket msg, PacketBuffer buffer) {
	CapabilityProvider<?> provider = msg.getCapabilityProvider();

	if (provider instanceof Entity) {
	    Entity entity = (Entity) provider;

	    buffer.writeString(Entity.class.getName());
	    buffer.writeString(msg.getCapability().getName());
	    buffer.writeInt(entity.getEntityId());
	}

	if (provider instanceof TileEntity) {
	    TileEntity tileEntity = (TileEntity) provider;

	    buffer.writeString(TileEntity.class.getName());
	    buffer.writeString(msg.getCapability().getName());
	    buffer.writeBlockPos(tileEntity.getPos());
	}

	buffer.writeCompoundTag(msg.getCompoundTag());

    }

    public static void handle(CapabilityPacket msg, Supplier<NetworkEvent.Context> ctx) {
	NetworkDirection direction = ctx.get().getDirection();

	ctx.get().enqueueWork(() -> {
	    Capability<?> cap = msg.getCapability();
	    CompoundNBT compound = msg.getCompoundTag();
	    CapabilityProvider<?> provider = msg.getCapabilityProvider();

	    if (direction.getReceptionSide().isClient() && direction.getOriginationSide().isServer()) {

		if (provider == null)
		    return;

		LazyOptional<?> lazy = provider.getCapability(cap);

		if (lazy.isPresent()) {

		    if (cap == AGE_CAPABILITY) {
			IAge instance = (IAge) lazy.orElseThrow(IllegalArgumentException::new);

			AGE_CAPABILITY.readNBT(instance, null, compound);

		    } else if (cap == ANGER_CAPABILITY) {
			IAnger instance = (IAnger) lazy.orElseThrow(IllegalArgumentException::new);

			ANGER_CAPABILITY.readNBT(instance, null, compound);

		    } else if (cap == BREED_CAPABILITY) {
			IBreed instance = (IBreed) lazy.orElseThrow(IllegalArgumentException::new);

			BREED_CAPABILITY.readNBT(instance, null, compound);

		    } else if (cap == GROUP_CAPABILITY) {
			IGroup instance = (IGroup) lazy.orElseThrow(IllegalArgumentException::new);

			GROUP_CAPABILITY.readNBT(instance, null, compound);

		    } else if (cap == LIT_CAPABILITY) {
			ILit instance = (ILit) lazy.orElseThrow(IllegalArgumentException::new);

			LIT_CAPABILITY.readNBT(instance, null, compound);

		    } else if (cap == POLLEN_CAPABILITY) {
			IPollen instance = (IPollen) lazy.orElseThrow(IllegalArgumentException::new);

			POLLEN_CAPABILITY.readNBT(instance, null, compound);

		    } else if (cap == TAME_CAPABILITY) {
			ITame instance = (ITame) lazy.orElseThrow(IllegalArgumentException::new);

			TAME_CAPABILITY.readNBT(instance, null, compound);

		    } else if (cap == TAXONOMY_CAPABILITY) {
			ITaxonomy instance = (ITaxonomy) lazy.orElseThrow(IllegalArgumentException::new);

			TAXONOMY_CAPABILITY.readNBT(instance, null, compound);
		    }
		}

	    }
	});

	ctx.get().setPacketHandled(true);
    }

    private Capability<?> capability;
    private CompoundNBT compound;
    private CapabilityProvider<?> provider;

    public CapabilityPacket(Capability<?> capability, CapabilityProvider<?> provider, CompoundNBT compound) {
	this.capability = capability;
	this.compound = compound;
	this.provider = provider;
    }

    public Capability<?> getCapability() {
	return this.capability;
    }

    public CapabilityProvider<?> getCapabilityProvider() {
	return this.provider;
    }

    public CompoundNBT getCompoundTag() {
	return this.compound;
    }

    public void setCapability(Capability<?> capability) {
	this.capability = capability;
    }

    public void setCapabilityProvider(CapabilityProvider<?> provider) {
	this.provider = provider;
    }

    public void setCompound(CompoundNBT compound) {
	this.compound = compound;
    }

}
