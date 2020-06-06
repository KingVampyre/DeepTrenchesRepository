package github.kingvampire.DeepTrenches.api.capabilities.lit;

import static github.kingvampire.DeepTrenches.core.util.NetworkHandler.INSTANCE;
import static net.minecraftforge.fml.network.PacketDistributor.TRACKING_ENTITY_AND_SELF;

import github.kingvampire.DeepTrenches.api.enums.LitState;
import github.kingvampire.DeepTrenches.core.util.packets.LitCapabilityPacket;
import net.minecraft.entity.CreatureEntity;

public interface ILit {

    LitState getLitState();

    default void sendPacket(CreatureEntity creature) {
	LitCapabilityPacket packet = new LitCapabilityPacket(creature, this.getLitState());

	INSTANCE.send(TRACKING_ENTITY_AND_SELF.with(() -> creature), packet);
    }

    void setLitState(LitState litState);

}
