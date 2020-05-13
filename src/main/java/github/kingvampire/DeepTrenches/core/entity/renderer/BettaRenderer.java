package github.kingvampire.DeepTrenches.core.entity.renderer;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import com.mojang.blaze3d.platform.GlStateManager;

import github.kingvampire.DeepTrenches.core.entity.BettaEntity;
import github.kingvampire.DeepTrenches.core.entity.models.BettaModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class BettaRenderer extends MobRenderer<BettaEntity, BettaModel<BettaEntity>> {

    private static final ResourceLocation[] TEXTURES = new ResourceLocation[] {
	    new ResourceLocation(MODID, "textures/entity/betta/blue_betta.png"),
	    new ResourceLocation(MODID, "textures/entity/betta/colorful_betta.png"),
	    new ResourceLocation(MODID, "textures/entity/betta/red_betta.png"),
	    new ResourceLocation(MODID, "textures/entity/betta/icarus_junior_betta.png"),
	    new ResourceLocation(MODID, "textures/entity/betta/icarus_betta.png"), };

    public BettaRenderer(EntityRendererManager entityRenderManager) {
	super(entityRenderManager, new BettaModel<BettaEntity>(), 0.3F);
    }

    @Override
    protected void preRenderCallback(BettaEntity entitylivingbaseIn, float partialTickTime) {
	GlStateManager.translatef(0.0F, -0.1F, -0.1F);
    }

    @Override
    protected ResourceLocation getEntityTexture(BettaEntity entity) {
	return TEXTURES[entity.getColor()];
    }

}
