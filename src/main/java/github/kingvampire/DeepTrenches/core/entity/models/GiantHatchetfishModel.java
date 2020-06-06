package github.kingvampire.DeepTrenches.core.entity.models;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GiantHatchetfishModel<T extends Entity> extends EntityModel<T> {

    public RendererModel Body;
    public RendererModel Head;
    public RendererModel Lower_jaw;
    public RendererModel Caudal_fin;
    public RendererModel Dorsal_fin;
    public RendererModel Pectoral_fin_2;
    public RendererModel Pectoral_fin_1;
    public RendererModel Pelvic_fin_2;
    public RendererModel Pelvic_fin_1;
    public RendererModel Eyes;
    public RendererModel Metallic_eye_thing_bottom;
    public RendererModel Metallic_eye_thing_rear;
    public RendererModel Snout;

    public GiantHatchetfishModel() {
	this.textureWidth = 64;
	this.textureHeight = 32;
	this.Body = new RendererModel(this, 0, 0);
	this.Body.setRotationPoint(0.0F, 18.0F, -5.0F);
	this.Body.addBox(-0.5F, 0.0F, 0.0F, 1, 6, 6, 0.0F);
	this.Pelvic_fin_1 = new RendererModel(this, 2, 1);
	this.Pelvic_fin_1.setRotationPoint(0.5F, 4.0F, 5.7F);
	this.Pelvic_fin_1.addBox(0.0F, 0.0F, 0.0F, 0, 2, 2, 0.0F);
	this.Pectoral_fin_2 = new RendererModel(this, 8, 2);
	this.Pectoral_fin_2.setRotationPoint(-0.5F, 4.8F, 0.0F);
	this.Pectoral_fin_2.addBox(0.0F, 0.0F, 0.0F, 0, 1, 2, 0.0F);
	this.setRotateAngle(Pectoral_fin_2, 0.0F, -0.10175269539126941F, 0.0F);
	this.Head = new RendererModel(this, 14, 4);
	this.Head.setRotationPoint(0.0F, 3.0F, 1.0F);
	this.Head.addBox(-0.5F, -3.1F, -3.8F, 1, 5, 3, 0.0F);
	this.Eyes = new RendererModel(this, 17, 14);
	this.Eyes.setRotationPoint(0.0F, -1.5F, -3.79F);
	this.Eyes.addBox(-1.5F, -0.5F, 0.0F, 3, 2, 2, 0.0F);
	this.Dorsal_fin = new RendererModel(this, 3, 13);
	this.Dorsal_fin.setRotationPoint(0.0F, 0.0F, 0.0F);
	this.Dorsal_fin.addBox(0.0F, -1.7F, 0.0F, 0, 2, 6, 0.0F);
	this.Lower_jaw = new RendererModel(this, 22, 7);
	this.Lower_jaw.setRotationPoint(0.0F, 3.9F, 1.0F);
	this.Lower_jaw.addBox(-0.5F, 0.0F, -3.8F, 1, 2, 3, 0.0F);
	this.Metallic_eye_thing_rear = new RendererModel(this, 28, 15);
	this.Metallic_eye_thing_rear.setRotationPoint(0.0F, 0.0F, 2.0F);
	this.Metallic_eye_thing_rear.addBox(-1.0F, -0.5F, 0.0F, 2, 2, 1, 0.0F);
	this.Metallic_eye_thing_bottom = new RendererModel(this, 35, 15);
	this.Metallic_eye_thing_bottom.setRotationPoint(0.0F, 2.0F, 0.0F);
	this.Metallic_eye_thing_bottom.addBox(-1.0F, -0.5F, 0.0F, 2, 1, 2, 0.0F);
	this.Pelvic_fin_2 = new RendererModel(this, 2, 1);
	this.Pelvic_fin_2.setRotationPoint(-0.5F, 4.0F, 5.7F);
	this.Pelvic_fin_2.addBox(0.0F, 0.0F, 0.0F, 0, 2, 2, 0.0F);
	this.Pectoral_fin_1 = new RendererModel(this, 8, 2);
	this.Pectoral_fin_1.setRotationPoint(0.5F, 4.8F, 0.0F);
	this.Pectoral_fin_1.addBox(0.0F, 0.0F, 0.0F, 0, 1, 2, 0.0F);
	this.setRotateAngle(Pectoral_fin_1, 0.0F, 0.10175269539126941F, 0.0F);
	this.Snout = new RendererModel(this, 30, 8);
	this.Snout.setRotationPoint(0.0F, 0.0F, -3.8F);
	this.Snout.addBox(-0.5F, -1.7F, -1.0F, 1, 3, 1, 0.0F);
	this.Caudal_fin = new RendererModel(this, 0, 4);
	this.Caudal_fin.setRotationPoint(0.0F, 3.0F, 6.0F);
	this.Caudal_fin.addBox(0.0F, -3.4F, 0.0F, 0, 6, 8, 0.0F);
	this.Body.addChild(this.Pelvic_fin_1);
	this.Body.addChild(this.Pectoral_fin_2);
	this.Body.addChild(this.Head);
	this.Head.addChild(this.Eyes);
	this.Body.addChild(this.Dorsal_fin);
	this.Body.addChild(this.Lower_jaw);
	this.Eyes.addChild(this.Metallic_eye_thing_rear);
	this.Eyes.addChild(this.Metallic_eye_thing_bottom);
	this.Body.addChild(this.Pelvic_fin_2);
	this.Body.addChild(this.Pectoral_fin_1);
	this.Lower_jaw.addChild(this.Snout);
	this.Body.addChild(this.Caudal_fin);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
	this.Body.render(f5);
    }

    public void setRotateAngle(RendererModel modelRenderer, float x, float y, float z) {
	modelRenderer.rotateAngleX = x;
	modelRenderer.rotateAngleY = y;
	modelRenderer.rotateAngleZ = z;
    }

}
