package github.kingvampire.DeepTrenches.core.items;

import static github.kingvampire.DeepTrenches.api.capabilities.taxonomy.TaxonomyProvider.TAXONOMY_CAPABILITY;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static github.kingvampire.DeepTrenches.core.util.NetworkHandler.INSTANCE;
import static net.minecraft.entity.SpawnReason.BUCKET;
import static net.minecraft.fluid.Fluids.WATER;
import static net.minecraft.util.text.TextFormatting.GRAY;
import static net.minecraft.util.text.TextFormatting.ITALIC;
import static net.minecraftforge.fml.network.PacketDistributor.TRACKING_ENTITY_AND_SELF;

import java.util.List;

import github.kingvampire.DeepTrenches.api.capabilities.taxonomy.ITaxonomy;
import github.kingvampire.DeepTrenches.api.taxonomy.RankInstance;
import github.kingvampire.DeepTrenches.api.taxonomy.SpeciesTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.SubspeciesTaxon;
import github.kingvampire.DeepTrenches.core.util.packets.TaxonomyCapabilityPacket;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.item.FishBucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor.PacketTarget;

public class ModFishBucketItem extends FishBucketItem {

    protected EntityType<? extends AbstractFishEntity> type;

    public ModFishBucketItem(EntityType<? extends AbstractFishEntity> fishTypeIn, Properties builder) {
	super(fishTypeIn, WATER, builder);

	this.type = fishTypeIn;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

	ITextComponent component = this.getTextComponent(stack);
	TextFormatting[] formatting = this.getTextFormatting();

	if (component != null) {
	    ITextComponent textComponent = new TranslationTextComponent("text." + MODID + ".subspecies", component);

	    tooltip.add(textComponent.applyTextStyles(formatting));
	}
    }

    @Override
    public CompoundNBT getShareTag(ItemStack stack) {
	return stack.serializeNBT();
    }

    public ITextComponent getTextComponent(ItemStack stack) {
	LazyOptional<ITaxonomy> taxonomy = stack.getCapability(TAXONOMY_CAPABILITY);

	if (taxonomy.isPresent()) {
	    ITaxonomy itaxonomy = taxonomy.orElseThrow(IllegalArgumentException::new);

	    if (itaxonomy.getTaxonomyInstance() != null) {
		SpeciesTaxon species = itaxonomy.getSpecies();
		SubspeciesTaxon subspecies = itaxonomy.getSubspecies();

		if (subspecies != null)
		    return new TranslationTextComponent(
			    "text." + MODID + ".entity." + species.getVulgarName() + "." + subspecies.getVulgarName());
	    }

	}

	return null;
    }

    public TextFormatting[] getTextFormatting() {
	return new TextFormatting[] { ITALIC, GRAY };
    }

    // TODO save ITame and IAge functionality
    @Override
    public void onLiquidPlaced(World worldIn, ItemStack p_203792_2_, BlockPos pos) {

	if (!worldIn.isRemote()) {
	    LazyOptional<ITaxonomy> taxonomy = p_203792_2_.getCapability(TAXONOMY_CAPABILITY);

	    if (taxonomy.isPresent()) {
		AbstractFishEntity entity = this.type.spawn(worldIn, null, null, null, pos, BUCKET, true, false);

		ITaxonomy itaxonomy = taxonomy.orElseThrow(IllegalArgumentException::new);
		RankInstance rank = itaxonomy.getTaxonomyInstance();

		if (rank != null) {
		    LazyOptional<ITaxonomy> tax = entity.getCapability(TAXONOMY_CAPABILITY);

		    if (tax.isPresent()) {
			ITaxonomy itax = tax.orElseThrow(IllegalArgumentException::new);

			if (itaxonomy.getSubspecies() != null) {
			    PacketTarget target = TRACKING_ENTITY_AND_SELF.with(() -> entity);

			    itax.setTaxonomyInstance(rank);
			    entity.setFromBucket(true);

			    INSTANCE.send(target, new TaxonomyCapabilityPacket(entity, rank));
			}
		    }
		}
	    }

	}

    }

    @Override
    public void readShareTag(ItemStack stack, CompoundNBT nbt) {
	super.readShareTag(stack, nbt);

	stack.deserializeNBT(nbt);
    }

}
