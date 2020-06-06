package github.kingvampire.DeepTrenches.core.entity.models;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DeepLakeBettaModel<T extends Entity> extends EntityModel<T> {
    
    public RendererModel Body;
    public RendererModel Lower_jaw;
    public RendererModel Head;
    public RendererModel Dorsal_fin;
    public RendererModel Caudal_fin;
    public RendererModel Pectoralfin_1;
    public RendererModel Pectoral_fin_2;
    public RendererModel Lower_jaw_tip;
    public RendererModel Lower_jaw_side_teeth_1;
    public RendererModel Lower_jaw_side_teeth_2;
    public RendererModel Lower_jaw_front_side_teeth_1;
    public RendererModel Lower_jaw_front_side_teeth_side_2;
    public RendererModel Lower_jaw_front_teeth;
    public RendererModel Snout;
    public RendererModel Bioluminescentappandage;
    public RendererModel Bioluminescent_appandage;

    public DeepLakeBettaModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Head = new RendererModel(this, 20, 2);
        this.Head.setRotationPoint(0.0F, 2.0F, 2.0F);
        this.Head.addBox(-2.0F, -1.4F, -5.7F, 4, 3, 4, 0.0F);
        this.Lower_jaw_side_teeth_2 = new RendererModel(this, 20, 12);
        this.Lower_jaw_side_teeth_2.setRotationPoint(1.9F, -1.0F, -1.0F);
        this.Lower_jaw_side_teeth_2.addBox(0.0F, -1.7F, -4.0F, 0, 2, 4, 0.0F);
        this.Lower_jaw_tip = new RendererModel(this, 36, 11);
        this.Lower_jaw_tip.setRotationPoint(0.0F, -1.0F, -5.7F);
        this.Lower_jaw_tip.addBox(-1.5F, 0.0F, -1.8F, 3, 1, 2, 0.0F);
        this.Pectoralfin_1 = new RendererModel(this, 48, 0);
        this.Pectoralfin_1.setRotationPoint(1.9F, 5.3F, 0.0F);
        this.Pectoralfin_1.addBox(0.0F, 0.0F, 0.0F, 0, 8, 4, 0.0F);
        this.Lower_jaw_front_side_teeth_1 = new RendererModel(this, 29, 14);
        this.Lower_jaw_front_side_teeth_1.setRotationPoint(-1.4F, 0.0F, 0.0F);
        this.Lower_jaw_front_side_teeth_1.addBox(0.0F, -1.5F, -1.7F, 0, 2, 2, 0.0F);
        this.Body = new RendererModel(this, 0, 0);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.addBox(-2.0F, 0.0F, 0.0F, 4, 6, 6, 0.0F);
        this.Lower_jaw_side_teeth_1 = new RendererModel(this, 20, 12);
        this.Lower_jaw_side_teeth_1.setRotationPoint(-1.9F, -1.0F, -1.0F);
        this.Lower_jaw_side_teeth_1.addBox(0.0F, -1.7F, -4.0F, 0, 2, 4, 0.0F);
        this.Caudal_fin = new RendererModel(this, 0, 8);
        this.Caudal_fin.setRotationPoint(0.0F, 2.5F, 4.0F);
        this.Caudal_fin.addBox(0.0F, -5.5F, 0.0F, 0, 11, 11, 0.0F);
        this.Bioluminescent_appandage = new RendererModel(this, 48, 13);
        this.Bioluminescent_appandage.setRotationPoint(-0.5F, 7.5F, 0.0F);
        this.Bioluminescent_appandage.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.Lower_jaw = new RendererModel(this, 20, 9);
        this.Lower_jaw.setRotationPoint(0.0F, 4.1F, 2.0F);
        this.Lower_jaw.addBox(-2.0F, -1.0F, -5.7F, 4, 2, 4, 0.0F);
        this.Pectoral_fin_2 = new RendererModel(this, 48, 0);
        this.Pectoral_fin_2.setRotationPoint(-1.9F, 5.3F, -0.1F);
        this.Pectoral_fin_2.addBox(0.0F, 0.0F, 0.0F, 0, 8, 4, 0.0F);
        this.Lower_jaw_front_side_teeth_side_2 = new RendererModel(this, 29, 14);
        this.Lower_jaw_front_side_teeth_side_2.setRotationPoint(1.4F, 0.0F, 0.0F);
        this.Lower_jaw_front_side_teeth_side_2.addBox(0.0F, -1.5F, -1.7F, 0, 2, 2, 0.0F);
        this.Dorsal_fin = new RendererModel(this, 0, 7);
        this.Dorsal_fin.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Dorsal_fin.addBox(0.0F, -2.8F, 0.0F, 0, 3, 6, 0.0F);
        this.Snout = new RendererModel(this, 36, 4);
        this.Snout.setRotationPoint(0.0F, 0.0F, -5.6F);
        this.Snout.addBox(-1.5F, -0.4F, -1.8F, 3, 2, 2, 0.0F);
        this.Lower_jaw_front_teeth = new RendererModel(this, 34, 16);
        this.Lower_jaw_front_teeth.setRotationPoint(0.0F, 0.0F, -1.69F);
        this.Lower_jaw_front_teeth.addBox(-1.5F, -1.5F, 0.0F, 3, 2, 0, 0.0F);
        this.Bioluminescentappandage = new RendererModel(this, 48, 13);
        this.Bioluminescentappandage.setRotationPoint(-0.5F, 7.5F, 0.0F);
        this.Bioluminescentappandage.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.Body.addChild(this.Head);
        this.Lower_jaw.addChild(this.Lower_jaw_side_teeth_2);
        this.Lower_jaw.addChild(this.Lower_jaw_tip);
        this.Body.addChild(this.Pectoralfin_1);
        this.Lower_jaw_tip.addChild(this.Lower_jaw_front_side_teeth_1);
        this.Lower_jaw.addChild(this.Lower_jaw_side_teeth_1);
        this.Body.addChild(this.Caudal_fin);
        this.Pectoral_fin_2.addChild(this.Bioluminescent_appandage);
        this.Body.addChild(this.Lower_jaw);
        this.Body.addChild(this.Pectoral_fin_2);
        this.Lower_jaw_tip.addChild(this.Lower_jaw_front_side_teeth_side_2);
        this.Body.addChild(this.Dorsal_fin);
        this.Head.addChild(this.Snout);
        this.Lower_jaw_tip.addChild(this.Lower_jaw_front_teeth);
        this.Pectoralfin_1.addChild(this.Bioluminescentappandage);
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
