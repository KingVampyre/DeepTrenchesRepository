package github.kingvampire.DeepTrenches.core.entity.layers;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;

import github.kingvampire.DeepTrenches.core.entity.models.StaspModel;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StaspAntennasLayer<T extends LivingEntity> extends LayerRenderer<T, StaspModel<T>> {

	private static final ResourceLocation STASP_ANTENNAS = new ResourceLocation(MODID,
			"textures/entity/stasp/stasp_antennas.png");

	public StaspAntennasLayer(IEntityRenderer<T, StaspModel<T>> entityRendererIn) {
		super(entityRendererIn);
	}

	@Override
	public void render(T entityIn, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_,
			float p_212842_6_, float p_212842_7_, float p_212842_8_) {

		this.bindTexture(STASP_ANTENNAS);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(SourceFactor.ONE, DestFactor.ONE);
		GlStateManager.depthMask(!entityIn.isInvisible());

		StaspModel<T> model = this.getEntityModel();

		model.render(entityIn, p_212842_2_, p_212842_3_, p_212842_5_, p_212842_6_, p_212842_7_, p_212842_8_);

		GlStateManager.disableBlend();
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}

}
