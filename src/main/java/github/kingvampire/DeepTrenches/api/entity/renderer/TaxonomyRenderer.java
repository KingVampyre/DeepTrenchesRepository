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
	    ITaxonomy itaxonomy = taxonomy.orElseThrow(IllegalArgumentException::new);

	    String family = itaxonomy.getFamily().getVulgarName();
	    String genus = itaxonomy.getGenus().getVulgarName();
	    String order = itaxonomy.getOrder().getVulgarName();
	    String species = itaxonomy.getSpecies().getVulgarName();

	    String rank = String.format("textures/entity/%1$s/%2$s/%3$s/%4$s", order, family, genus, species);
	    SubspeciesTaxon subspecies = itaxonomy.getSubspecies();

	    if (subspecies != null) {
		String subspicies = subspecies.getVulgarName();

		return new ResourceLocation(MODID, rank + "/" + subspicies);
	    }

	    return new ResourceLocation(MODID, rank);
	}

	return null;
    }

}
