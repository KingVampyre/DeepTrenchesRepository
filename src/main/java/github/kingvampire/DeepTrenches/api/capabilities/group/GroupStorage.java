package github.kingvampire.DeepTrenches.api.capabilities.group;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class GroupStorage implements IStorage<IGroup> {

    @Override
    public void readNBT(Capability<IGroup> capability, IGroup instance, Direction side, INBT nbt) {
	CreatureEntity creature = instance.getCreatureEntity();
	ServerWorld world = (ServerWorld) creature.getEntityWorld();

	CompoundNBT compound = (CompoundNBT) nbt;
	ListNBT listNBT = compound.getList("Group", 8);
	Set<CreatureEntity> group = Sets.newHashSet();

	for (int i = 0; i < listNBT.size(); i++) {
	    CompoundNBT compoundNBT = listNBT.getCompound(i);
	    UUID uuid = UUID.fromString(compoundNBT.getString("UUID"));

	    group.add(CreatureEntity.class.cast(world.getEntityByUuid(uuid)));
	}

	instance.setGroup(group);

	if (compound.contains("GroupLeader")) {
	    UUID uuid = UUID.fromString(compound.getString("UUID"));

	    instance.setGroupLeader(CreatureEntity.class.cast(world.getEntityByUuid(uuid)));
	}

    }

    @Override
    public INBT writeNBT(Capability<IGroup> capability, IGroup instance, Direction side) {
	CompoundNBT compound = new CompoundNBT();
	ListNBT listNBT = new ListNBT();

	List<CreatureEntity> entities = instance.getGroup().stream().collect(Collectors.toList());

	for (int i = 0; i < instance.getGroup().size(); i++) {
	    CompoundNBT compoundNBT = new CompoundNBT();
	    CreatureEntity creature = entities.get(i);

	    compoundNBT.putString("UUID", creature.getUniqueID().toString());

	    listNBT.add(i, compoundNBT);
	}

	CreatureEntity groupLeader = instance.getGroupLeader();

	compound.put("Group", listNBT);

	if (groupLeader != null)
	    compound.putUniqueId("GroupLeader", groupLeader.getUniqueID());

	return compound;
    }

}
