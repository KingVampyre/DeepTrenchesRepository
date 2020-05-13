package github.kingvampire.DeepTrenches.core.items;

import static github.kingvampire.DeepTrenches.core.init.ItemGroups.GENERAL;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.DEEP_LAKE_BETTA;
import static net.minecraft.entity.SpawnReason.BUCKET;
import static net.minecraft.fluid.Fluids.WATER;
import static net.minecraft.util.text.TextFormatting.GRAY;
import static net.minecraft.util.text.TextFormatting.ITALIC;

import java.util.List;

import github.kingvampire.DeepTrenches.core.entity.BettaEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.FishBucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class DeepLakeBettaBucketItem extends FishBucketItem {

    public DeepLakeBettaBucketItem() {
	super(DEEP_LAKE_BETTA, WATER, new Item.Properties().maxStackSize(1).group(GENERAL));
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
	CompoundNBT compound = stack.getOrCreateChildTag("Entity");

	ITextComponent component = new TranslationTextComponent("text.deep_trenches.entity.deep_lake_betta");
	TextFormatting[] formatting = new TextFormatting[] { ITALIC, GRAY };

	if (!compound.isEmpty())
	    tooltip.add(new TranslationTextComponent("text.deep_trenches.kind", component).applyTextStyles(formatting));
    }

    public void onLiquidPlaced(World worldIn, ItemStack p_203792_2_, BlockPos pos) {

	if (!worldIn.isRemote()) {
	    BettaEntity betta = DEEP_LAKE_BETTA.create(worldIn, null, null, null, pos, BUCKET, true, false);
	    CompoundNBT compound = p_203792_2_.getOrCreateChildTag("Entity");

	    if (betta != null) {
		betta.setFromBucket(true);

		if (!compound.isEmpty())
		    betta.readAdditional(compound);

		worldIn.addEntity(betta);
	    }
	}
    }

}
