package github.kingvampire.DeepTrenches.core.entity.layers.dragonfishes;

import com.mojang.blaze3d.platform.GlStateManager;

import github.kingvampire.DeepTrenches.api.entity.layers.CarriedItemLayer;
import github.kingvampire.DeepTrenches.core.entity.BarbeledLoosejawEntity;
import github.kingvampire.DeepTrenches.core.entity.models.dragonfishes.BarbeledLoosejawModel;
import net.minecraft.client.renderer.entity.IEntityRenderer;

public class BarbeledLoosejawCarriedItemLayer
	extends CarriedItemLayer<BarbeledLoosejawEntity, BarbeledLoosejawModel<BarbeledLoosejawEntity>> {

    public BarbeledLoosejawCarriedItemLayer(
	    IEntityRenderer<BarbeledLoosejawEntity, BarbeledLoosejawModel<BarbeledLoosejawEntity>> entityRendererIn) {
	super(entityRendererIn);
    }

    @Override
    protected void translateCallback(BarbeledLoosejawEntity creature) {
	GlStateManager.translatef(0, 1.4F, -1.45F);
    }

}
