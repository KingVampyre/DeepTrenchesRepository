package github.kingvampire.DeepTrenches.core.entity;

import static github.kingvampire.DeepTrenches.api.enums.WoodType.ALMOND;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.BOAT;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.network.datasync.DataSerializers.VARINT;
import static net.minecraft.world.storage.loot.LootParameters.DAMAGE_SOURCE;
import static net.minecraft.world.storage.loot.LootParameters.DIRECT_KILLER_ENTITY;
import static net.minecraft.world.storage.loot.LootParameters.KILLER_ENTITY;
import static net.minecraft.world.storage.loot.LootParameters.LAST_DAMAGE_PLAYER;
import static net.minecraft.world.storage.loot.LootParameters.POSITION;
import static net.minecraft.world.storage.loot.LootParameters.THIS_ENTITY;

import github.kingvampire.DeepTrenches.api.enums.WoodType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootContext.Builder;
import net.minecraft.world.storage.loot.LootParameter;
import net.minecraft.world.storage.loot.LootParameterSet;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class BoatEntityDT extends BoatEntity {

	private static final DataParameter<Integer> BOAT_TYPE = EntityDataManager.createKey(BoatEntityDT.class, VARINT);
	public static final LootParameter<WoodType> WOOD_TYPE = new LootParameter<>(new ResourceLocation(MODID, "wood_type"));

	public static final LootParameterSet BOAT_ENTITY = new LootParameterSet.Builder()
			.required(THIS_ENTITY)
			.required(POSITION)
			.required(DAMAGE_SOURCE)
			.required(WOOD_TYPE)
			.optional(KILLER_ENTITY)
			.optional(DIRECT_KILLER_ENTITY)
			.optional(LAST_DAMAGE_PLAYER)
			.build();

	public BoatEntityDT(EntityType<? extends BoatEntity> entityType, World world) {
		super(entityType, world);

		this.preventEntitySpawning = true;
	}

	public BoatEntityDT(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
		this(BOAT, world);
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	public void dropLoot(DamageSource source) {
		LootTableManager manager = this.world.getServer().getLootTableManager();
		ServerWorld server = (ServerWorld) this.world;

		ResourceLocation id = this.getType().getLootTable();
		LootTable table = manager.getLootTableFromLocation(id);

		Builder context = new LootContext.Builder(server)
				.withRandom(this.rand).withParameter(THIS_ENTITY, this)
				.withParameter(POSITION, this.getPosition())
				.withParameter(DAMAGE_SOURCE, source)
				.withParameter(WOOD_TYPE, this.getWoodType())
				.withNullableParameter(KILLER_ENTITY, source.getTrueSource())
				.withNullableParameter(DIRECT_KILLER_ENTITY, source.getImmediateSource());

		table.generate(context.build(BOAT_ENTITY), this::entityDropItem);
	}

	public WoodType getWoodType() {
		return WoodType.values()[this.dataManager.get(BOAT_TYPE)];
	}

	@Override
	protected void registerData() {
		super.registerData();

		this.dataManager.register(BOAT_TYPE, ALMOND.ordinal());
	}

	@Override
	public void setRotationYawHead(float rotation) {
		this.rotationYaw = rotation;
	}

	public void setWoodType(WoodType boatType) {
		this.dataManager.set(BOAT_TYPE, boatType.ordinal());
	}

}