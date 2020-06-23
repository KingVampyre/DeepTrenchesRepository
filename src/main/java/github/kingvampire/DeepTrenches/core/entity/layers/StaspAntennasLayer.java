package github.kingvampire.DeepTrenches.core.entity.layers;

import static github.kingvampire.DeepTrenches.api.capabilities.taxonomy.TaxonomyProvider.TAXONOMY_CAPABILITY;
import static github.kingvampire.DeepTrenches.core.init.ModTaxons.OBSCURUS;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraftforge.api.distmarker.Dist.CLIENT;

import github.kingvampire.DeepTrenches.api.capabilities.taxonomy.ITaxonomy;
import github.kingvampire.DeepTrenches.api.entity.layers.LitLayer;
import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import github.kingvampire.DeepTrenches.core.entity.models.StaspModel;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.LazyOptional;

@OnlyIn(CLIENT)
public class StaspAntennasLayer extends LitLayer<StaspEntity, StaspModel<StaspEntity>> {

    public StaspAntennasLayer(IEntityRenderer<StaspEntity, StaspModel<StaspEntity>> entityRendererIn) {
	super(entityRendererIn);
    }

    @Override
    protected ResourceLocation getEntityTexture(StaspEntity entityIn) {
	LazyOptional<ITaxonomy> taxonomy = entityIn.getCapability(TAXONOMY_CAPABILITY);

	if (taxonomy.isPresent()) {
	    ITaxonomy itaxonomy = taxonomy.orElseThrow(IllegalArgumentException::new);

	    if (itaxonomy.getSubspecies() == OBSCURUS)
		return new ResourceLocation(MODID, "textures/entity/stasp/black/antennas_layer.png");

	}

	return new ResourceLocation(MODID, "textures/entity/stasp/antennas_layer.png");
    }

}
