package github.kingvampire.DeepTrenches.api.capabilities.group;

import static github.kingvampire.DeepTrenches.api.capabilities.group.GroupProvider.GROUP_CAPABILITY;

import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;

import com.google.common.collect.Sets;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public interface IGroup {

    boolean canEnterGroup(IGroup group);;
    
    boolean enterGroup(CreatureEntity creature);
    
    CreatureEntity getCreatureEntity();
    
    CreatureEntity getGroupLeader();

    int getGroupSize();

    int getMaxGroupSize();
    
    Set<CreatureEntity> getMembers();

    Map<CreatureEntity, Integer> getMemberTicks();

    World getWorld();

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
	    	.map(group -> group.orElse(null))
	    	.forEach(igroup -> igroup.setGroupLeader(null));

	    this.setGroup(Sets.newHashSet());
	}

	this.setGroupLeader(null);
    }

    void setGroup(@Nonnull Set<CreatureEntity> group);

    void setGroupLeader(CreatureEntity groupLeader);

    void setMemberTicks(Map<CreatureEntity, Integer> memberTicks);

    default void tick() {}

}
