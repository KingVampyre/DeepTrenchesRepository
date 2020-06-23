package github.kingvampire.DeepTrenches.core.entity.capability.group;

import static github.kingvampire.DeepTrenches.api.capabilities.taxonomy.TaxonomyProvider.TAXONOMY_CAPABILITY;

import com.google.common.collect.Maps;

import github.kingvampire.DeepTrenches.api.capabilities.group.Group;
import github.kingvampire.DeepTrenches.api.capabilities.taxonomy.ITaxonomy;
import github.kingvampire.DeepTrenches.core.entity.StaspNestTileEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public class StaspNestGroup extends Group {

    protected final StaspNestTileEntity tileEntity;

    public StaspNestGroup() {
	// TODO Default Constructor

	this.tileEntity = null;
    }

    public StaspNestGroup(StaspNestTileEntity tileEntity, int maxGroupSize) {
	super(null, maxGroupSize);

	this.memberTicks = Maps.newHashMap();
	this.tileEntity = tileEntity;
    }

    @Override
    public boolean enterGroup(CreatureEntity creature) {

	if (this.members.add(creature))
	    return this.memberTicks.put(creature, 6000 + creature.getRNG().nextInt(1200)) > 0;

	return false;
    }

    public StaspNestTileEntity getTileEntity() {
	return this.tileEntity;
    }

    @Override
    public World getWorld() {
	return this.tileEntity.getWorld();
    }

    @Override
    public boolean inRangeOfGroup() {
	return false;
    }

    @Override
    public boolean leaveGroup() {
	return false;
    }

    public void onDisturb(LivingEntity living) {

	for (CreatureEntity stasp : this.getMembers()) {
	    stasp.revive();
	    stasp.setRevengeTarget(living);

	    stasp.world.addEntity(stasp);

	    LazyOptional<ITaxonomy> taxonomy = stasp.getCapability(TAXONOMY_CAPABILITY);

	    if (taxonomy.isPresent()) {
		ITaxonomy itaxonomy = taxonomy.orElseThrow(IllegalArgumentException::new);

		itaxonomy.sendPacket(stasp);
	    }

	}

	this.members.clear();
	this.memberTicks.clear();
    }

    @Override
    public void tick() {

	for (CreatureEntity creature : this.members) {

	    if (!this.memberTicks.containsKey(creature))
		this.memberTicks.put(creature, 6000 + creature.getRNG().nextInt(1200));

	    int ticksLeft = this.memberTicks.computeIfPresent(creature, (key, ticks) -> --ticks);

	    if (ticksLeft == 0) {
		creature.revive();
		creature.world.addEntity(creature);

		LazyOptional<ITaxonomy> taxonomy = creature.getCapability(TAXONOMY_CAPABILITY);

		if (taxonomy.isPresent()) {
		    ITaxonomy itaxonomy = taxonomy.orElseThrow(IllegalArgumentException::new);

		    itaxonomy.sendPacket(creature);
		}

		this.memberTicks.remove(creature);
		this.members.remove(creature);
	    }

	}
    }

}
