package github.kingvampire.DeepTrenches.core.entity.renderer.dragonfishes;

import com.mojang.blaze3d.platform.GlStateManager;

import github.kingvampire.DeepTrenches.api.entity.renderer.DragonfishRenderer;
import github.kingvampire.DeepTrenches.core.entity.SmalltoothDragonfishEntity;
import github.kingvampire.DeepTrenches.core.entity.layers.dragonfishes.SmalltoothDragonfishCarriedItemLayer;
import github.kingvampire.DeepTrenches.core.entity.models.dragonfishes.SmalltoothDragonfishModel;
import github.kingvampire.DeepTrenches.core.entity.models.dragonfishes.SmalltoothDragonfishTransparentModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class SmalltoothDragonfishRenderer
	extends DragonfishRenderer<SmalltoothDragonfishEntity, SmalltoothDragonfishModel<SmalltoothDragonfishEntity>> {

    public SmalltoothDragonfishRenderer(EntityRendererManager renderManager) {
	super(renderManager, new SmalltoothDragonfishModel<>(), new SmalltoothDragonfishTransparentModel<>(), 0.4F);

	this.addLayer(new SmalltoothDragonfishCarriedItemLayer(this));
    }

    @Override
    protected void preRenderCallback(SmalltoothDragonfishEntity entitylivingbaseIn, float partialTickTime) {
	GlStateManager.translatef(0.0F, 0.08F, 0.15F);
    }

}
