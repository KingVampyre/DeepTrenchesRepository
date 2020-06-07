package github.kingvampire.DeepTrenches.core.entity.renderer;

import static com.mojang.blaze3d.platform.GlStateManager.Profile.TRANSPARENT_MODEL;
import static github.kingvampire.DeepTrenches.api.capabilities.lit.LitProvider.LIT_CAPABILITY;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import com.mojang.blaze3d.platform.GlStateManager;

import github.kingvampire.DeepTrenches.api.capabilities.lit.ILit;
import github.kingvampire.DeepTrenches.api.entity.renderer.TaxonomyRenderer;
import github.kingvampire.DeepTrenches.core.entity.GiantHatchetfishEntity;
import github.kingvampire.DeepTrenches.core.entity.layers.TaxonomyLitLayer;
import github.kingvampire.DeepTrenches.core.entity.models.GiantHatchetfishModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;

public class GiantHatchetfishRenderer
	extends TaxonomyRenderer<GiantHatchetfishEntity, GiantHatchetfishModel<GiantHatchetfishEntity>> {

    public GiantHatchetfishRenderer(EntityRendererManager renderManagerIn) {
	super(renderManagerIn, new GiantHatchetfishModel<>(), 0.3F);

	this.addLayer(new TaxonomyLitLayer<>(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(GiantHatchetfishEntity entity) {
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
    protected void preRenderCallback(GiantHatchetfishEntity entitylivingbaseIn, float partialTickTime) {
	GlStateManager.translatef(0.0F, 0.0F, 0.15F);
    }

    @Override
    protected void renderModel(GiantHatchetfishEntity living, float limbSwing, float limbSwingAmount, float ageInTicks,
	    float netHeadYaw, float headPitch, float scaleFactor) {

	boolean flag = this.isVisible(living);
	boolean flag1 = !flag && !living.isInvisibleToPlayer(Minecraft.getInstance().player);

	if (flag || flag1) {

	    if (!this.bindEntityTexture(living))
		return;

	    if (flag1)
		GlStateManager.setProfile(TRANSPARENT_MODEL);

	    GlStateManager.enableBlend();
	    this.entityModel.render(living, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
	    GlStateManager.disableBlend();

	    if (flag1)
		GlStateManager.unsetProfile(TRANSPARENT_MODEL);

	}

    }

}
