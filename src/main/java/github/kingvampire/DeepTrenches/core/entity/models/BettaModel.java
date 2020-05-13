package github.kingvampire.DeepTrenches.core.entity.models;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BettaModel<T extends Entity> extends EntityModel<T> {
    public RendererModel Body;
    public RendererModel Head;
    public RendererModel Dorsal_fin;
    public RendererModel Caudal_fin;
    public RendererModel Ventral_fin_1;
    public RendererModel Ventral_fin_2;
    public RendererModel Pectoral_fin_1;
    public RendererModel Pectoral_fin_2;
    public RendererModel Snout;

    public BettaModel() {
	this.textureWidth = 64;
	this.textureHeight = 32;
	this.Dorsal_fin = new RendererModel(this, 10, 3);
	this.Dorsal_fin.setRotationPoint(0.0F, 0.0F, 0.5F);
	this.Dorsal_fin.addBox(0.0F, -6.0F, 0.0F, 0, 6, 7, 0.0F);
	this.setRotateAngle(Dorsal_fin, -0.136659280431156F, 0.0F, 0.0F);
	this.Snout = new RendererModel(this, 0, 11);
	this.Snout.setRotationPoint(0.0F, 0.4F, -1.0F);
	this.Snout.addBox(-1.5F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
	this.Caudal_fin = new RendererModel(this, 11, 5);
	this.Caudal_fin.setRotationPoint(0.0F, 1.0F, 2.8F);
	this.Caudal_fin.addBox(0.0F, -5.0F, 0.0F, 0, 12, 11, 0.0F);
	this.Ventral_fin_1 = new RendererModel(this, 35, 11);
	this.Ventral_fin_1.setRotationPoint(-1.2F, 3.1F, 0.0F);
	this.Ventral_fin_1.addBox(0.0F, 0.0F, 0.0F, 0, 4, 2, 0.0F);
	this.setRotateAngle(Ventral_fin_1, 0.045553093477052F, 0.0F, 0.22759093446006054F);
	this.Pectoral_fin_2 = new RendererModel(this, 27, -1);
	this.Pectoral_fin_2.setRotationPoint(2.0F, 1.0F, 0.4F);
	this.Pectoral_fin_2.addBox(0.0F, 0.0F, 0.0F, 0, 3, 3, 0.0F);
	this.setRotateAngle(Pectoral_fin_2, 0.0F, 0.22759093446006054F, 0.0F);
	this.Body = new RendererModel(this, 12, 0);
	this.Body.setRotationPoint(0.0F, 20.0F, 0.0F);
	this.Body.addBox(-2.0F, 0.0F, 0.0F, 4, 4, 6, 0.0F);
	this.Ventral_fin_2 = new RendererModel(this, 35, 11);
	this.Ventral_fin_2.setRotationPoint(1.4F, 3.1F, -0.1F);
	this.Ventral_fin_2.addBox(0.0F, 0.0F, 0.0F, 0, 4, 2, 0.0F);
	this.setRotateAngle(Ventral_fin_2, 0.045553093477052F, 0.0F, -0.22759093446006054F);
	this.Pectoral_fin_1 = new RendererModel(this, 27, -1);
	this.Pectoral_fin_1.setRotationPoint(-2.0F, 1.0F, 0.4F);
	this.Pectoral_fin_1.addBox(0.0F, 0.0F, 0.0F, 0, 3, 3, 0.0F);
	this.setRotateAngle(Pectoral_fin_1, 0.0F, -0.22759093446006054F, 0.0F);
	this.Head = new RendererModel(this, 0, 4);
	this.Head.setRotationPoint(0.0F, 0.3F, -2.8F);
	this.Head.addBox(-1.5F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
	this.Body.addChild(this.Dorsal_fin);
	this.Head.addChild(this.Snout);
	this.Body.addChild(this.Caudal_fin);
	this.Body.addChild(this.Ventral_fin_1);
	this.Body.addChild(this.Pectoral_fin_2);
	this.Body.addChild(this.Ventral_fin_2);
	this.Body.addChild(this.Pectoral_fin_1);
	this.Body.addChild(this.Head);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {

	if (this.isChild) {
	    GlStateManager.pushMatrix();
	    GlStateManager.scalef(0.5F, 0.5F, 0.5F);
	    GlStateManager.translatef(0.0F, 1.5F, 0.0F);
	    this.Body.render(f5);
	    GlStateManager.popMatrix();
	} else
	    this.Body.render(f5);
    }

    public void setRotateAngle(RendererModel modelRenderer, float x, float y, float z) {
	modelRenderer.rotateAngleX = x;
	modelRenderer.rotateAngleY = y;
	modelRenderer.rotateAngleZ = z;
    }
}
