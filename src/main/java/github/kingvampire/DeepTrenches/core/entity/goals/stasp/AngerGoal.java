package github.kingvampire.DeepTrenches.core.entity.goals.stasp;

import static net.minecraft.util.math.BlockPos.ZERO;

import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AngerGoal extends HurtByTargetGoal {

	public AngerGoal(StaspEntity stasp) {
		super(stasp);

		this.setCallsForHelp();
	}

	@Override
	protected void alertOthers() {
		World world = this.goalOwner.getEntityWorld();
		BlockPos pos = this.goalOwner.getPosition();

		AxisAlignedBB box = new AxisAlignedBB(pos).grow(48F);
		LivingEntity target = this.goalOwner.getAttackTarget();

		BlockPos home = this.goalOwner.getHomePosition();

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

	@Override
	protected void setAttackTarget(MobEntity mobIn, LivingEntity targetIn) {
		super.setAttackTarget(mobIn, targetIn);

		World world = mobIn.getEntityWorld();
		StaspEntity stasp = (StaspEntity) mobIn;

		stasp.setAnger(200 + world.rand.nextInt(100));
	}

	@Override
	public boolean shouldContinueExecuting() {
		StaspEntity stasp = (StaspEntity) this.goalOwner;

		return stasp.isAngry() && super.shouldContinueExecuting();
	}

	public boolean shouldExecute() {
		int revengeTimer = this.goalOwner.getRevengeTimer();
		LivingEntity targetIn = this.goalOwner.getRevengeTarget();

		if (targetIn != null && targetIn instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) targetIn;

			if (player.abilities.isCreativeMode)
				return false;
		}
				
		return targetIn != null && revengeTimer >= 0;
	}

	@Override
	public void startExecuting() {
		super.startExecuting();

		StaspEntity stasp = (StaspEntity) this.goalOwner;
		World world = stasp.getEntityWorld();

		stasp.setAnger(200 + world.rand.nextInt(100));

	}

	@Override
	public void tick() {
		StaspEntity stasp = (StaspEntity) this.goalOwner;

		if (stasp.isAngry()) {
			int anger = stasp.getAnger();

			stasp.setAnger(--anger);
		}
	}

}