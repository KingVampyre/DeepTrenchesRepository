package github.kingvampire.DeepTrenches.core.entity;

import static github.kingvampire.DeepTrenches.core.init.ModEntities.DEEP_LAKE_BETTA;
import static net.minecraft.entity.SharedMonsterAttributes.ATTACK_DAMAGE;
import static net.minecraft.entity.SharedMonsterAttributes.MAX_HEALTH;
import static net.minecraft.entity.SharedMonsterAttributes.MOVEMENT_SPEED;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class DeepLakeBettaEntity extends BettaEntity {

    public DeepLakeBettaEntity(EntityType<? extends BettaEntity> type, World worldIn) {
	super(type, worldIn);
    }

    public DeepLakeBettaEntity(FMLPlayMessages.SpawnEntity spawnEntity, World worldIn) {
	this(DEEP_LAKE_BETTA, worldIn);
    }

    @Override
    protected void registerAttributes() {
	super.registerAttributes();

	this.getAttribute(ATTACK_DAMAGE).setBaseValue(4F);
	this.getAttribute(MAX_HEALTH).setBaseValue(5F);
	this.getAttribute(MOVEMENT_SPEED).setBaseValue(0.71F);
    }
    
}
