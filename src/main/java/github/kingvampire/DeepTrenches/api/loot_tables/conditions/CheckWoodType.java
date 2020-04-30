package github.kingvampire.DeepTrenches.api.loot_tables.conditions;

import static github.kingvampire.DeepTrenches.core.entity.BoatEntityDT.WOOD_TYPE;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import github.kingvampire.DeepTrenches.api.enums.WoodType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameter;
import net.minecraft.world.storage.loot.conditions.ILootCondition;

public class CheckWoodType implements ILootCondition {

	private WoodType woodType;

	public CheckWoodType(WoodType woodType) {
		this.woodType = woodType;
	}

	@Override
	public Set<LootParameter<?>> getRequiredParameters() {
		return ImmutableSet.of(WOOD_TYPE);
	}

	@Override
	public boolean test(LootContext t) {

		if (t.has(WOOD_TYPE))
			return this.woodType == t.get(WOOD_TYPE);

		return false;
	}

	public WoodType getWoodType() {
		return this.woodType;
	}

	public static class Serializer extends AbstractSerializer<CheckWoodType> {

		public Serializer() {
			super(new ResourceLocation(MODID, "check_wood_type"), CheckWoodType.class);
		}

		public void serialize(JsonObject json, CheckWoodType value, JsonSerializationContext context) {
			json.addProperty("wood_type", value.getWoodType().getName());
		}

		public CheckWoodType deserialize(JsonObject json, JsonDeserializationContext context) {
			WoodType woodType = WoodType.getType(json.get("wood_type").getAsString());

			return new CheckWoodType(woodType);
		}
	}

}
