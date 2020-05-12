package github.kingvampire.DeepTrenches.api.entity.goals;

import static github.kingvampire.DeepTrenches.api.capabilities.group.GroupProvider.GROUP_CAPABILITY;
import static net.minecraft.entity.SharedMonsterAttributes.FOLLOW_RANGE;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import github.kingvampire.DeepTrenches.api.capabilities.group.IGroup;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FollowGroupLeaderGoal extends Goal {

    private final CreatureEntity creature;
    private final IGroup igroup;
    private int navigateTimer;
    private int runDelay;
    private double speed;

    public FollowGroupLeaderGoal(CreatureEntity creature, double speed) {
	this.runDelay = this.getRunDelay(creature.getRNG());
	this.creature = creature;
	this.speed = speed;

	this.igroup = creature.getCapability(GROUP_CAPABILITY).orElseThrow(IllegalArgumentException::new);
    }

    protected int getRunDelay(Random random) {
	return 900;
    }

    @Override
    public boolean shouldContinueExecuting() {
	return this.igroup.hasGroupLeader() && this.igroup.inRangeOfGroup();
    }

    @Override
    public boolean shouldExecute() {

	if (this.igroup.isGroupLeader())
	    return false;

	if (this.igroup.hasGroupLeader())
	    return true;

	if (this.runDelay > 0) {
	    --this.runDelay;

	    return false;

	}

	this.runDelay = this.getRunDelay(this.creature.getRNG());

	double range = this.creature.getAttribute(FOLLOW_RANGE).getBaseValue();
	BlockPos pos = this.creature.getPosition();
	World world = this.creature.getEntityWorld();

	AxisAlignedBB aabb = new AxisAlignedBB(pos).grow(range);

	List<IGroup> bettas = world.getEntitiesInAABBexcluding(this.creature, aabb, null)
		.stream()
		.filter(entity -> this.creature.getClass() == entity.getClass())
		.map(this.creature.getClass()::cast)
		.map(creature -> creature.getCapability(GROUP_CAPABILITY))
		.filter(lazyOptional -> lazyOptional.isPresent())
		.map(lazyOptional -> lazyOptional.orElseThrow(IllegalArgumentException::new))
		.collect(Collectors.toList());

	List<IGroup> leaders = bettas
		.stream()
		.filter(group -> group.isGroupLeader())
		.filter(group -> group.getGroupSize() < group.getMaxGroupSize())
		.sorted(Comparator.comparing(group -> this.creature.getDistanceSq(group.getCreatureEntity())))
		.collect(Collectors.toList());

	if (!leaders.isEmpty()) {
	    Optional<IGroup> optional = leaders.stream().findFirst();

	    if (optional.isPresent()) {
		CreatureEntity leader = optional.get().getCreatureEntity();

		this.igroup.setGroupLeader(leader);

		return optional.get().enterGroup(leader);
	    }
	}

	List<IGroup> list = bettas
		.stream()
		.filter(IGroup::isAlone)
		.limit(this.igroup.getMaxGroupSize() - this.igroup.getGroupSize())
		.collect(Collectors.toList());

	if (!list.isEmpty()) {
	    this.igroup.setGroupLeader(this.creature);

	    return list.stream().allMatch(group -> group.enterGroup(this.creature));
	}

	return false;
    }

    @Override
    public void startExecuting() {
	this.navigateTimer = 0;
    }

    @Override
    public void tick() {

	if (--this.navigateTimer <= 0) {
	    this.navigateTimer = 10;

	    if (this.igroup.hasGroupLeader())
		this.creature.getNavigator().tryMoveToEntityLiving(this.igroup.getGroupLeader(), this.speed);
	}

    }

}
