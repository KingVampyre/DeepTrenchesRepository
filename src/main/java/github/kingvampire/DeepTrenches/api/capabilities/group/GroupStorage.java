package github.kingvampire.DeepTrenches.api.capabilities.group;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class GroupStorage implements IStorage<IGroup> {

    @Override
    public void readNBT(Capability<IGroup> capability, IGroup instance, Direction side, INBT nbt) {
	World world = instance.getWorld();

	CompoundNBT compound = (CompoundNBT) nbt;
	ListNBT listNBT = compound.getList("Members", 8);

	Set<CreatureEntity> members = Sets.newHashSet();
	Map<CreatureEntity, Integer> memberTicks = Maps.newHashMap();

	for (int i = 0; i < listNBT.size(); i++) {
	    CompoundNBT compoundNBT = listNBT.getCompound(i);

	    int id = compoundNBT.getInt("ID");
	    int ticks = compoundNBT.getInt("Ticks");

	    CreatureEntity creature = (CreatureEntity) world.getEntityByID(id);

	    members.add(creature);
	    memberTicks.put(creature, ticks);
	}

	instance.setGroup(members);
	instance.setMemberTicks(memberTicks);

	if (compound.contains("GroupLeader")) {
	    int id = compound.getInt("GroupLeader");

	    instance.setGroupLeader(CreatureEntity.class.cast(world.getEntityByID(id)));
	}

    }

    @Override
    public INBT writeNBT(Capability<IGroup> capability, IGroup instance, Direction side) {
	CompoundNBT compound = new CompoundNBT();
	ListNBT listNBT = new ListNBT();

	List<CreatureEntity> entities = instance.getMembers().stream().collect(Collectors.toList());

	for (int i = 0; i < instance.getMembers().size(); i++) {
	    CompoundNBT compoundNBT = new CompoundNBT();
	    CreatureEntity creature = entities.get(i);

	    compoundNBT.putInt("ID", creature.getEntityId());
	    compoundNBT.putInt("Ticks", instance.getMemberTicks().get(creature));

	    listNBT.add(i, compoundNBT);
	}

	CreatureEntity groupLeader = instance.getGroupLeader();

	compound.put("Members", listNBT);

	if (groupLeader != null)
	    compound.putInt("GroupLeader", groupLeader.getEntityId());

	return compound;
    }

}
