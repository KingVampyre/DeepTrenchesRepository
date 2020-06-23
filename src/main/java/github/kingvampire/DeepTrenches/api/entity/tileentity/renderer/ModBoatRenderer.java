package github.kingvampire.DeepTrenches.api.entity.tileentity.renderer;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import github.kingvampire.DeepTrenches.api.entity.tileentity.ModBoatEntity;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BoatModel;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModBoatRenderer extends BoatRenderer {

    protected final BoatModel model = new BoatModel();

    public ModBoatRenderer(EntityRendererManager renderManager) {
	super(renderManager);
    }

    @Override
    protected ResourceLocation getEntityTexture(BoatEntity entity) {
	ModBoatEntity boat = (ModBoatEntity) entity;

	return new ResourceLocation(MODID, "textures/entity/boat/" + boat.getWoodType() + ".png");
    }

}
