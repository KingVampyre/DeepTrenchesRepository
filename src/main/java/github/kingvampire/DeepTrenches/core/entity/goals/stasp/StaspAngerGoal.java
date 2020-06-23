package github.kingvampire.DeepTrenches.core.entity.goals.stasp;

import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;
import static github.kingvampire.DeepTrenches.core.init.ModEffects.FLOWER_BEAUTY;
import static net.minecraft.entity.SharedMonsterAttributes.FOLLOW_RANGE;
import static net.minecraft.util.math.BlockPos.ZERO;

import github.kingvampire.DeepTrenches.api.capabilities.anger.IAnger;
import github.kingvampire.DeepTrenches.api.entity.goals.AngerGoal;
import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public class StaspAngerGoal extends AngerGoal {

    public StaspAngerGoal(CreatureEntity creature) {
	super(creature);
    }

    @Override
    protected void alertOthers() {
	double range = this.goalOwner.getAttribute(FOLLOW_RANGE).getBaseValue();
	LivingEntity target = this.goalOwner.getAttackTarget();

	BlockPos home = this.goalOwner.getHomePosition();
	BlockPos pos = this.goalOwner.getPosition();
	World world = this.goalOwner.getEntityWorld();

	AxisAlignedBB box = new AxisAlignedBB(pos).grow(range);

	for (StaspEntity stasp : world.func_225317_b(StaspEntity.class, box)) {

	    if (stasp.getAttackTarget() != null)
		continue;

	    if (home != ZERO && !home.equals(stasp.getHomePosition()))
		continue;

	    if (stasp.isPotionActive(FLOWER_BEAUTY))
		continue;

	    if (target.isPotionActive(FLOWER_BEAUTY))
		continue;

	    if (target instanceof PlayerEntity) {
		PlayerEntity player = (PlayerEntity) target;

		if (player.abilities.isCreativeMode)
		    continue;
	    }

	    stasp.setIsHanging(false);

	    this.setAttackTarget(stasp, target);
	}
    }

    @Override
    public boolean shouldContinueExecuting() {
	LazyOptional<IAnger> anger = this.creature.getCapability(ANGER_CAPABILITY);

	if (anger.isPresent()) {
	    IAnger ianger = anger.orElseThrow(IllegalArgumentException::new);

	    if (ianger.isAngry()) {
		LivingEntity target = this.goalOwner.getAttackTarget();

		if (target == null)
		    return false;

		if (!target.isAlive())
		    return false;

		if (this.goalOwner.getHomePosition() == ZERO)
		    return false;

		if (this.goalOwner.isPotionActive(FLOWER_BEAUTY))
		    return false;

		if (target.isPotionActive(FLOWER_BEAUTY))
		    return false;

		if (target instanceof PlayerEntity) {
		    PlayerEntity player = (PlayerEntity) target;

		    if (player.abilities.isCreativeMode)
			return false;
		}

		return super.shouldContinueExecuting();
	    }
	}

	return false;
    }

    @Override
    public boolean shouldExecute() {
	LivingEntity targetIn = this.creature.getRevengeTarget();

	if (targetIn == null)
	    return false;

	if (!targetIn.isAlive())
	    return false;

	if (this.goalOwner.getHomePosition() == ZERO)
	    return false;

	if (this.goalOwner.isPotionActive(FLOWER_BEAUTY))
	    return false;

	if (targetIn.isPotionActive(FLOWER_BEAUTY))
	    return false;

	if (targetIn instanceof PlayerEntity) {
	    PlayerEntity player = (PlayerEntity) targetIn;

	    if (player.abilities.isCreativeMode)
		return false;
	}

	return super.shouldExecute();
    }

}
