package github.kingvampire.DeepTrenches.core.entity.renderer.dragonfishes;

import com.mojang.blaze3d.platform.GlStateManager;

import github.kingvampire.DeepTrenches.api.entity.renderer.DragonfishRenderer;
import github.kingvampire.DeepTrenches.core.entity.BlackLoosejawEntity;
import github.kingvampire.DeepTrenches.core.entity.layers.dragonfishes.BlackLoosejawCarriedItemLayer;
import github.kingvampire.DeepTrenches.core.entity.models.dragonfishes.BlackLoosejawModel;
import github.kingvampire.DeepTrenches.core.entity.models.dragonfishes.BlackLoosejawTransparentModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class BlackLoosejawRenderer
	extends DragonfishRenderer<BlackLoosejawEntity, BlackLoosejawModel<BlackLoosejawEntity>> {

    public BlackLoosejawRenderer(EntityRendererManager renderManager) {
	super(renderManager, new BlackLoosejawModel<>(), new BlackLoosejawTransparentModel<>(), 0.4F);
	
	this.addLayer(new BlackLoosejawCarriedItemLayer(this));
    }

    @Override
    protected void preRenderCallback(BlackLoosejawEntity entitylivingbaseIn, float partialTickTime) {
	GlStateManager.translatef(0.0F, 0.08F, -0.1F);
    }

}
