package github.kingvampire.DeepTrenches.core.entity.goals.stasp;

import static net.minecraft.entity.SharedMonsterAttributes.FOLLOW_RANGE;
import static net.minecraft.util.math.BlockPos.ZERO;

import github.kingvampire.DeepTrenches.api.entity.goals.AngerGoal;
import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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

	    if (home != ZERO && home.equals(stasp.getHomePosition()))
		continue;

	    if (target instanceof PlayerEntity) {
		PlayerEntity player = (PlayerEntity) target;

		if (player.abilities.isCreativeMode)
		    continue;
	    }

	    this.setAttackTarget(stasp, target);
	}
    }

}
