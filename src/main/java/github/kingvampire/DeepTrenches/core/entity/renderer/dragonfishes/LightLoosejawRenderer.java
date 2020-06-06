package github.kingvampire.DeepTrenches.core.entity.renderer.dragonfishes;

import com.mojang.blaze3d.platform.GlStateManager;

import github.kingvampire.DeepTrenches.api.entity.renderer.DragonfishRenderer;
import github.kingvampire.DeepTrenches.core.entity.LightLoosejawEntity;
import github.kingvampire.DeepTrenches.core.entity.layers.dragonfishes.LightLoosejawCarriedItemLayer;
import github.kingvampire.DeepTrenches.core.entity.models.dragonfishes.LightLoosejawModel;
import github.kingvampire.DeepTrenches.core.entity.models.dragonfishes.LightLoosejawTransparentModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class LightLoosejawRenderer
	extends DragonfishRenderer<LightLoosejawEntity, LightLoosejawModel<LightLoosejawEntity>> {

    public LightLoosejawRenderer(EntityRendererManager renderManager) {
	super(renderManager, new LightLoosejawModel<>(), new LightLoosejawTransparentModel<>(), 0.4F);

	this.addLayer(new LightLoosejawCarriedItemLayer(this));
    }

    @Override
    protected void preRenderCallback(LightLoosejawEntity entitylivingbaseIn, float partialTickTime) {
	GlStateManager.translatef(0.0F, 0.08F, -0.005F);
    }

}
