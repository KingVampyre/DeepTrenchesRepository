package github.kingvampire.DeepTrenches.core.entity;

import static github.kingvampire.DeepTrenches.api.capabilities.lit.LitProvider.LIT_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.enums.LitState.ALL_LIT;
import static github.kingvampire.DeepTrenches.api.enums.LitState.ALL_UNLIT;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.GIANT_HATCHETFISH;
import static github.kingvampire.DeepTrenches.core.init.ModItems.GIANT_HATCHETFISH_BUCKET;
import static net.minecraft.entity.SharedMonsterAttributes.MAX_HEALTH;
import static net.minecraft.entity.SharedMonsterAttributes.MOVEMENT_SPEED;

import github.kingvampire.DeepTrenches.api.capabilities.lit.ILit;
import github.kingvampire.DeepTrenches.api.entity.HatchetfishEntity;
import github.kingvampire.DeepTrenches.api.entity.goals.AvoidPlayerGoal;
import github.kingvampire.DeepTrenches.api.entity.goals.FollowGroupLeaderGoal;
import github.kingvampire.DeepTrenches.api.entity.goals.RandomGroupSwimGoal;
import github.kingvampire.DeepTrenches.core.entity.goals.hatchetfish.AvoidLoosejawGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class GiantHatchetfishEntity extends HatchetfishEntity {

    public GiantHatchetfishEntity(EntityType<? extends GiantHatchetfishEntity> type, World worldIn) {
	super(type, worldIn);
    }

    public GiantHatchetfishEntity(FMLPlayMessages.SpawnEntity spawnEntity, World worldIn) {
	this(GIANT_HATCHETFISH, worldIn);
    }

    @Override
    protected ItemStack getFishBucket() {
	return new ItemStack(GIANT_HATCHETFISH_BUCKET);
    }

    @Override
    protected SoundEvent getFlopSound() {
	// TODO flop sound
	return null;
    }

    @Override
    protected void registerAttributes() {
	super.registerAttributes();

	this.getAttribute(MAX_HEALTH).setBaseValue(16F);
	this.getAttribute(MOVEMENT_SPEED).setBaseValue(2.17F);
	this.getAttribute(MOVEMENT_SPEED_BOOST).setBaseValue(2.53F);
    }

    @Override
    protected void registerGoals() {
	this.goalSelector.addGoal(2, new AvoidPlayerGoal(this, 8F));
	this.goalSelector.addGoal(2, new AvoidLoosejawGoal(this, 8F));
	this.goalSelector.addGoal(4, new RandomGroupSwimGoal(this));

	this.goalSelector.addGoal(4, new FollowGroupLeaderGoal(this));
    }

    protected void updateLitState() {
	LazyOptional<ILit> lit = this.getCapability(LIT_CAPABILITY);

	if (!this.world.isRemote() && lit.isPresent()) {
	    ILit ilit = lit.orElseThrow(IllegalArgumentException::new);

	    BlockPos pos = this.getPosition();
	    int light = this.world.getLight(pos);

	    if (light >= 3 && light <= 6)
		ilit.setLitState(ALL_LIT);
	    else
		ilit.setLitState(ALL_UNLIT);

	    super.updateLitState();
	}

    }

}
