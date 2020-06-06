package github.kingvampire.DeepTrenches.core.entity.renderer.dragonfishes;

import com.mojang.blaze3d.platform.GlStateManager;

import github.kingvampire.DeepTrenches.api.entity.renderer.DragonfishRenderer;
import github.kingvampire.DeepTrenches.core.entity.BarbeledLoosejawEntity;
import github.kingvampire.DeepTrenches.core.entity.layers.dragonfishes.BarbeledLoosejawCarriedItemLayer;
import github.kingvampire.DeepTrenches.core.entity.models.dragonfishes.BarbeledLoosejawModel;
import github.kingvampire.DeepTrenches.core.entity.models.dragonfishes.BarbeledLoosejawTransparentModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class BarbeledLoosejawRenderer
	extends DragonfishRenderer<BarbeledLoosejawEntity, BarbeledLoosejawModel<BarbeledLoosejawEntity>> {

    public BarbeledLoosejawRenderer(EntityRendererManager renderManager) {
	super(renderManager, new BarbeledLoosejawModel<>(), new BarbeledLoosejawTransparentModel<>(), 0.4F);

	this.addLayer(new BarbeledLoosejawCarriedItemLayer(this));
    }

    @Override
    protected void preRenderCallback(BarbeledLoosejawEntity entitylivingbaseIn, float partialTickTime) {
	GlStateManager.translatef(0.0F, 0.08F, 0.15F);
    }

}
