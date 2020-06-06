package github.kingvampire.DeepTrenches.api.entity;

import static github.kingvampire.DeepTrenches.api.capabilities.lit.LitProvider.LIT_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.taxonomy.TaxonomyProvider.TAXONOMY_CAPABILITY;

import github.kingvampire.DeepTrenches.api.capabilities.lit.ILit;
import github.kingvampire.DeepTrenches.api.entity.goals.GroupPanicGoal;
import github.kingvampire.DeepTrenches.api.taxonomy.RankInstance;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public abstract class HatchetfishEntity extends AbstractFishEntity {

    public static final IAttribute RANDOM_SWIM_CHANCE = new RangedAttribute(null, "generic.randomSwimChance", 0, 0, 100);
    public static final IAttribute MOVEMENT_SPEED_BOOST = new RangedAttribute(null, "generic.movementSpeedBoost", 0.7F, 0, 1024).setShouldWatch(true);

    public HatchetfishEntity(EntityType<? extends AbstractFishEntity> type, World worldIn) {
	super(type, worldIn);
    }

    @Override
    public void livingTick() {
	super.livingTick();

	this.updateLitState();
    }

    @Override
    protected void registerAttributes() {
	super.registerAttributes();

	this.getAttributes().registerAttribute(RANDOM_SWIM_CHANCE).setBaseValue(40);
	this.getAttributes().registerAttribute(MOVEMENT_SPEED_BOOST).setBaseValue(2.5F);
    }

    @Override
    protected void registerGoals() {
	this.goalSelector.addGoal(0, new GroupPanicGoal(this));
    }

    @Override
    protected void setBucketData(ItemStack bucket) {
	super.setBucketData(bucket);

	this.getCapability(TAXONOMY_CAPABILITY).ifPresent(itaxonomy -> {
	    RankInstance rank = itaxonomy.getTaxonomyInstance();

	    bucket.getCapability(TAXONOMY_CAPABILITY).ifPresent(itax -> itax.setTaxonomyInstance(rank));
	});
    }

    protected void updateLitState() {
	LazyOptional<ILit> lit = this.getCapability(LIT_CAPABILITY);

	if (!this.world.isRemote() && lit.isPresent())
	    lit.orElseThrow(IllegalArgumentException::new).sendPacket(this);

    }

}
