package github.kingvampire.DeepTrenches.core.entity.renderer;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import com.mojang.blaze3d.platform.GlStateManager;

import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import github.kingvampire.DeepTrenches.core.entity.layers.StaspAntennasLayer;
import github.kingvampire.DeepTrenches.core.entity.models.StaspModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class StaspRenderer extends MobRenderer<StaspEntity, StaspModel<StaspEntity>> {

	public StaspRenderer(EntityRendererManager renderManager) {
		super(renderManager, new StaspModel<>(), 0.75F);

		this.addLayer(new StaspAntennasLayer<>(this));
	}

	@Override
	protected void preRenderCallback(StaspEntity entitylivingbaseIn, float partialTickTime) {
		GlStateManager.translatef(-0.1F, 1F, -0.2F);
	}

	@Override
	protected ResourceLocation getEntityTexture(StaspEntity entity) {
		int aqueanSap = entity.getAqueanSap();
		int pollen = entity.getPollen();
		int wingColor = entity.getWingColor();

		if (wingColor == 0) {

			if (pollen > 0)
				return new ResourceLocation(MODID, "textures/entity/stasp/stasp_with_pollen.png");
			else if (aqueanSap > 0)
				return new ResourceLocation(MODID, "textures/entity/stasp/stasp_with_aquean_sap.png");

			return new ResourceLocation(MODID, "textures/entity/stasp/stasp.png");

		} else if (wingColor == 1) {

			if (pollen > 0)
				return new ResourceLocation(MODID, "textures/entity/stasp/blue_stasp_with_pollen.png");
			else if (aqueanSap > 0)
				return new ResourceLocation(MODID, "textures/entity/stasp/blue_stasp_with_aquean_sap.png");

			return new ResourceLocation(MODID, "textures/entity/stasp/blue_stasp.png");

		}

		return null;
	}

}
