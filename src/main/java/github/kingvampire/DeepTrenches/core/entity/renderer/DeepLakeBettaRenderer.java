package github.kingvampire.DeepTrenches.core.entity.renderer;

import static github.kingvampire.DeepTrenches.api.capabilities.lit.LitProvider.LIT_CAPABILITY;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import com.mojang.blaze3d.platform.GlStateManager;

import github.kingvampire.DeepTrenches.api.capabilities.lit.ILit;
import github.kingvampire.DeepTrenches.api.entity.renderer.TaxonomyRenderer;
import github.kingvampire.DeepTrenches.core.entity.DeepLakeBettaEntity;
import github.kingvampire.DeepTrenches.core.entity.layers.TaxonomyLitLayer;
import github.kingvampire.DeepTrenches.core.entity.models.DeepLakeBettaModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;

public class DeepLakeBettaRenderer
	extends TaxonomyRenderer<DeepLakeBettaEntity, DeepLakeBettaModel<DeepLakeBettaEntity>> {

    public DeepLakeBettaRenderer(EntityRendererManager entityRenderManager) {
	super(entityRenderManager, new DeepLakeBettaModel<>(), 0.35F);

	this.addLayer(new TaxonomyLitLayer<>(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(DeepLakeBettaEntity entity) {
	LazyOptional<ILit> lit = entity.getCapability(LIT_CAPABILITY);
	ResourceLocation id = super.getEntityTexture(entity);

	if (lit.isPresent()) {
	    ILit ilit = lit.orElseThrow(IllegalArgumentException::new);

	    if (id != null)
		return new ResourceLocation(MODID, id.getPath() + "/" + ilit.getLitState() + ".png");
	}

	return null;
    }

    @Override
    protected void preRenderCallback(DeepLakeBettaEntity entitylivingbaseIn, float partialTickTime) {
	GlStateManager.translatef(0, 1.1F, 0);
    }

}
