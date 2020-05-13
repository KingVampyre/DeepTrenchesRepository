package github.kingvampire.DeepTrenches.core.entity.renderer;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import com.mojang.blaze3d.platform.GlStateManager;

import github.kingvampire.DeepTrenches.core.entity.BettaEntity;
import github.kingvampire.DeepTrenches.core.entity.layers.DeepLakeBettaBodyLayer;
import github.kingvampire.DeepTrenches.core.entity.layers.DeepLakeBettaLayer;
import github.kingvampire.DeepTrenches.core.entity.layers.DeepLakeBettaLureLayer;
import github.kingvampire.DeepTrenches.core.entity.models.DeepLakeBettaModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DeepLakeBettaRenderer extends MobRenderer<BettaEntity, DeepLakeBettaModel<BettaEntity>> {

    public DeepLakeBettaRenderer(EntityRendererManager entityRenderManager) {
	super(entityRenderManager, new DeepLakeBettaModel<BettaEntity>(), 0.35F);

	this.addLayer(new DeepLakeBettaLureLayer<BettaEntity>(this));
	this.addLayer(new DeepLakeBettaLayer<BettaEntity>(this));
	this.addLayer(new DeepLakeBettaBodyLayer<BettaEntity>(this));
    }

    @Override
    protected void preRenderCallback(BettaEntity entitylivingbaseIn, float partialTickTime) {
	GlStateManager.translatef(0, 1.1F, 0);
    }

    @Override
    protected ResourceLocation getEntityTexture(BettaEntity entity) {
	BlockPos pos = entity.getPosition();
	World world = entity.getEntityWorld();

	if (entity.getAttackingEntity() != null || world.getLight(pos) < 5 && world.getGameTime() % 10 == 0)
	    return new ResourceLocation(MODID, "textures/entity/betta/deep_lake_betta.png");

	if (world.getGameTime() % 10 == 0)
	    return new ResourceLocation(MODID, "textures/entity/betta/deep_lake_betta_lure.png");

	if (world.getLight(pos) < 5)
	    return new ResourceLocation(MODID, "textures/entity/betta/deep_lake_betta_body.png");

	return new ResourceLocation(MODID, "textures/entity/betta/deep_lake_betta_unlit.png");
    }

}
