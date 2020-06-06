package github.kingvampire.DeepTrenches.core.entity.layers.dragonfishes;

import com.mojang.blaze3d.platform.GlStateManager;

import github.kingvampire.DeepTrenches.api.entity.layers.CarriedItemLayer;
import github.kingvampire.DeepTrenches.core.entity.BlackLoosejawEntity;
import github.kingvampire.DeepTrenches.core.entity.models.dragonfishes.BlackLoosejawModel;
import net.minecraft.client.renderer.entity.IEntityRenderer;

public class BlackLoosejawCarriedItemLayer
	extends CarriedItemLayer<BlackLoosejawEntity, BlackLoosejawModel<BlackLoosejawEntity>> {

    public BlackLoosejawCarriedItemLayer(
	    IEntityRenderer<BlackLoosejawEntity, BlackLoosejawModel<BlackLoosejawEntity>> entityRendererIn) {
	super(entityRendererIn);
    }

    @Override
    protected void translateCallback(BlackLoosejawEntity creature) {
	GlStateManager.translatef(0, 1.4F, -1.2F);
    }

}
