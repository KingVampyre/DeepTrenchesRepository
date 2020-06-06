package github.kingvampire.DeepTrenches.core.entity.renderer;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import com.mojang.blaze3d.platform.GlStateManager;

import github.kingvampire.DeepTrenches.api.entity.renderer.TaxonomyRenderer;
import github.kingvampire.DeepTrenches.core.entity.BettaEntity;
import github.kingvampire.DeepTrenches.core.entity.models.BettaModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class BettaRenderer extends TaxonomyRenderer<BettaEntity, BettaModel<BettaEntity>> {

    public BettaRenderer(EntityRendererManager entityRenderManager) {
	super(entityRenderManager, new BettaModel<>(), 0.3F);
    }

    @Override
    protected void preRenderCallback(BettaEntity entitylivingbaseIn, float partialTickTime) {
	GlStateManager.translatef(0.0F, -0.1F, -0.1F);
    }

    @Override
    protected ResourceLocation getEntityTexture(BettaEntity entity) {
	ResourceLocation id = super.getEntityTexture(entity);

	return new ResourceLocation(MODID, id.getPath() + ".png");
    }

}
