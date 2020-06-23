package github.kingvampire.DeepTrenches.api.capabilities.group;

import static github.kingvampire.DeepTrenches.api.capabilities.group.GroupProvider.GROUP_CAPABILITY;
import static net.minecraft.entity.SharedMonsterAttributes.FOLLOW_RANGE;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public class Group implements IGroup {
    
    protected final int maxGroupSize;

    protected CreatureEntity creature;    
    protected CreatureEntity groupLeader;
    protected Set<CreatureEntity> members;    
    protected Map<CreatureEntity, Integer> memberTicks;

    public Group() {
	this.maxGroupSize = 3;
	this.members = Sets.newHashSet();
    }

    public Group(CreatureEntity creature, int maxGroupSize) {
	this.creature = creature;
	this.maxGroupSize = maxGroupSize;
	this.members = Sets.newHashSet();
    }

    @Override
    public boolean canEnterGroup(IGroup group) {
	return group != null && group.getGroupSize() < group.getMaxGroupSize();
    }

    @Override
    public boolean enterGroup(CreatureEntity creature) {
	LazyOptional<IGroup> group = creature.getCapability(GROUP_CAPABILITY);

	if (group.isPresent()) {
	    IGroup igroup = group.orElseThrow(IllegalArgumentException::new);

	    if (this.canEnterGroup(igroup)) {
		this.setGroupLeader(creature);

		return igroup.getMembers().add(this.creature);
	    }
	}

	return false;
    }

    @Override
    public CreatureEntity getCreatureEntity() {
	return this.creature;
    }

    @Override
    public CreatureEntity getGroupLeader() {
	return this.groupLeader;
    }

    @Override
    public int getGroupSize() {
	return this.members.size();
    }

    @Override
    public int getMaxGroupSize() {
	return this.maxGroupSize;
    }

    @Override
    public Set<CreatureEntity> getMembers() {
	return this.members;
    }

    @Override
    public Map<CreatureEntity, Integer> getMemberTicks() {
	return this.memberTicks;
    }

    @Override
    public World getWorld() {
	return this.getCreatureEntity().getEntityWorld();
    }

    @Override
    public boolean hasGroupLeader() {
	return this.groupLeader != null && this.groupLeader.isAlive();
    }

    @Override
    public boolean inRangeOfGroup() {
	double range = this.creature.getAttribute(FOLLOW_RANGE).getBaseValue();

	return this.members.stream().allMatch(creature -> creature.getDistanceSq(this.creature) <= range);
    }

    @Override
    public boolean isAlone() {
	return !this.hasGroupLeader();
    }

    @Override
    public boolean isGroupLeader() {
	return this.groupLeader != null && this.creature == this.groupLeader;
    }

    @Override
    public boolean leaveGroup() {

	if (!this.isGroupLeader()) {

	    if (this.groupLeader == null)
		return true;

	    LazyOptional<IGroup> group = this.groupLeader.getCapability(GROUP_CAPABILITY);

	    this.setGroup(Sets.newHashSet());
	    this.setGroupLeader(null);

	    if (group.isPresent())
		return group.orElseThrow(IllegalArgumentException::new).getMembers().remove(this.creature);
	}

	return false;
    }

    @Override
    public void setGroup(Set<CreatureEntity> group) {
	this.members = group;
    }

    @Override
    public void setGroupLeader(CreatureEntity groupLeader) {
	this.groupLeader = groupLeader;
    }

    @Override
    public void setMemberTicks(Map<CreatureEntity, Integer> memberTicks) {
	this.memberTicks = memberTicks;
    }

}
