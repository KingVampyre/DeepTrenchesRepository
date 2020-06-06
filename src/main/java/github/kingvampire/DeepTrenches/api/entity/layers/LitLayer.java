package github.kingvampire.DeepTrenches.api.entity.layers;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class LitLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {

    public LitLayer(IEntityRenderer<T, M> entityRendererIn) {
	super(entityRendererIn);
    }

    abstract protected ResourceLocation getEntityTexture(T entityIn);

    @Override
    public void render(T entityIn, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_,
	    float p_212842_6_, float p_212842_7_, float p_212842_8_) {

	this.bindTexture(this.getEntityTexture(entityIn));
	GlStateManager.enableBlend();
	GlStateManager.blendFunc(SourceFactor.ONE, DestFactor.ONE);
	GlStateManager.depthMask(!entityIn.isInvisible());

	this.getEntityModel().render(entityIn, p_212842_2_, p_212842_3_, p_212842_5_, p_212842_6_, p_212842_7_,
		p_212842_8_);

	GlStateManager.disableBlend();
    }

    @Override
    public boolean shouldCombineTextures() {
	return false;
    }

}
