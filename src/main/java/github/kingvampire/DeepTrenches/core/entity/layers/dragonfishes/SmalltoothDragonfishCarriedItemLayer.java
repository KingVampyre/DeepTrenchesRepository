package github.kingvampire.DeepTrenches.core.entity.layers.dragonfishes;

import com.mojang.blaze3d.platform.GlStateManager;

import github.kingvampire.DeepTrenches.api.entity.layers.CarriedItemLayer;
import github.kingvampire.DeepTrenches.core.entity.SmalltoothDragonfishEntity;
import github.kingvampire.DeepTrenches.core.entity.models.dragonfishes.SmalltoothDragonfishModel;
import net.minecraft.client.renderer.entity.IEntityRenderer;

public class SmalltoothDragonfishCarriedItemLayer
	extends CarriedItemLayer<SmalltoothDragonfishEntity, SmalltoothDragonfishModel<SmalltoothDragonfishEntity>> {

    public SmalltoothDragonfishCarriedItemLayer(
	    IEntityRenderer<SmalltoothDragonfishEntity, SmalltoothDragonfishModel<SmalltoothDragonfishEntity>> entityRendererIn) {
	super(entityRendererIn);
    }

    @Override
    protected void translateCallback(SmalltoothDragonfishEntity creature) {
	GlStateManager.translatef(0, 1.4F, -1.45F);
    }

}
