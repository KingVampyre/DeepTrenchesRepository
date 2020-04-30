package github.kingvampire.DeepTrenches.core.entity.renderer;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import com.mojang.blaze3d.platform.GlStateManager;

import github.kingvampire.DeepTrenches.core.entity.models.AdaiggerModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.TridentRenderer;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AdaiggerRenderer extends TridentRenderer {

	private final AdaiggerModel model = new AdaiggerModel();

	public AdaiggerRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	public void doRender(TridentEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		this.bindEntityTexture(entity);

		float rotationYaw = entity.rotationYaw;
		float rotationPitch = entity.rotationPitch;

		GlStateManager.color4f(1, 1, 1, 1);
		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();

		GlStateManager.translatef((float) x, (float) y - 0.9f, (float) z);
		GlStateManager.rotatef(MathHelper.lerp(partialTicks, entity.prevRotationYaw, rotationYaw) - 180, 0, 1, 0);
		GlStateManager.rotatef(rotationPitch < 0 ? -10 : 10, rotationPitch < 0 ? -rotationPitch : rotationPitch, 0, 1);

		this.model.renderer();

		GlStateManager.popMatrix();

		if (!this.renderOutlines)
			this.renderName(entity, x, y, z);

		GlStateManager.enableLighting();
	}

	@Override
	protected ResourceLocation getEntityTexture(TridentEntity entity) {
		return new ResourceLocation(MODID, "textures/entity/adaigger.png");
	}

}
