package github.kingvampire.DeepTrenches.api.entity.renderer;

import static github.kingvampire.DeepTrenches.api.capabilities.lit.LitProvider.LIT_CAPABILITY;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;

import github.kingvampire.DeepTrenches.api.capabilities.lit.ILit;
import github.kingvampire.DeepTrenches.core.entity.layers.TaxonomyLitLayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;

public class DragonfishRenderer<T extends MobEntity, M extends EntityModel<T>> extends TaxonomyRenderer<T, M> {

    protected EntityModel<T> transparent;

    public DragonfishRenderer(EntityRendererManager renderManager, M model, EntityModel<T> transparent, float shadow) {
	super(renderManager, model, shadow);

	this.addLayer(new TaxonomyLitLayer<>(this));

	this.transparent = transparent;
    }

    @Override
    protected ResourceLocation getEntityTexture(T entity) {
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
    protected void renderModel(T living, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
	    float headPitch, float scale) {

	boolean flag = this.isVisible(living);
	boolean flag1 = !flag && living.isInvisibleToPlayer(Minecraft.getInstance().player);

	if (!this.bindEntityTexture(living))
	    return;

	if (flag1) {
	    GlStateManager.color4f(255, 255, 255, 0.5F);
	    GlStateManager.enableBlend();
	    GlStateManager.depthMask(!living.isInvisible());
	    GlStateManager.blendFunc(SourceFactor.CONSTANT_ALPHA, DestFactor.CONSTANT_ALPHA);
	    this.transparent.render(living, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	    GlStateManager.blendFunc(SourceFactor.ONE, DestFactor.ZERO);
	    GlStateManager.depthMask(true);
	    GlStateManager.disableBlend();

	} else {
	    GlStateManager.enableBlend();
	    GlStateManager.depthMask(!living.isInvisible());
	    GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
	    this.entityModel.render(living, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	    GlStateManager.blendFunc(SourceFactor.ONE, DestFactor.ZERO);
	    GlStateManager.disableBlend();
	}

    }

}
