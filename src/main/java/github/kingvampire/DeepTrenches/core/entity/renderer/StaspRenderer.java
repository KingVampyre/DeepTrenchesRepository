package github.kingvampire.DeepTrenches.core.entity.renderer;

import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.pollen.PollenProvider.POLLEN_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.taxonomy.TaxonomyProvider.TAXONOMY_CAPABILITY;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import com.mojang.blaze3d.platform.GlStateManager;

import github.kingvampire.DeepTrenches.api.capabilities.anger.IAnger;
import github.kingvampire.DeepTrenches.api.capabilities.pollen.IPollen;
import github.kingvampire.DeepTrenches.api.capabilities.taxonomy.ITaxonomy;
import github.kingvampire.DeepTrenches.api.entity.renderer.TaxonomyRenderer;
import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import github.kingvampire.DeepTrenches.core.entity.layers.StaspAntennasLayer;
import github.kingvampire.DeepTrenches.core.entity.models.StaspModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;

public class StaspRenderer extends TaxonomyRenderer<StaspEntity, StaspModel<StaspEntity>> {

    public StaspRenderer(EntityRendererManager entityRenderManager) {
	super(entityRenderManager, new StaspModel<>(), 0.75F);

	this.addLayer(new StaspAntennasLayer(this));
    }

    @Override
    protected void preRenderCallback(StaspEntity entitylivingbaseIn, float partialTickTime) {
	GlStateManager.translatef(-0.1F, 1F, -0.2F);
    }

    @Override
    protected ResourceLocation getEntityTexture(StaspEntity entity) {
	ResourceLocation id = super.getEntityTexture(entity);

	LazyOptional<IAnger> anger = entity.getCapability(ANGER_CAPABILITY);
	LazyOptional<IPollen> pollen = entity.getCapability(POLLEN_CAPABILITY);
	LazyOptional<ITaxonomy> taxonomy = entity.getCapability(TAXONOMY_CAPABILITY);

	if (anger.isPresent() && pollen.isPresent() && taxonomy.isPresent()) {
	    IAnger ianger = anger.orElseThrow(IllegalArgumentException::new);
	    IPollen ipollen = pollen.orElseThrow(IllegalArgumentException::new);
	    ITaxonomy itaxonomy = taxonomy.orElseThrow(IllegalArgumentException::new);

	    String species = itaxonomy.getSpecies().getVulgarName();
	    String subspecies = itaxonomy.getSubspecies().getVulgarName();

	    String prefix = subspecies + "_" + species;

	    if (ianger.isAngry()) {

		if (ipollen.hasPollen())
		    return new ResourceLocation(MODID, id.getPath() + "/angry/with_pollen.png");
		else if (ipollen.hasAqueanSap())
		    return new ResourceLocation(MODID, id.getPath() + "/angry/with_aquean_sap.png");

		return new ResourceLocation(MODID, id.getPath() + "/angry/" + prefix + ".png");
	    }

	    if (ipollen.hasPollen())
		return new ResourceLocation(MODID, id.getPath() + "/with_pollen.png");
	    else if (ipollen.hasAqueanSap())
		return new ResourceLocation(MODID, id.getPath() + "/with_aquean_sap.png");

	    return new ResourceLocation(MODID, id.getPath() + "/" + prefix + ".png");
	}

	return null;
    }

}
