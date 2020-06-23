package github.kingvampire.DeepTrenches.api.capabilities.lit;

import static github.kingvampire.DeepTrenches.api.capabilities.lit.LitProvider.LIT_CAPABILITY;
import static net.minecraftforge.fml.network.PacketDistributor.TRACKING_ENTITY_AND_SELF;

import github.kingvampire.DeepTrenches.api.capabilities.IPacketSender;
import github.kingvampire.DeepTrenches.api.enums.LitState;
import github.kingvampire.DeepTrenches.core.util.NetworkHandler;
import github.kingvampire.DeepTrenches.core.util.packets.CapabilityPacket;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;

public interface ILit extends IPacketSender {

    LitState getLitState();

    @Override
    default void sendPacket(Entity entity) {
	CompoundNBT compound = (CompoundNBT) LIT_CAPABILITY.writeNBT(this, null);
	CapabilityPacket packet = new CapabilityPacket(LIT_CAPABILITY, entity, compound);

	NetworkHandler.INSTANCE.send(TRACKING_ENTITY_AND_SELF.with(() -> entity), packet);
    }

    void setLitState(LitState litState);

}
