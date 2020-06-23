package github.kingvampire.DeepTrenches.api.entity.tileentity;

import static github.kingvampire.DeepTrenches.api.enums.WoodType.ALMOND;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.BOAT;
import static github.kingvampire.DeepTrenches.core.init.ModLootParameters.WOOD_TYPE;
import static net.minecraft.network.datasync.DataSerializers.STRING;
import static net.minecraft.world.GameRules.DO_ENTITY_DROPS;
import static net.minecraft.world.storage.loot.LootParameters.LAST_DAMAGE_PLAYER;

import javax.annotation.Nullable;

import github.kingvampire.DeepTrenches.api.enums.WoodType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameterSet;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class ModBoatEntity extends BoatEntity {

    private static final LootParameterSet BOAT_LOOT = new LootParameterSet.Builder().required(WOOD_TYPE)
	    .optional(LAST_DAMAGE_PLAYER).build();

    private static final DataParameter<String> BOAT_TYPE = EntityDataManager.createKey(ModBoatEntity.class, STRING);

    public ModBoatEntity(EntityType<? extends BoatEntity> entityType, World world) {
	super(entityType, world);

	this.preventEntitySpawning = true;
    }

    public ModBoatEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
	this(BOAT, world);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {

	if (this.isInvulnerableTo(source))
	    return false;

	if (!this.world.isRemote && this.isAlive()) {
	    Entity trueSource = source.getTrueSource();

	    if (source instanceof IndirectEntityDamageSource && trueSource != null && this.isPassenger(trueSource))
		return false;

	    this.setForwardDirection(-this.getForwardDirection());
	    this.setTimeSinceHit(10);
	    this.setDamageTaken(this.getDamageTaken() + amount * 10.0F);
	    this.markVelocityChanged();

	    if (trueSource instanceof PlayerEntity) {
		GameRules gameRules = this.world.getGameRules();
		PlayerEntity player = (PlayerEntity) trueSource;

		if (!player.abilities.isCreativeMode && gameRules.getBoolean(DO_ENTITY_DROPS)) {
		    ServerWorld server = (ServerWorld) this.world;
		    WoodType woodType = this.getWoodType();

		    LootContext.Builder context = new LootContext.Builder(server).withParameter(WOOD_TYPE, woodType)
			    .withNullableParameter(LAST_DAMAGE_PLAYER, player);

		    if (this.getDamageTaken() > 40.0F) {
			this.dropLoot(context);
			this.remove();
		    }

		}

		if (player.abilities.isCreativeMode)
		    this.remove();

	    }
	}

	return true;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
	return NetworkHooks.getEntitySpawningPacket(this);
    }

    public void dropLoot(LootContext.Builder context) {
	LootTableManager manager = this.world.getServer().getLootTableManager();

	ResourceLocation id = this.getType().getLootTable();
	LootTable table = manager.getLootTableFromLocation(id);

	table.generate(context.build(BOAT_LOOT), this::entityDropItem);
    }

    @Override
    public ItemEntity entityDropItem(IItemProvider p_199702_1_, int offset) {
	return null;
    }

    @Nullable
    public ItemEntity entityDropItem(ItemStack stack, float offsetY) {
	if (stack.isEmpty())
	    return null;
	else if (this.world.isRemote)
	    return null;

	ItemEntity itementity = new ItemEntity(this.world, this.posX, this.posY + (double) offsetY, this.posZ, stack);
	itementity.setDefaultPickupDelay();

	if (captureDrops() != null)
	    captureDrops().add(itementity);
	else
	    this.world.addEntity(itementity);

	return itementity;

    }

    public WoodType getWoodType() {
	return WoodType.getType(this.dataManager.get(BOAT_TYPE));
    }

    @Override
    protected void registerData() {
	super.registerData();

	this.dataManager.register(BOAT_TYPE, ALMOND.getName());
    }

    @Override
    public void setRotationYawHead(float rotation) {
	this.rotationYaw = rotation;
    }

    public void setWoodType(WoodType boatType) {
	this.dataManager.set(BOAT_TYPE, boatType.getName());
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
	super.updateFallState(y, onGroundIn, state, pos);

	if (!this.isPassenger() && onGroundIn) {

	    if (this.fallDistance > 3.0F) {

		if (!this.world.isRemote && this.isAlive()) {
		    ServerWorld server = (ServerWorld) this.world;
		    WoodType woodType = this.getWoodType();

		    LootContext.Builder context = new LootContext.Builder(server)
			    .withNullableParameter(LAST_DAMAGE_PLAYER, null).withParameter(WOOD_TYPE, woodType);

		    this.dropLoot(context);
		}
	    }
	}

    }

}