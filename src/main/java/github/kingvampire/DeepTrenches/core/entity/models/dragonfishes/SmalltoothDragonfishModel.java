package github.kingvampire.DeepTrenches.core.entity.models.dragonfishes;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SmalltoothDragonfishModel<T extends Entity> extends EntityModel<T> {

    public RendererModel Body;
    public RendererModel Upper_Jaw;
    public RendererModel Lower_jaw;
    public RendererModel Tail;
    public RendererModel Pectoral_fin_side_1;
    public RendererModel Pectoral_fin_side_2;
    public RendererModel Pelvic_fin_side_1;
    public RendererModel Pelvic_fin_side_2;
    public RendererModel Eyeinnerside1;
    public RendererModel Eyeinnerside2;
    public RendererModel Upper_teeth_side_2;
    public RendererModel Upper_teeth_side_1;
    public RendererModel Front_upper_teeth;
    public RendererModel Eyeouterside1;
    public RendererModel Eyeouterside2;
    public RendererModel Fronttooth;
    public RendererModel Teeth_side_1;
    public RendererModel Teeth_side_2;
    public RendererModel Front_tooth_side_2;
    public RendererModel Front_tooth_side_1;
    public RendererModel Barbel_joint;
    public RendererModel Barbel;
    public RendererModel Barbel_tip;
    public RendererModel Barbel_tip_appandage;
    public RendererModel Dorsal_fin;
    public RendererModel Anal_fin;
    public RendererModel Caudal_fin;

    public SmalltoothDragonfishModel() {

	this.textureWidth = 128;
	this.textureHeight = 64;
	this.Pectoral_fin_side_2 = new RendererModel(this, 2, 0);
	this.Pectoral_fin_side_2.setRotationPoint(2.4F, 6.9F, 1.0F);
	this.Pectoral_fin_side_2.addBox(0.0F, 0.0F, -1.0F, 0, 5, 3, 0.0F);
	this.setRotateAngle(Pectoral_fin_side_2, 0.4642575810304917F, 0.0F, -0.13962634015954636F);
	this.Pelvic_fin_side_1 = new RendererModel(this, 8, -1);
	this.Pelvic_fin_side_1.setRotationPoint(-2.4F, 6.6F, 10.4F);
	this.Pelvic_fin_side_1.addBox(0.0F, 0.0F, 0.0F, 0, 6, 5, 0.0F);
	this.setRotateAngle(Pelvic_fin_side_1, 0.03490658503988659F, 0.0F, 0.13962634015954636F);
	this.Anal_fin = new RendererModel(this, 70, 31);
	this.Anal_fin.setRotationPoint(0.0F, 3.0F, 5.0F);
	this.Anal_fin.addBox(0.0F, 0.0F, 0.0F, 0, 5, 6, 0.0F);
	this.Caudal_fin = new RendererModel(this, 70, 37);
	this.Caudal_fin.setRotationPoint(0.0F, -0.0F, 11.0F);
	this.Caudal_fin.addBox(0.0F, -6.5F, 0.0F, 0, 13, 12, 0.0F);
	this.Upper_teeth_side_2 = new RendererModel(this, 0, 36);
	this.Upper_teeth_side_2.setRotationPoint(2.4F, 3.2F, -11.5F);
	this.Upper_teeth_side_2.addBox(0.0F, 0.0F, 0.0F, 0, 1, 7, 0.0F);
	this.Front_upper_teeth = new RendererModel(this, 0, 44);
	this.Front_upper_teeth.setRotationPoint(0.0F, 3.2F, -11.3F);
	this.Front_upper_teeth.addBox(-2.5F, 0.0F, 0.0F, 5, 1, 0, 0.0F);
	this.Eyeouterside2 = new RendererModel(this, 0, 14);
	this.Eyeouterside2.mirror = true;
	this.Eyeouterside2.setRotationPoint(0.0F, -0.4F, -0.4F);
	this.Eyeouterside2.addBox(-1.2F, 0.0F, 0.0F, 3, 4, 4, 0.0F);
	this.Front_tooth_side_2 = new RendererModel(this, 36, 35);
	this.Front_tooth_side_2.setRotationPoint(2.4F, -1.0F, -11.0F);
	this.Front_tooth_side_2.addBox(0.0F, -1.3F, 0.0F, 0, 2, 1, 0.0F);
	this.setRotateAngle(Front_tooth_side_2, 0.19198621771937624F, 0.0F, 0.0F);
	this.Barbel_tip = new RendererModel(this, 18, 9);
	this.Barbel_tip.setRotationPoint(-0.5F, 7.9F, 0.05F);
	this.Barbel_tip.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
	this.setRotateAngle(Barbel_tip, -0.07051130178057091F, 0.0F, 0.0F);
	this.Body = new RendererModel(this, 0, 40);
	this.Body.setRotationPoint(0.0F, 16.0F, -12.0F);
	this.Body.addBox(-2.5F, 0.0F, 0.0F, 5, 7, 16, 0.0F);
	this.Lower_jaw = new RendererModel(this, 32, 27);
	this.Lower_jaw.setRotationPoint(0.0F, 7.5F, 1.0F);
	this.Lower_jaw.addBox(-2.5F, -0.5F, -11.3F, 5, 1, 12, 0.0F);
	this.setRotateAngle(Lower_jaw, 0.22759093446006054F, 0.0F, 0.0F);
	this.Pelvic_fin_side_2 = new RendererModel(this, 8, -1);
	this.Pelvic_fin_side_2.setRotationPoint(2.4F, 6.6F, 10.4F);
	this.Pelvic_fin_side_2.addBox(0.0F, 0.0F, 0.0F, 0, 6, 5, 0.0F);
	this.setRotateAngle(Pelvic_fin_side_2, 0.03490658503988659F, 0.0F, -0.13962634015954636F);
	this.Teeth_side_2 = new RendererModel(this, 0, 32);
	this.Teeth_side_2.setRotationPoint(2.4F, -1.0F, -9.8F);
	this.Teeth_side_2.addBox(0.0F, -0.8F, 0.0F, 0, 2, 8, 0.0F);
	this.Tail = new RendererModel(this, 43, 45);
	this.Tail.setRotationPoint(0.0F, 3.0F, 15.3F);
	this.Tail.addBox(-1.0F, -2.8F, 0.0F, 2, 6, 11, 0.0F);
	this.Eyeouterside1 = new RendererModel(this, 0, 14);
	this.Eyeouterside1.setRotationPoint(0.0F, -0.4F, -0.4F);
	this.Eyeouterside1.addBox(-2.2F, 0.0F, 0.0F, 3, 4, 4, 0.0F);
	this.Eyeinnerside2 = new RendererModel(this, 15, 16);
	this.Eyeinnerside2.setRotationPoint(2.5F, -2.3F, -8.9F);
	this.Eyeinnerside2.addBox(0.0F, 0.0F, 0.0F, 1, 3, 3, 0.0F);
	this.Dorsal_fin = new RendererModel(this, 70, 37);
	this.Dorsal_fin.setRotationPoint(0.0F, -3.0F, 4.4F);
	this.Dorsal_fin.addBox(0.0F, -3.3F, 0.0F, 0, 5, 6, 0.0F);
	this.Eyeinnerside1 = new RendererModel(this, 15, 16);
	this.Eyeinnerside1.mirror = true;
	this.Eyeinnerside1.setRotationPoint(-2.0F, -2.3F, -8.9F);
	this.Eyeinnerside1.addBox(-1.5F, 0.0F, 0.0F, 1, 3, 3, 0.0F);
	this.Front_tooth_side_1 = new RendererModel(this, 36, 35);
	this.Front_tooth_side_1.setRotationPoint(-2.4F, -1.0F, -11.0F);
	this.Front_tooth_side_1.addBox(0.0F, -1.3F, 0.0F, 0, 2, 1, 0.0F);
	this.setRotateAngle(Front_tooth_side_1, 0.19198621771937624F, 0.0F, 0.0F);
	this.Upper_teeth_side_1 = new RendererModel(this, 0, 36);
	this.Upper_teeth_side_1.setRotationPoint(-2.4F, 3.2F, -11.5F);
	this.Upper_teeth_side_1.addBox(0.0F, 0.0F, 0.0F, 0, 1, 7, 0.0F);
	this.Teeth_side_1 = new RendererModel(this, 0, 32);
	this.Teeth_side_1.setRotationPoint(-2.4F, -1.0F, -9.8F);
	this.Teeth_side_1.addBox(0.0F, -0.8F, 0.0F, 0, 2, 8, 0.0F);
	this.Barbel_tip_appandage = new RendererModel(this, 23, 7);
	this.Barbel_tip_appandage.setRotationPoint(0.5F, 1.6F, 0.0F);
	this.Barbel_tip_appandage.addBox(0.0F, 0.0F, 0.0F, 0, 4, 1, 0.0F);
	this.setRotateAngle(Barbel_tip_appandage, 0.23963370629882144F, -0.005061454830783555F, 0.0F);
	this.Barbel_joint = new RendererModel(this, 0, 0);
	this.Barbel_joint.setRotationPoint(0.0F, 0.5F, -5.0F);
	this.Barbel_joint.addBox(0.0F, 0.0F, 0.0F, 2, 0, 1, 0.0F);
	this.setRotateAngle(Barbel_joint, 0.8196066167365371F, 0.0F, 0.0F);
	this.Upper_Jaw = new RendererModel(this, 0, 22);
	this.Upper_Jaw.setRotationPoint(0.0F, 3.0F, 1.0F);
	this.Upper_Jaw.addBox(-2.5F, -3.0F, -11.7F, 5, 7, 11, 0.0F);
	this.Barbel = new RendererModel(this, 0, 0);
	this.Barbel.setRotationPoint(-0.4F, -0.5F, 0.0F);
	this.Barbel.addBox(0.0F, 0.0F, 0.0F, 0, 8, 1, 0.0F);
	this.setRotateAngle(Barbel, -0.3794345793835672F, 0.0F, 0.0F);
	this.Pectoral_fin_side_1 = new RendererModel(this, 2, 0);
	this.Pectoral_fin_side_1.setRotationPoint(-2.4F, 6.9F, 1.0F);
	this.Pectoral_fin_side_1.addBox(0.0F, 0.0F, -1.0F, 0, 5, 3, 0.0F);
	this.setRotateAngle(Pectoral_fin_side_1, 0.4642575810304917F, 0.0F, 0.13962634015954636F);
	this.Fronttooth = new RendererModel(this, 32, 35);
	this.Fronttooth.setRotationPoint(0.0F, -1.0F, -11.9F);
	this.Fronttooth.addBox(0.0F, -1.7F, 0.0F, 0, 2, 1, 0.0F);
	this.setRotateAngle(Fronttooth, 0.27314402793711257F, 0.0F, 0.0F);
	this.Body.addChild(this.Pectoral_fin_side_2);
	this.Body.addChild(this.Pelvic_fin_side_1);
	this.Tail.addChild(this.Anal_fin);
	this.Tail.addChild(this.Caudal_fin);
	this.Upper_Jaw.addChild(this.Upper_teeth_side_2);
	this.Upper_Jaw.addChild(this.Front_upper_teeth);
	this.Eyeinnerside2.addChild(this.Eyeouterside2);
	this.Lower_jaw.addChild(this.Front_tooth_side_2);
	this.Barbel.addChild(this.Barbel_tip);
	this.Body.addChild(this.Lower_jaw);
	this.Body.addChild(this.Pelvic_fin_side_2);
	this.Lower_jaw.addChild(this.Teeth_side_2);
	this.Body.addChild(this.Tail);
	this.Eyeinnerside1.addChild(this.Eyeouterside1);
	this.Upper_Jaw.addChild(this.Eyeinnerside2);
	this.Tail.addChild(this.Dorsal_fin);
	this.Upper_Jaw.addChild(this.Eyeinnerside1);
	this.Lower_jaw.addChild(this.Front_tooth_side_1);
	this.Upper_Jaw.addChild(this.Upper_teeth_side_1);
	this.Lower_jaw.addChild(this.Teeth_side_1);
	this.Barbel_tip.addChild(this.Barbel_tip_appandage);
	this.Lower_jaw.addChild(this.Barbel_joint);
	this.Body.addChild(this.Upper_Jaw);
	this.Barbel_joint.addChild(this.Barbel);
	this.Body.addChild(this.Pectoral_fin_side_1);
	this.Lower_jaw.addChild(this.Fronttooth);
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
