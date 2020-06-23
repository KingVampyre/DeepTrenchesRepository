package github.kingvampire.DeepTrenches.api.capabilities.anger;

import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;
import static net.minecraftforge.fml.network.PacketDistributor.TRACKING_ENTITY_AND_SELF;

import github.kingvampire.DeepTrenches.api.capabilities.IPacketSender;
import github.kingvampire.DeepTrenches.core.util.NetworkHandler;
import github.kingvampire.DeepTrenches.core.util.packets.CapabilityPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;

public interface IAnger extends IPacketSender {

    int getAnger();

    boolean isAngry();

    void livingTick();

    @Override
    default void sendPacket(Entity entity) {
	CompoundNBT compound = (CompoundNBT) ANGER_CAPABILITY.writeNBT(this, null);
	CapabilityPacket packet = new CapabilityPacket(ANGER_CAPABILITY, entity, compound);

	NetworkHandler.INSTANCE.send(TRACKING_ENTITY_AND_SELF.with(() -> entity), packet);
    }

    void setAnger(int anger);

    void setAnger(LivingEntity target);

}
