package github.kingvampire.DeepTrenches.api.entity.layers;

import static net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType.GROUND;
import static net.minecraft.util.BlockRenderLayer.TRANSLUCENT;
import static net.minecraft.util.HandSide.RIGHT;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public class CarriedItemLayer<T extends MobEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {

    public CarriedItemLayer(IEntityRenderer<T, M> entityRendererIn) {
	super(entityRendererIn);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void render(T entityIn, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_,
	    float p_212842_6_, float p_212842_7_, float p_212842_8_) {

	boolean flag = entityIn.getPrimaryHand() == RIGHT;

	ItemStack stack = flag ? entityIn.getHeldItemOffhand() : entityIn.getHeldItemMainhand();
	ItemStack stack1 = flag ? entityIn.getHeldItemMainhand() : entityIn.getHeldItemOffhand();

	if (!stack.isEmpty() || !stack1.isEmpty()) {

	    if (!stack1.isEmpty()) {
		Block block = Block.getBlockFromItem(stack1.getItem());
		ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

		boolean translucent = itemRenderer.shouldRenderItemIn3D(stack1) && block.getRenderLayer() == TRANSLUCENT;

		GlStateManager.pushMatrix();

		if (translucent)
		    GlStateManager.depthMask(false);

		this.translateCallback(entityIn);
		itemRenderer.renderItem(stack1, entityIn, GROUND, false);

		if (translucent)
		    GlStateManager.depthMask(true);

		GlStateManager.popMatrix();
	    }
	}
    }

    protected void translateCallback(T creature) {
	float f2 = MathHelper.abs(creature.rotationPitch) / 60;

	if (creature.rotationPitch < 0)
	    GlStateManager.translatef(0, 1 - f2 * 0.5F, -1 + f2 * 0.5F);
	else
	    GlStateManager.translatef(0, 1 + f2 * 0.8F, -1 + f2 * 0.2F);
    }

    @Override
    public boolean shouldCombineTextures() {
	return false;
    }

}
