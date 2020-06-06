package github.kingvampire.DeepTrenches.core.entity.layers.dragonfishes;

import com.mojang.blaze3d.platform.GlStateManager;

import github.kingvampire.DeepTrenches.api.entity.layers.CarriedItemLayer;
import github.kingvampire.DeepTrenches.core.entity.LightLoosejawEntity;
import github.kingvampire.DeepTrenches.core.entity.models.dragonfishes.LightLoosejawModel;
import net.minecraft.client.renderer.entity.IEntityRenderer;

public class LightLoosejawCarriedItemLayer
	extends CarriedItemLayer<LightLoosejawEntity, LightLoosejawModel<LightLoosejawEntity>> {

    public LightLoosejawCarriedItemLayer(
	    IEntityRenderer<LightLoosejawEntity, LightLoosejawModel<LightLoosejawEntity>> entityRendererIn) {
	super(entityRendererIn);
    }

    @Override
    protected void translateCallback(LightLoosejawEntity creature) {
	GlStateManager.translatef(0, 1.4F, -1.4F);
    }

}
