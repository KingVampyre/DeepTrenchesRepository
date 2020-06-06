package github.kingvampire.DeepTrenches.core.entity.models.dragonfishes;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LightLoosejawTransparentModel<T extends Entity> extends EntityModel<T> {

    public RendererModel Upper_Jaw;
    public RendererModel Eyeinnerside2;
    public RendererModel Eyeinnerside1;
    public RendererModel Upper_teeth_side_1;
    public RendererModel Upper_teeth_side_2;
    public RendererModel Front_upper_teeth;
    public RendererModel Body;
    public RendererModel Eyeouterside2;
    public RendererModel Eyeouterside1;
    public RendererModel Lower_jaw;
    public RendererModel Tail;
    public RendererModel Pelvic_fin_side_1;
    public RendererModel Pelvic_fin_side_2;
    public RendererModel Fronttooth;
    public RendererModel Front_tooth_side_1;
    public RendererModel Front_tooth_side_2;
    public RendererModel Teeth_side_1;
    public RendererModel Teeth_side_2;
    public RendererModel Caudal_fin;
    public RendererModel Dorsal_fin;
    public RendererModel Anal_fin;

    public LightLoosejawTransparentModel() {
	this.textureWidth = 128;
	this.textureHeight = 64;
	this.Eyeouterside1 = new RendererModel(this, 0, 15);
	this.Eyeouterside1.setRotationPoint(0.0F, -0.3F, -0.4F);
	this.Eyeouterside1.addBox(-0.8F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
	this.Tail = new RendererModel(this, 43, 41);
	this.Tail.setRotationPoint(0.0F, 3.0F, 15.7F);
	this.Tail.addBox(-1.0F, -2.8F, 0.0F, 2, 6, 15, 0.0F);
	this.Teeth_side_1 = new RendererModel(this, 0, 30);
	this.Teeth_side_1.setRotationPoint(-2.4F, -1.0F, -10.2F);
	this.Teeth_side_1.addBox(0.0F, -1.4F, 0.0F, 0, 2, 8, 0.0F);
	this.setRotateAngle(Teeth_side_1, 0.17034413499464657F, 0.0F, 0.0F);
	this.Caudal_fin = new RendererModel(this, 88, 37);
	this.Caudal_fin.setRotationPoint(0.0F, 0.0F, 14.3F);
	this.Caudal_fin.addBox(0.0F, -6.3F, 0.0F, 0, 13, 12, 0.0F);
	this.Body = new RendererModel(this, 0, 39);
	this.Body.setRotationPoint(0.0F, -3.1F, -1.0F);
	this.Body.addBox(-2.5F, 0.0F, 0.0F, 5, 7, 16, 0.0F);
	this.Eyeouterside2 = new RendererModel(this, 0, 15);
	this.Eyeouterside2.mirror = true;
	this.Eyeouterside2.setRotationPoint(0.0F, -0.3F, 1.0F);
	this.Eyeouterside2.addBox(-1.6F, 0.0F, -1.4F, 3, 3, 3, 0.0F);
	this.Eyeinnerside1 = new RendererModel(this, 13, 17);
	this.Eyeinnerside1.mirror = true;
	this.Eyeinnerside1.setRotationPoint(-2.5F, -1.8F, -9.5F);
	this.Eyeinnerside1.addBox(-0.5F, 0.0F, 0.0F, 1, 2, 2, 0.0F);
	this.Upper_teeth_side_2 = new RendererModel(this, 0, 35);
	this.Upper_teeth_side_2.setRotationPoint(2.4F, 2.3F, -10.7F);
	this.Upper_teeth_side_2.addBox(0.0F, 0.0F, 0.0F, 0, 2, 6, 0.0F);
	this.Dorsal_fin = new RendererModel(this, 69, 34);
	this.Dorsal_fin.setRotationPoint(0.0F, -3.0F, 6.0F);
	this.Dorsal_fin.addBox(0.0F, -3.7F, 0.0F, 0, 5, 9, 0.0F);
	this.Eyeinnerside2 = new RendererModel(this, 13, 17);
	this.Eyeinnerside2.setRotationPoint(2.0F, -1.8F, -9.5F);
	this.Eyeinnerside2.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2, 0.0F);
	this.Upper_Jaw = new RendererModel(this, 0, 22);
	this.Upper_Jaw.setRotationPoint(0.0F, 16.0F, -12.0F);
	this.Upper_Jaw.addBox(-2.5F, -3.0F, -11.0F, 5, 6, 10, 0.0F);
	this.Pelvic_fin_side_2 = new RendererModel(this, 8, -3);
	this.Pelvic_fin_side_2.setRotationPoint(2.5F, 6.6F, 8.0F);
	this.Pelvic_fin_side_2.addBox(0.0F, 0.0F, 0.0F, 0, 13, 4, 0.0F);
	this.setRotateAngle(Pelvic_fin_side_2, 0.5464625887994246F, 0.0F, -0.13962634015954636F);
	this.Fronttooth = new RendererModel(this, 31, 33);
	this.Fronttooth.setRotationPoint(0.0F, -1.0F, -12.3F);
	this.Fronttooth.addBox(0.0F, -2.8F, 0.0F, 0, 3, 1, 0.0F);
	this.setRotateAngle(Fronttooth, 0.27314402793711257F, 0.0F, 0.0F);
	this.Pelvic_fin_side_1 = new RendererModel(this, 8, -3);
	this.Pelvic_fin_side_1.setRotationPoint(-2.5F, 6.6F, 8.0F);
	this.Pelvic_fin_side_1.addBox(0.0F, 0.0F, 0.0F, 0, 13, 4, 0.0F);
	this.setRotateAngle(Pelvic_fin_side_1, 0.5464625887994246F, 0.0F, 0.13962634015954636F);
	this.Anal_fin = new RendererModel(this, 69, 28);
	this.Anal_fin.setRotationPoint(0.0F, 2.9F, 6.0F);
	this.Anal_fin.addBox(0.0F, 0.0F, 0.0F, 0, 5, 9, 0.0F);
	this.Teeth_side_2 = new RendererModel(this, 0, 30);
	this.Teeth_side_2.setRotationPoint(2.4F, -1.0F, -10.2F);
	this.Teeth_side_2.addBox(0.0F, -1.4F, 0.0F, 0, 2, 8, 0.0F);
	this.setRotateAngle(Teeth_side_2, 0.17034413499464657F, 0.0F, 0.0F);
	this.Front_upper_teeth = new RendererModel(this, 0, 43);
	this.Front_upper_teeth.setRotationPoint(0.0F, 2.3F, -10.7F);
	this.Front_upper_teeth.addBox(-2.5F, 0.0F, 0.0F, 5, 2, 0, 0.0F);
	this.Front_tooth_side_1 = new RendererModel(this, 34, 32);
	this.Front_tooth_side_1.setRotationPoint(-2.4F, -1.5F, -11.49F);
	this.Front_tooth_side_1.addBox(0.0F, -3.32F, 0.0F, 0, 4, 1, 0.0F);
	this.setRotateAngle(Front_tooth_side_1, 0.19198621771937624F, 0.0F, 0.0F);
	this.Front_tooth_side_2 = new RendererModel(this, 34, 32);
	this.Front_tooth_side_2.setRotationPoint(2.4F, -1.5F, -11.49F);
	this.Front_tooth_side_2.addBox(0.0F, -3.32F, 0.0F, 0, 4, 1, 0.0F);
	this.setRotateAngle(Front_tooth_side_2, 0.19198621771937624F, 0.0F, 0.0F);
	this.Upper_teeth_side_1 = new RendererModel(this, 0, 35);
	this.Upper_teeth_side_1.setRotationPoint(-2.4F, 2.3F, -10.7F);
	this.Upper_teeth_side_1.addBox(0.0F, 0.0F, 0.0F, 0, 2, 6, 0.0F);
	this.Lower_jaw = new RendererModel(this, 30, 26);
	this.Lower_jaw.setRotationPoint(0.0F, 7.0F, 2.0F);
	this.Lower_jaw.addBox(-2.5F, -1.5F, -12.0F, 5, 1, 12, 0.0F);
	this.setRotateAngle(Lower_jaw, 0.31869712141416456F, 0.0F, 0.0F);
	this.Eyeinnerside1.addChild(this.Eyeouterside1);
	this.Body.addChild(this.Tail);
	this.Lower_jaw.addChild(this.Teeth_side_1);
	this.Tail.addChild(this.Caudal_fin);
	this.Upper_Jaw.addChild(this.Body);
	this.Eyeinnerside2.addChild(this.Eyeouterside2);
	this.Upper_Jaw.addChild(this.Eyeinnerside1);
	this.Upper_Jaw.addChild(this.Upper_teeth_side_2);
	this.Tail.addChild(this.Dorsal_fin);
	this.Upper_Jaw.addChild(this.Eyeinnerside2);
	this.Body.addChild(this.Pelvic_fin_side_2);
	this.Lower_jaw.addChild(this.Fronttooth);
	this.Body.addChild(this.Pelvic_fin_side_1);
	this.Tail.addChild(this.Anal_fin);
	this.Lower_jaw.addChild(this.Teeth_side_2);
	this.Upper_Jaw.addChild(this.Front_upper_teeth);
	this.Lower_jaw.addChild(this.Front_tooth_side_1);
	this.Lower_jaw.addChild(this.Front_tooth_side_2);
	this.Upper_Jaw.addChild(this.Upper_teeth_side_1);
	this.Body.addChild(this.Lower_jaw);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
	this.Upper_Jaw.render(f5);
    }

    public void setRotateAngle(RendererModel modelRenderer, float x, float y, float z) {
	modelRenderer.rotateAngleX = x;
	modelRenderer.rotateAngleY = y;
	modelRenderer.rotateAngleZ = z;
    }
}
