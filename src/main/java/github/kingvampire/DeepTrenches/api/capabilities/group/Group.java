package github.kingvampire.DeepTrenches.api.capabilities.group;

import static github.kingvampire.DeepTrenches.api.capabilities.group.GroupProvider.GROUP_CAPABILITY;
import static net.minecraft.entity.SharedMonsterAttributes.FOLLOW_RANGE;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.entity.CreatureEntity;
import net.minecraftforge.common.util.LazyOptional;

public class Group implements IGroup {

    protected int maxGroupSize;
    
    protected CreatureEntity creature;
    protected Set<CreatureEntity> group;
    protected CreatureEntity groupLeader;

    public Group() {
	this.group = Sets.newHashSet();
    }

    public Group(CreatureEntity creature, int maxGroupSize) {
	this.creature = creature;
	this.group = Sets.newHashSet();
	this.maxGroupSize = maxGroupSize;
    }

    @Override
    public boolean canEnterGroup(IGroup group) {
	return group != null && group.getGroupSize() < group.getMaxGroupSize();
    }

    @Override
    public boolean enterGroup(CreatureEntity creature) {
	LazyOptional<IGroup> lazyOptional = creature.getCapability(GROUP_CAPABILITY);

	if (lazyOptional.isPresent()) {
	    IGroup group = lazyOptional.orElse(null);

	    if (this.canEnterGroup(group)) {
		this.setGroupLeader(creature);

		return group.getMembers().add(this.creature);
	    }
	}

	return false;
    }

    @Override
    public CreatureEntity getCreatureEntity() {
	return this.creature;
    }

    @Override
    public Set<CreatureEntity> getMembers() {
	return this.group;
    }

    @Override
    public CreatureEntity getGroupLeader() {
	return this.groupLeader;
    }

    @Override
    public int getGroupSize() {
	return this.group.size();
    }

    @Override
    public int getMaxGroupSize() {
	return this.maxGroupSize;
    }

    @Override
    public boolean hasGroupLeader() {
	return this.groupLeader != null && this.groupLeader.isAlive();
    }

    @Override
    public boolean inRangeOfGroup() {
	double range = this.creature.getAttribute(FOLLOW_RANGE).getBaseValue();

	return this.group.stream().allMatch(creature -> creature.getDistanceSq(this.creature) <= range);
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

	    LazyOptional<IGroup> lazyOptional = this.groupLeader.getCapability(GROUP_CAPABILITY);

	    this.setGroup(Sets.newHashSet());
	    this.setGroupLeader(null);

	    if (lazyOptional.isPresent())
		return lazyOptional.orElseThrow(IllegalArgumentException::new).getMembers().remove(this.creature);
	}

	return false;
    }

    @Override
    public void setGroup(Set<CreatureEntity> group) {
	this.group = group;
    }

    @Override
    public void setGroupLeader(CreatureEntity groupLeader) {
	this.groupLeader = groupLeader;
    }

}
