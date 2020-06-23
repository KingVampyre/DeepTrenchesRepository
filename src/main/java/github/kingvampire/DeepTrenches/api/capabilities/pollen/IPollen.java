package github.kingvampire.DeepTrenches.api.capabilities.pollen;

import static github.kingvampire.DeepTrenches.api.capabilities.pollen.PollenProvider.POLLEN_CAPABILITY;
import static net.minecraftforge.fml.network.PacketDistributor.TRACKING_ENTITY_AND_SELF;

import github.kingvampire.DeepTrenches.api.capabilities.IPacketSender;
import github.kingvampire.DeepTrenches.core.util.NetworkHandler;
import github.kingvampire.DeepTrenches.core.util.packets.CapabilityPacket;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;

public interface IPollen extends IPacketSender {

    int getAqueanSap();

    int getMaxAqueanSap();

    int getMaxPollen();

    int getPollen();

    int getTicksSincePollination();

    default boolean hasAqueanSap() {
	return this.getAqueanSap() > 0;
    }

    default boolean hasAqueanSapCapacity() {
	return this.getMaxAqueanSap() > this.getAqueanSap();
    }

    default boolean hasPollen() {
	return this.getPollen() > 0;
    }

    default boolean hasPollenCapacity() {
	return this.getMaxPollen() > this.getPollen();
    }

    @Override
    default void sendPacket(Entity entity) {
	CompoundNBT compound = (CompoundNBT) POLLEN_CAPABILITY.writeNBT(this, null);
	CapabilityPacket packet = new CapabilityPacket(POLLEN_CAPABILITY, entity, compound);

	NetworkHandler.INSTANCE.send(TRACKING_ENTITY_AND_SELF.with(() -> entity), packet);
    }

    void setAqueanSap(int aqueanSap);

    void setPollen(int pollen);

    void setTicksSincePollination(int ticksSincePollination);

}
