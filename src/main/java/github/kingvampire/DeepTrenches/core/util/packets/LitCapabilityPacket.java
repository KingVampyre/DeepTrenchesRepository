package github.kingvampire.DeepTrenches.core.util.packets;

import static github.kingvampire.DeepTrenches.api.capabilities.lit.LitProvider.LIT_CAPABILITY;

import java.util.function.Supplier;

import github.kingvampire.DeepTrenches.api.capabilities.lit.ILit;
import github.kingvampire.DeepTrenches.api.enums.LitState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class LitCapabilityPacket {

    public static LitCapabilityPacket decode(PacketBuffer buffer) {
	World world = Minecraft.getInstance().world;

	CreatureEntity creature = (CreatureEntity) world.getEntityByID(buffer.readInt());
	LitState litState = LitState.getState(buffer.readString());

	return new LitCapabilityPacket(creature, litState);
    }

    public static void encode(LitCapabilityPacket msg, PacketBuffer buffer) {
	buffer.writeInt(msg.getCreature().getEntityId());
	buffer.writeString(msg.getLitState().getName());
    }

    public static void handle(LitCapabilityPacket msg, Supplier<NetworkEvent.Context> ctx) {
	Context context = ctx.get();
	NetworkDirection direction = context.getDirection();

	context.enqueueWork(() -> {
	    CreatureEntity creature = msg.getCreature();

	    if (creature != null) {
		LazyOptional<ILit> lit = creature.getCapability(LIT_CAPABILITY);

		if (lit.isPresent()) {
		    ILit ilit = lit.orElseThrow(IllegalArgumentException::new);

		    if (direction.getReceptionSide().isClient() && direction.getOriginationSide().isServer())
			ilit.setLitState(msg.getLitState());
		}
	    }
	});

	context.setPacketHandled(true);
    }

    private CreatureEntity creature;

    private LitState litState;

    public LitCapabilityPacket(CreatureEntity creature, LitState litState) {
	this.creature = creature;
	this.litState = litState;
    }

    public CreatureEntity getCreature() {
	return this.creature;
    }

    public LitState getLitState() {
	return litState;
    }

    public void setCreature(CreatureEntity creature) {
	this.creature = creature;
    }

    public void setLitState(LitState litState) {
	this.litState = litState;
    }

}
