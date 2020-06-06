package github.kingvampire.DeepTrenches.core.entity.layers;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import github.kingvampire.DeepTrenches.api.entity.layers.LitLayer;
import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import github.kingvampire.DeepTrenches.core.entity.models.StaspModel;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StaspAntennasLayer extends LitLayer<StaspEntity, StaspModel<StaspEntity>> {

    public StaspAntennasLayer(IEntityRenderer<StaspEntity, StaspModel<StaspEntity>> entityRendererIn) {
	super(entityRendererIn);
    }

    @Override
    protected ResourceLocation getEntityTexture(StaspEntity entityIn) {
	return new ResourceLocation(MODID, "textures/entity/stasp/stasp_antennas.png");
    }

}
