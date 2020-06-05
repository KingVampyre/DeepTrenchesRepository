package github.kingvampire.DeepTrenches.api.entity.renderer;

import static com.mojang.blaze3d.platform.GlStateManager.LogicOp.OR_REVERSE;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.ALMOND_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.ALMOND_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.ANAMEATA_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.ANAMEATA_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.AQUEAN_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.AQUEAN_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.BARSHROOKLE_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.BARSHROOKLE_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.BLACK_BIRCH_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.BLACK_BIRCH_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.CHERRY_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.CHERRY_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.COOK_PINE_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.COOK_PINE_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.CROLOOD_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.CROLOOD_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.DARK_CROLOOD_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.DARK_CROLOOD_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.EBONY_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.EBONY_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.FUCHSITRA_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.FUCHSITRA_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.FUNERANITE_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.FUNERANITE_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.GHOSHROOM_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.GHOSHROOM_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PELTOGYNE_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PELTOGYNE_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PIN_CHERRY_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PIN_CHERRY_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PLUM_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PLUM_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PURFUNGA_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PURFUNGA_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.SPROOM_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.SPROOM_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.STORTREEAN_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.STORTREEAN_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.STROOMEAN_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.STROOMEAN_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.SUNRISE_FUNGUS_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.SUNRISE_FUNGUS_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.TEAK_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.TEAK_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.THUNDERCLOUD_PLUM_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.THUNDERCLOUD_PLUM_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.block.StandingSignBlock.ROTATION;
import static net.minecraft.block.WallSignBlock.FACING;
import static net.minecraft.client.renderer.vertex.DefaultVertexFormats.POSITION;

import java.util.List;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StandingSignBlock;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.RenderComponentsUtil;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.model.SignModel;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ModSignTileEntityRenderer extends SignTileEntityRenderer {

    private final SignModel model = new SignModel();

    public void render(SignTileEntity tileEntity, double x, double y, double z, float partialTicks, int destroyStage) {

	BlockState state = tileEntity.getBlockState();
	GlStateManager.pushMatrix();

	if (state.getBlock() instanceof StandingSignBlock) {
	    GlStateManager.translatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
	    GlStateManager.rotatef(-((float) (state.get(ROTATION) * 360) / 16), 0, 1, 0);

	    this.model.getSignStick().showModel = true;
	} else {
	    GlStateManager.translatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
	    GlStateManager.rotatef(-state.get(FACING).getHorizontalAngle(), 0, 1, 0);
	    GlStateManager.translatef(0F, -0.3125F, -0.4375F);

	    this.model.getSignStick().showModel = false;
	}

	if (destroyStage >= 0) {
	    this.bindTexture(DESTROY_STAGES[destroyStage]);

	    GlStateManager.matrixMode(5890);
	    GlStateManager.pushMatrix();
	    GlStateManager.scalef(4, 2, 1);
	    GlStateManager.translatef(0.0625F, 0.0625F, 0.0625F);
	    GlStateManager.matrixMode(5888);
	} else
	    this.bindTexture(this.getTexture(state.getBlock()));

	GlStateManager.enableRescaleNormal();
	GlStateManager.pushMatrix();
	GlStateManager.scalef(0.6666667F, -0.6666667F, -0.6666667F);

	this.model.renderSign();

	GlStateManager.popMatrix();
	FontRenderer fontrenderer = this.getFontRenderer();

	GlStateManager.translatef(0, 0.33333334F, 0.046666667F);
	GlStateManager.scalef(0.010416667F, -0.010416667F, 0.010416667F);
	GlStateManager.normal3f(0, 0, -0.010416667F);
	GlStateManager.depthMask(false);

	int color = tileEntity.getTextColor().func_218388_g();

	if (destroyStage < 0) {
	    for (int i = 0; i < 4; ++i) {
		String renderText = tileEntity.getRenderText(i, text -> {
		    List<ITextComponent> list = RenderComponentsUtil.splitText(text, 90, fontrenderer, false, true);

		    return list.isEmpty() ? "" : list.get(0).getFormattedText();
		});

		if (renderText != null) {
		    fontrenderer.drawString(renderText, (float) (-fontrenderer.getStringWidth(renderText) / 2),
			    (float) (i * 10 - tileEntity.signText.length * 5), color);

		    if (i == tileEntity.getLineBeingEdited() && tileEntity.func_214065_t() >= 0) {
			int k = fontrenderer.getStringWidth(renderText.substring(0,
				Math.max(Math.min(tileEntity.func_214065_t(), renderText.length()), 0)));
			int l = fontrenderer.getBidiFlag() ? -1 : 1;
			int i1 = (k - fontrenderer.getStringWidth(renderText) / 2) * l;
			int j1 = i * 10 - tileEntity.signText.length * 5;

			if (tileEntity.func_214069_r())
			    if (tileEntity.func_214065_t() < renderText.length())
				AbstractGui.fill(i1, j1 - 1, i1 + 1, j1 + 9, -16777216 | color);
			    else
				fontrenderer.drawString("_", (float) i1, (float) j1, color);

			if (tileEntity.func_214067_u() != tileEntity.func_214065_t()) {
			    int k1 = Math.min(tileEntity.func_214065_t(), tileEntity.func_214067_u());
			    int l1 = Math.max(tileEntity.func_214065_t(), tileEntity.func_214067_u());

			    int i2 = (fontrenderer.getStringWidth(renderText.substring(0, k1))
				    - fontrenderer.getStringWidth(renderText) / 2) * l;

			    int j2 = (fontrenderer.getStringWidth(renderText.substring(0, l1))
				    - fontrenderer.getStringWidth(renderText) / 2) * l;

			    this.drawTexture(Math.min(i2, j2), j1, Math.max(i2, j2), j1 + 9);
			}
		    }
		}
	    }
	}

	GlStateManager.depthMask(true);
	GlStateManager.color4f(1, 1, 1, 1);
	GlStateManager.popMatrix();

	if (destroyStage >= 0) {
	    GlStateManager.matrixMode(5890);
	    GlStateManager.popMatrix();
	    GlStateManager.matrixMode(5888);
	}

    }

    private ResourceLocation getTexture(Block block) {

	if (block == ALMOND_SIGN || block == ALMOND_WALL_SIGN)
	    return new ResourceLocation(MODID, "textures/entity/sign/almond.png");

	if (block == ANAMEATA_SIGN || block == ANAMEATA_WALL_SIGN)
	    return new ResourceLocation(MODID, "textures/entity/sign/anameata.png");

	if (block == AQUEAN_SIGN || block == AQUEAN_WALL_SIGN)
	    return new ResourceLocation(MODID, "textures/entity/sign/aquean.png");

	if (block == BARSHROOKLE_SIGN || block == BARSHROOKLE_WALL_SIGN)
	    return new ResourceLocation(MODID, "textures/entity/sign/barshrookle.png");

	if (block == BLACK_BIRCH_SIGN || block == BLACK_BIRCH_WALL_SIGN)
	    return new ResourceLocation(MODID, "textures/entity/sign/black_birch.png");

	if (block == CHERRY_SIGN || block == CHERRY_WALL_SIGN)
	    return new ResourceLocation(MODID, "textures/entity/sign/cherry.png");

	if (block == COOK_PINE_SIGN || block == COOK_PINE_WALL_SIGN)
	    return new ResourceLocation(MODID, "textures/entity/sign/cook_pine.png");

	if (block == CROLOOD_SIGN || block == CROLOOD_WALL_SIGN)
	    return new ResourceLocation(MODID, "textures/entity/sign/crolood.png");

	if (block == DARK_CROLOOD_SIGN || block == DARK_CROLOOD_WALL_SIGN)
	    return new ResourceLocation(MODID, "textures/entity/sign/dark_crolood.png");

	if (block == EBONY_SIGN || block == EBONY_WALL_SIGN)
	    return new ResourceLocation(MODID, "textures/entity/sign/ebony.png");

	if (block == FUNERANITE_SIGN || block == FUNERANITE_WALL_SIGN)
	    return new ResourceLocation(MODID, "textures/entity/sign/funeranite.png");

	if (block == FUCHSITRA_SIGN || block == FUCHSITRA_WALL_SIGN)
	    return new ResourceLocation(MODID, "textures/entity/sign/fuchsitra.png");

	if (block == GHOSHROOM_SIGN || block == GHOSHROOM_WALL_SIGN)
	    return new ResourceLocation(MODID, "textures/entity/sign/ghoshroom.png");

	if (block == PELTOGYNE_SIGN || block == PELTOGYNE_WALL_SIGN)
	    return new ResourceLocation(MODID, "textures/entity/sign/peltogyne.png");

	if (block == PIN_CHERRY_SIGN || block == PIN_CHERRY_WALL_SIGN)
	    return new ResourceLocation(MODID, "textures/entity/sign/pin_cherry.png");

	if (block == PLUM_SIGN || block == PLUM_WALL_SIGN)
	    return new ResourceLocation(MODID, "textures/entity/sign/plum.png");

	if (block == PURFUNGA_SIGN || block == PURFUNGA_WALL_SIGN)
	    return new ResourceLocation(MODID, "textures/entity/sign/purfunga.png");

	if (block == SPROOM_SIGN || block == SPROOM_WALL_SIGN)
	    return new ResourceLocation(MODID, "textures/entity/sign/sproom.png");

	if (block == STORTREEAN_SIGN || block == STORTREEAN_WALL_SIGN)
	    return new ResourceLocation(MODID, "textures/entity/sign/stortreean.png");

	if (block == STROOMEAN_SIGN || block == STROOMEAN_WALL_SIGN)
	    return new ResourceLocation(MODID, "textures/entity/sign/stroomean.png");

	if (block == SUNRISE_FUNGUS_SIGN || block == SUNRISE_FUNGUS_WALL_SIGN)
	    return new ResourceLocation(MODID, "textures/entity/sign/sunrise_fungus.png");

	if (block == TEAK_SIGN || block == TEAK_WALL_SIGN)
	    return new ResourceLocation(MODID, "textures/entity/sign/teak.png");

	if (block == THUNDERCLOUD_PLUM_SIGN || block == THUNDERCLOUD_PLUM_WALL_SIGN)
	    return new ResourceLocation(MODID, "textures/entity/sign/thundercloud_plum.png");

	return null;
    }

    private void drawTexture(int p_217657_1_, int p_217657_2_, int p_217657_3_, int p_217657_4_) {
	Tessellator tessellator = Tessellator.getInstance();
	BufferBuilder bufferbuilder = tessellator.getBuffer();

	GlStateManager.color4f(0.0F, 0.0F, 255.0F, 255.0F);
	GlStateManager.disableTexture();
	GlStateManager.enableColorLogicOp();
	GlStateManager.logicOp(OR_REVERSE);

	bufferbuilder.begin(7, POSITION);
	bufferbuilder.pos((double) p_217657_1_, (double) p_217657_4_, 0.0D).endVertex();
	bufferbuilder.pos((double) p_217657_3_, (double) p_217657_4_, 0.0D).endVertex();
	bufferbuilder.pos((double) p_217657_3_, (double) p_217657_2_, 0.0D).endVertex();
	bufferbuilder.pos((double) p_217657_1_, (double) p_217657_2_, 0.0D).endVertex();
	tessellator.draw();

	GlStateManager.disableColorLogicOp();
	GlStateManager.enableTexture();
    }

}
