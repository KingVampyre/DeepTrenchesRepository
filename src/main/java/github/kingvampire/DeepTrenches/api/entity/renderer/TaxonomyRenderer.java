package github.kingvampire.DeepTrenches.api.entity.renderer;

import static github.kingvampire.DeepTrenches.api.capabilities.taxonomy.TaxonomyProvider.TAXONOMY_CAPABILITY;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import github.kingvampire.DeepTrenches.api.capabilities.taxonomy.ITaxonomy;
import github.kingvampire.DeepTrenches.api.taxonomy.SubspeciesTaxon;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;

public class TaxonomyRenderer<T extends MobEntity, M extends EntityModel<T>> extends MobRenderer<T, M> {

    public TaxonomyRenderer(EntityRendererManager renderManagerIn, M model, float shadow) {
	super(renderManagerIn, model, shadow);
    }

    @Override
    protected ResourceLocation getEntityTexture(T entity) {
	LazyOptional<ITaxonomy> taxonomy = entity.getCapability(TAXONOMY_CAPABILITY);

	if (taxonomy.isPresent()) {
	    ResourceLocation id = entity.getType().getRegistryName();
	    ITaxonomy itaxonomy = taxonomy.orElseThrow(IllegalArgumentException::new);

	    SubspeciesTaxon subspecies = itaxonomy.getSubspecies();

	    if (subspecies != null) {
		String subspicies = subspecies.getVulgarName();

		return new ResourceLocation(MODID, "textures/entity/" + id.getPath() + "/" + subspicies);
	    }

	    return new ResourceLocation(MODID, "textures/entity/" + id.getPath());
	}

	return null;
    }

}
