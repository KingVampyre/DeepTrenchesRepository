package github.kingvampire.DeepTrenches.api.capabilities.group;

import static github.kingvampire.DeepTrenches.api.capabilities.group.GroupProvider.GROUP_CAPABILITY;

import java.util.Set;

import javax.annotation.Nonnull;

import com.google.common.collect.Sets;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.util.LazyOptional;

public interface IGroup {

    boolean canEnterGroup(IGroup group);

    boolean enterGroup(CreatureEntity creature);

    CreatureEntity getCreatureEntity();

    Set<CreatureEntity> getMembers();

    CreatureEntity getGroupLeader();

    int getGroupSize();

    int getMaxGroupSize();

    boolean hasGroupLeader();

    boolean inRangeOfGroup();

    boolean isAlone();

    public boolean isGroupLeader();

    boolean leaveGroup();

    /**
     * Override CreatureEntity::onDeath
     */
    default void onDeath(DamageSource cause) {
	
	if (this.isGroupLeader()) {
	    this.getMembers().stream()
	    	.map(creature -> creature.getCapability(GROUP_CAPABILITY))
	    	.filter(LazyOptional::isPresent)
	    	.map(lazyOptional -> lazyOptional.orElse(null))
	    	.forEach(group -> group.setGroupLeader(null));

	    this.setGroup(Sets.newHashSet());
	}

	this.setGroupLeader(null);
    }

    void setGroup(@Nonnull Set<CreatureEntity> group);

    void setGroupLeader(CreatureEntity groupLeader);

}
