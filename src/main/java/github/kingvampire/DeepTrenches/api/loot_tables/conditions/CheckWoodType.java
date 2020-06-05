package github.kingvampire.DeepTrenches.api.loot_tables.conditions;

import static github.kingvampire.DeepTrenches.core.init.ModLootParameters.WOOD_TYPE;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import java.util.Set;
import java.util.function.Function;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import github.kingvampire.DeepTrenches.api.enums.WoodType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameter;
import net.minecraft.world.storage.loot.LootParameterSet;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.ValidationResults;
import net.minecraft.world.storage.loot.conditions.ILootCondition;

public class CheckWoodType implements ILootCondition {

    public static class Serializer extends AbstractSerializer<CheckWoodType> {

	public Serializer() {
	    super(new ResourceLocation(MODID, "check_wood_type"), CheckWoodType.class);
	}

	public CheckWoodType deserialize(JsonObject json, JsonDeserializationContext context) {
	    WoodType woodType = WoodType.getType(json.get("wood_type").getAsString());

	    return new CheckWoodType(woodType);
	}

	public void serialize(JsonObject json, CheckWoodType value, JsonSerializationContext context) {
	    json.addProperty("wood_type", value.getWoodType().getName());
	}
    }

    private WoodType woodType;

    public CheckWoodType(WoodType woodType) {
	this.woodType = woodType;
    }

    @Override
    public void func_215856_a(ValidationResults p_215856_1_, Function<ResourceLocation, LootTable> p_215856_2_,
	    Set<ResourceLocation> p_215856_3_, LootParameterSet p_215856_4_) {

    }

    @Override
    public Set<LootParameter<?>> getRequiredParameters() {
	return ImmutableSet.of(WOOD_TYPE);
    }

    public WoodType getWoodType() {
	return this.woodType;
    }

    @Override
    public boolean test(LootContext t) {

	if (t.has(WOOD_TYPE))
	    return this.woodType == t.get(WOOD_TYPE);

	return false;
    }

}
