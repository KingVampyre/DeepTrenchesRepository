package github.kingvampire.DeepTrenches.core.entity.layers;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;

import github.kingvampire.DeepTrenches.core.entity.models.DeepLakeBettaModel;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DeepLakeBettaBodyLayer<T extends LivingEntity> extends LayerRenderer<T, DeepLakeBettaModel<T>> {

    private static final ResourceLocation DEEP_LAKE_BETTA_BODY = new ResourceLocation(MODID,
	    "textures/entity/betta/layers/deep_lake_betta_body.png");

    public DeepLakeBettaBodyLayer(IEntityRenderer<T, DeepLakeBettaModel<T>> entityRendererIn) {
	super(entityRendererIn);
    }

    @Override
    public void render(T entityIn, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_,
	    float p_212842_6_, float p_212842_7_, float p_212842_8_) {

	BlockPos pos = entityIn.getPosition();
	World world = entityIn.getEntityWorld();

	if (entityIn.getAttackingEntity() == null && world.getLight(pos) < 5) {
	    this.bindTexture(DEEP_LAKE_BETTA_BODY);
	    GlStateManager.enableBlend();
	    GlStateManager.blendFunc(SourceFactor.ONE, DestFactor.ONE);
	    GlStateManager.depthMask(!entityIn.isInvisible());

	    DeepLakeBettaModel<T> model = this.getEntityModel();

	    model.render(entityIn, p_212842_2_, p_212842_3_, p_212842_5_, p_212842_6_, p_212842_7_, p_212842_8_);

	    GlStateManager.disableBlend();
	}
    }

    @Override
    public boolean shouldCombineTextures() {
	return false;
    }

}
