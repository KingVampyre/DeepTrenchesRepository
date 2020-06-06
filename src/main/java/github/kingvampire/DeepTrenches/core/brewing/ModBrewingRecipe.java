package github.kingvampire.DeepTrenches.core.brewing;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.common.brewing.BrewingRecipe;

public class ModBrewingRecipe extends BrewingRecipe {

    public ModBrewingRecipe(Ingredient input, Item ingredient, ItemStack output) {
	super(input, Ingredient.fromItems(ingredient), output);
    }

    @Override
    public boolean isIngredient(ItemStack ingredient) {
	ItemStack output = this.getOutput();

	for (ItemStack match : this.getInput().getMatchingStacks()) {

	    if (match.getItem() == output.getItem())
		if (PotionUtils.getPotionFromItem(match) == PotionUtils.getPotionFromItem(output))
		    return false;
	}

	return super.isIngredient(ingredient);
    }

    @Override
    public boolean isInput(ItemStack stack) {

	if (super.isInput(stack)) {
	    Ingredient input = this.getInput();

	    for (ItemStack match : input.getMatchingStacks()) {

		if (match.getItem() == stack.getItem())
		    return PotionUtils.getPotionFromItem(match) == PotionUtils.getPotionFromItem(stack);
	    }
	}

	return false;
    }

}
