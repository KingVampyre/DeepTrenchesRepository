package github.kingvampire.DeepTrenches.core.entity.layers;

import static github.kingvampire.DeepTrenches.api.capabilities.lit.LitProvider.LIT_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.taxonomy.TaxonomyProvider.TAXONOMY_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.enums.LitState.ALL_UNLIT;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import github.kingvampire.DeepTrenches.api.capabilities.lit.ILit;
import github.kingvampire.DeepTrenches.api.capabilities.taxonomy.ITaxonomy;
import github.kingvampire.DeepTrenches.api.entity.layers.LitLayer;
import github.kingvampire.DeepTrenches.api.enums.LitState;
import github.kingvampire.DeepTrenches.api.taxonomy.SubspeciesTaxon;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.LazyOptional;

@OnlyIn(Dist.CLIENT)
public class TaxonomyLitLayer<T extends LivingEntity, M extends EntityModel<T>> extends LitLayer<T, M> {

    public TaxonomyLitLayer(IEntityRenderer<T, M> entityRenderer) {
	super(entityRenderer);
    }

    @Override
    protected ResourceLocation getEntityTexture(T entityIn) {
	LazyOptional<ILit> lit = entityIn.getCapability(LIT_CAPABILITY);
	LazyOptional<ITaxonomy> taxonomy = entityIn.getCapability(TAXONOMY_CAPABILITY);

	if (lit.isPresent() && taxonomy.isPresent()) {
	    ILit ilit = lit.orElseThrow(IllegalArgumentException::new);
	    ITaxonomy itaxonomy = taxonomy.orElseThrow(IllegalArgumentException::new);

	    String family = itaxonomy.getFamily().getVulgarName();
	    String genus = itaxonomy.getGenus().getVulgarName();
	    String order = itaxonomy.getOrder().getVulgarName();
	    String species = itaxonomy.getSpecies().getVulgarName();

	    SubspeciesTaxon subspecies = itaxonomy.getSubspecies();
	    LitState litState = ilit.getLitState();

	    String rank = String.format("textures/entity/%1$s/%2$s/%3$s/%4$s", order, family, genus, species);

	    if (subspecies != null) {
		String subspicies = subspecies.getVulgarName();

		return new ResourceLocation(MODID, rank + "/" + subspicies + "/" + litState + "_layer.png");
	    }

	    return new ResourceLocation(MODID, rank + "/" + litState + "_layer.png");
	}

	return null;
    }

    @Override
    public void render(T entityIn, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_,
	    float p_212842_6_, float p_212842_7_, float p_212842_8_) {

	LazyOptional<ILit> lit = entityIn.getCapability(LIT_CAPABILITY);

	if (lit.isPresent()) {
	    ILit ilit = lit.orElseThrow(IllegalArgumentException::new);

	    if (ilit.getLitState() != ALL_UNLIT)
		super.render(entityIn, p_212842_2_, p_212842_3_, p_212842_4_, p_212842_5_, p_212842_6_, p_212842_7_,
			p_212842_8_);
	}
    }

}
