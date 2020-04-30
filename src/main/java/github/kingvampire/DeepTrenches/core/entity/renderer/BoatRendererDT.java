package github.kingvampire.DeepTrenches.core.entity.renderer;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import github.kingvampire.DeepTrenches.core.entity.BoatEntityDT;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BoatModel;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BoatRendererDT extends BoatRenderer {

	private static final ResourceLocation[] BOAT_TEXTURES = new ResourceLocation[] {
			new ResourceLocation(MODID, "textures/entity/boat/almond.png"),
			new ResourceLocation(MODID, "textures/entity/boat/anameata.png"),
			new ResourceLocation(MODID, "textures/entity/boat/aquaen.png"),
			new ResourceLocation(MODID, "textures/entity/boat/barshrookle.png"),
			new ResourceLocation(MODID, "textures/entity/boat/black_birch.png"),
			new ResourceLocation(MODID, "textures/entity/boat/cherry.png"),
			new ResourceLocation(MODID, "textures/entity/boat/cook_pine.png"),
			new ResourceLocation(MODID, "textures/entity/boat/crolood.png"),
			new ResourceLocation(MODID, "textures/entity/boat/dark_crolood.png"),
			new ResourceLocation(MODID, "textures/entity/boat/ebony.png"),
			new ResourceLocation(MODID, "textures/entity/boat/feneranite.png"),
			new ResourceLocation(MODID, "textures/entity/boat/fuchsitra.png"),
			new ResourceLocation(MODID, "textures/entity/boat/ghoshroom.png"),
			new ResourceLocation(MODID, "textures/entity/boat/peltogyne.png"),
			new ResourceLocation(MODID, "textures/entity/boat/pin_cherry.png"),
			new ResourceLocation(MODID, "textures/entity/boat/plum.png"),
			new ResourceLocation(MODID, "textures/entity/boat/purfunga.png"),
			new ResourceLocation(MODID, "textures/entity/boat/sproom.png"),
			new ResourceLocation(MODID, "textures/entity/boat/stortreean.png"),
			new ResourceLocation(MODID, "textures/entity/boat/stroomean.png"),
			new ResourceLocation(MODID, "textures/entity/boat/sunrise_fungus.png"),
			new ResourceLocation(MODID, "textures/entity/boat/teak.png"),
			new ResourceLocation(MODID, "textures/entity/boat/thundercloud_plum.png")
	};

	protected final BoatModel model = new BoatModel();

	public BoatRendererDT(EntityRendererManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getEntityTexture(BoatEntity entity) {
		BoatEntityDT boat = (BoatEntityDT) entity;

		return BOAT_TEXTURES[boat.getWoodType().ordinal()];
	}

}
