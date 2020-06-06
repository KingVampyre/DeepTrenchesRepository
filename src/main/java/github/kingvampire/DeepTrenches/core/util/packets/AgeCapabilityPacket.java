package github.kingvampire.DeepTrenches.core.util.packets;

import static github.kingvampire.DeepTrenches.api.capabilities.age.AgeProvider.AGE_CAPABILITY;

import java.util.function.Supplier;

import github.kingvampire.DeepTrenches.api.capabilities.age.IAge;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class AgeCapabilityPacket {

    public static AgeCapabilityPacket decode(PacketBuffer buffer) {
	World world = Minecraft.getInstance().world;

	CreatureEntity creature = (CreatureEntity) world.getEntityByID(buffer.readInt());
	int forcedAge = buffer.readInt();
	int forcedAgeTimer = buffer.readInt();
	int growingAge = buffer.readInt();

	return new AgeCapabilityPacket(creature, forcedAge, forcedAgeTimer, growingAge);
    }

    public static void encode(AgeCapabilityPacket msg, PacketBuffer buffer) {
	buffer.writeInt(msg.getCreature().getEntityId());
	buffer.writeInt(msg.getForcedAge());
	buffer.writeInt(msg.getForcedAgeTimer());
	buffer.writeInt(msg.getGrowingAge());
    }

    public static void handle(AgeCapabilityPacket msg, Supplier<NetworkEvent.Context> ctx) {
	Context context = ctx.get();
	NetworkDirection direction = context.getDirection();

	context.enqueueWork(() -> {
	    LazyOptional<IAge> age = msg.getCreature().getCapability(AGE_CAPABILITY);

	    if (age.isPresent()) {
		IAge iage = age.orElseThrow(IllegalArgumentException::new);

		if (direction.getReceptionSide().isClient() && direction.getOriginationSide().isServer()) {
		    iage.setForcedAge(msg.getForcedAge());
		    iage.setForcedAgeTimer(msg.getForcedAgeTimer());
		    iage.setGrowingAge(msg.getGrowingAge());
		}
	    }
	});

	context.setPacketHandled(true);
    }

    private CreatureEntity creature;
    private int forcedAge;
    private int forcedAgeTimer;
    private int growingAge;

    public AgeCapabilityPacket(CreatureEntity creature, int forcedAge, int forcedAgeTimer, int growingAge) {
	this.creature = creature;
	this.forcedAge = forcedAge;
	this.forcedAgeTimer = forcedAgeTimer;
	this.growingAge = growingAge;
    }

    public AgeCapabilityPacket(CreatureEntity creature, IAge iage) {
	this.creature = creature;
	this.forcedAge = iage.getForcedAge();
	this.forcedAgeTimer = iage.getForcedAgeTimer();
	this.growingAge = iage.getGrowingAge();
    }

    public CreatureEntity getCreature() {
	return this.creature;
    }

    public void setCreature(CreatureEntity creature) {
	this.creature = creature;
    }

    public int getForcedAge() {
	return forcedAge;
    }

    public void setForcedAge(int forcedAge) {
	this.forcedAge = forcedAge;
    }

    public int getForcedAgeTimer() {
	return forcedAgeTimer;
    }

    public void setForcedAgeTimer(int forcedAgeTimer) {
	this.forcedAgeTimer = forcedAgeTimer;
    }

    public int getGrowingAge() {
	return growingAge;
    }

    public void setGrowingAge(int growingAge) {
	this.growingAge = growingAge;
    }

}
