package github.kingvampire.DeepTrenches.core.entity.models.dragonfishes;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BlackLoosejawTransparentModel<T extends Entity> extends EntityModel<T> {

    public RendererModel Upper_Jaw;
    public RendererModel Eyeinnerside2;
    public RendererModel Eyeinnerside1;
    public RendererModel Upper_teeth_side_1;
    public RendererModel Front_upper_teeth;
    public RendererModel Upper_teeth_side_2;
    public RendererModel Body;
    public RendererModel Eyeouterside2;
    public RendererModel Eyeouterside1;
    public RendererModel Lower_jaw;
    public RendererModel Tail;
    public RendererModel Pectoral_fin_side_1;
    public RendererModel Pectoral_fin_side_2;
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

    public BlackLoosejawTransparentModel() {
	this.textureWidth = 128;
	this.textureHeight = 64;
	this.Upper_Jaw = new RendererModel(this, 0, 22);
	this.Upper_Jaw.setRotationPoint(0.0F, 16.0F, -8.0F);
	this.Upper_Jaw.addBox(-2.5F, -2.5F, -12.0F, 5, 6, 10, 0.0F);
	this.Lower_jaw = new RendererModel(this, 31, 25);
	this.Lower_jaw.setRotationPoint(0.0F, 8.0F, 2.0F);
	this.Lower_jaw.addBox(-2.5F, -2.0F, -12.0F, 5, 1, 13, 0.0F);
	this.setRotateAngle(Lower_jaw, 0.31869712141416456F, 0.0F, 0.0F);
	this.Teeth_side_1 = new RendererModel(this, 0, 30);
	this.Teeth_side_1.setRotationPoint(-2.4F, -1.5F, 0.0F);
	this.Teeth_side_1.addBox(0.0F, -2.45F, -11.0F, 0, 2, 8, 0.0F);
	this.setRotateAngle(Teeth_side_1, 0.045553093477052F, 0.0F, 0.0F);
	this.Teeth_side_2 = new RendererModel(this, 0, 30);
	this.Teeth_side_2.setRotationPoint(2.4F, -1.5F, 0.0F);
	this.Teeth_side_2.addBox(0.0F, -2.45F, -11.0F, 0, 2, 8, 0.0F);
	this.setRotateAngle(Teeth_side_2, 0.045553093477052F, 0.0F, 0.0F);
	this.Upper_teeth_side_2 = new RendererModel(this, 0, 35);
	this.Upper_teeth_side_2.setRotationPoint(2.4F, 2.5F, -11.3F);
	this.Upper_teeth_side_2.addBox(0.0F, 0.6F, 0.0F, 0, 1, 6, 0.0F);
	this.Front_tooth_side_2 = new RendererModel(this, 34, 34);
	this.Front_tooth_side_2.setRotationPoint(2.3F, -1.5F, -11.0F);
	this.Front_tooth_side_2.addBox(0.0F, -2.7F, -1.0F, 0, 2, 1, 0.0F);
	this.setRotateAngle(Front_tooth_side_2, 0.19198621771937624F, 0.0F, 0.0F);
	this.Upper_teeth_side_1 = new RendererModel(this, 0, 35);
	this.Upper_teeth_side_1.setRotationPoint(-2.4F, 2.5F, -11.3F);
	this.Upper_teeth_side_1.addBox(0.0F, 0.6F, 0.0F, 0, 1, 6, 0.0F);
	this.Eyeouterside1 = new RendererModel(this, 0, 15);
	this.Eyeouterside1.setRotationPoint(-0.5F, 1.0F, 1.0F);
	this.Eyeouterside1.addBox(-1.0F, -1.4F, -1.6F, 3, 3, 3, 0.0F);
	this.Pectoral_fin_side_2 = new RendererModel(this, 4, 1);
	this.Pectoral_fin_side_2.setRotationPoint(2.5F, 6.9F, -0.2F);
	this.Pectoral_fin_side_2.addBox(0.0F, 0.0F, 0.0F, 0, 9, 2, 0.0F);
	this.setRotateAngle(Pectoral_fin_side_2, 0.4642575810304917F, 0.0F, -0.13962634015954636F);
	this.Front_tooth_side_1 = new RendererModel(this, 34, 34);
	this.Front_tooth_side_1.setRotationPoint(-2.3F, -1.5F, -11.0F);
	this.Front_tooth_side_1.addBox(0.0F, -2.7F, -1.0F, 0, 2, 1, 0.0F);
	this.setRotateAngle(Front_tooth_side_1, 0.19198621771937624F, 0.0F, 0.0F);
	this.Caudal_fin = new RendererModel(this, 70, 37);
	this.Caudal_fin.setRotationPoint(0.0F, -1.0F, 10.9F);
	this.Caudal_fin.addBox(0.0F, -6.1F, 3.0F, 0, 13, 12, 0.0F);
	this.Front_upper_teeth = new RendererModel(this, 0, 43);
	this.Front_upper_teeth.setRotationPoint(0.0F, 3.3F, -11.7F);
	this.Front_upper_teeth.addBox(-2.5F, 0.0F, 0.0F, 5, 1, 0, 0.0F);
	this.Anal_fin = new RendererModel(this, 70, 30);
	this.Anal_fin.setRotationPoint(0.0F, 2.1F, 7.0F);
	this.Anal_fin.addBox(0.0F, 0.0F, 0.0F, 0, 5, 7, 0.0F);
	this.Pectoral_fin_side_1 = new RendererModel(this, 4, 1);
	this.Pectoral_fin_side_1.setRotationPoint(-2.5F, 6.9F, -0.2F);
	this.Pectoral_fin_side_1.addBox(0.0F, 0.0F, 0.0F, 0, 9, 2, 0.0F);
	this.setRotateAngle(Pectoral_fin_side_1, 0.4642575810304917F, 0.0F, 0.13962634015954636F);
	this.Tail = new RendererModel(this, 43, 45);
	this.Tail.setRotationPoint(0.0F, 3.5F, 12.7F);
	this.Tail.addBox(-1.0F, -3.3F, 3.0F, 2, 6, 11, 0.0F);
	this.Pelvic_fin_side_1 = new RendererModel(this, 8, 0);
	this.Pelvic_fin_side_1.setRotationPoint(-2.5F, 6.6F, 10.3F);
	this.Pelvic_fin_side_1.addBox(0.0F, 0.0F, 0.0F, 0, 8, 4, 0.0F);
	this.setRotateAngle(Pelvic_fin_side_1, 0.03490658503988659F, 0.0F, 0.13962634015954636F);
	this.Eyeouterside2 = new RendererModel(this, 0, 15);
	this.Eyeouterside2.mirror = true;
	this.Eyeouterside2.setRotationPoint(-1.6F, 1.0F, 1.0F);
	this.Eyeouterside2.addBox(0.0F, -1.4F, -1.6F, 3, 3, 3, 0.0F);
	this.Fronttooth = new RendererModel(this, 31, 33);
	this.Fronttooth.setRotationPoint(0.0F, -1.5F, -11.0F);
	this.Fronttooth.addBox(0.0F, -3.5F, -1.5F, 0, 3, 1, 0.0F);
	this.setRotateAngle(Fronttooth, 0.27314402793711257F, 0.0F, 0.0F);
	this.Eyeinnerside2 = new RendererModel(this, 13, 17);
	this.Eyeinnerside2.setRotationPoint(2.0F, -1.2F, -10.5F);
	this.Eyeinnerside2.addBox(0.0F, 0.0F, -0.2F, 1, 2, 2, 0.0F);
	this.Dorsal_fin = new RendererModel(this, 70, 36);
	this.Dorsal_fin.setRotationPoint(0.0F, 0.0F, 7.1F);
	this.Dorsal_fin.addBox(0.0F, -7.5F, 0.0F, 0, 5, 7, 0.0F);
	this.Body = new RendererModel(this, 0, 39);
	this.Body.setRotationPoint(0.0F, -2.5F, -2.0F);
	this.Body.addBox(-2.5F, 0.0F, 0.0F, 5, 7, 16, 0.0F);
	this.Eyeinnerside1 = new RendererModel(this, 13, 17);
	this.Eyeinnerside1.setRotationPoint(-2.0F, -1.2F, -10.5F);
	this.Eyeinnerside1.addBox(-1.0F, 0.0F, -0.2F, 1, 2, 2, 0.0F);
	this.Pelvic_fin_side_2 = new RendererModel(this, 8, 0);
	this.Pelvic_fin_side_2.setRotationPoint(2.5F, 6.6F, 10.3F);
	this.Pelvic_fin_side_2.addBox(0.0F, 0.0F, 0.0F, 0, 8, 4, 0.0F);
	this.setRotateAngle(Pelvic_fin_side_2, 0.03490658503988659F, 0.0F, -0.13962634015954636F);
	this.Body.addChild(this.Lower_jaw);
	this.Lower_jaw.addChild(this.Teeth_side_1);
	this.Lower_jaw.addChild(this.Teeth_side_2);
	this.Upper_Jaw.addChild(this.Upper_teeth_side_2);
	this.Lower_jaw.addChild(this.Front_tooth_side_2);
	this.Upper_Jaw.addChild(this.Upper_teeth_side_1);
	this.Eyeinnerside1.addChild(this.Eyeouterside1);
	this.Body.addChild(this.Pectoral_fin_side_2);
	this.Lower_jaw.addChild(this.Front_tooth_side_1);
	this.Tail.addChild(this.Caudal_fin);
	this.Upper_Jaw.addChild(this.Front_upper_teeth);
	this.Tail.addChild(this.Anal_fin);
	this.Body.addChild(this.Pectoral_fin_side_1);
	this.Body.addChild(this.Tail);
	this.Body.addChild(this.Pelvic_fin_side_1);
	this.Eyeinnerside2.addChild(this.Eyeouterside2);
	this.Lower_jaw.addChild(this.Fronttooth);
	this.Upper_Jaw.addChild(this.Eyeinnerside2);
	this.Tail.addChild(this.Dorsal_fin);
	this.Upper_Jaw.addChild(this.Body);
	this.Upper_Jaw.addChild(this.Eyeinnerside1);
	this.Body.addChild(this.Pelvic_fin_side_2);
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
