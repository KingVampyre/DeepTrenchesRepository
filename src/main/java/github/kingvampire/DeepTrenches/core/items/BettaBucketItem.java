package github.kingvampire.DeepTrenches.core.items;

import static github.kingvampire.DeepTrenches.core.init.ItemGroups.GENERAL;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.BETTA;
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

public class BettaBucketItem extends FishBucketItem {

    public BettaBucketItem() {
	super(BETTA, WATER, new Item.Properties().maxStackSize(1).group(GENERAL));
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
	CompoundNBT compound = stack.getOrCreateChildTag("Entity");
	TextFormatting[] formatting = new TextFormatting[] { ITALIC, GRAY };

	ITextComponent component;

	switch (compound.getInt("Color")) {
	case 0:
	    component = new TranslationTextComponent("text.deep_trenches.entity.blue_betta");
	    break;
	case 1:
	    component = new TranslationTextComponent("text.deep_trenches.entity.colorful_betta");
	    break;
	case 2:
	    component = new TranslationTextComponent("text.deep_trenches.entity.red_betta");
	    break;
	case 3:
	    component = new TranslationTextComponent("text.deep_trenches.entity.icarus_junior");
	    break;
	case 4:
	    component = new TranslationTextComponent("text.deep_trenches.entity.icarus");
	    break;
	default:
	    component = stack.getDisplayName();
	}

	if (!compound.isEmpty())
	    tooltip.add(new TranslationTextComponent("text.deep_trenches.kind", component).applyTextStyles(formatting));
    }

    public void onLiquidPlaced(World worldIn, ItemStack p_203792_2_, BlockPos pos) {

	if (!worldIn.isRemote()) {
	    BettaEntity betta = BETTA.create(worldIn, null, null, null, pos, BUCKET, true, false);
	    CompoundNBT compound = p_203792_2_.getOrCreateChildTag("Entity");

	    if (betta != null) {
		betta.setFromBucket(true);

		if (compound.isEmpty())
		    betta.setColor(betta.getRNG().nextInt(5));
		else
		    betta.readAdditional(compound);

		worldIn.addEntity(betta);
	    }
	}
    }

}
