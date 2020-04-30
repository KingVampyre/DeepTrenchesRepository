package github.kingvampire.DeepTrenches.core.entity.models;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StaspModel<T extends Entity> extends EntityModel<T> {
	public RendererModel Body;
	public RendererModel Trunk;
	public RendererModel Wing_1;
	public RendererModel Wing_2;
	public RendererModel Eyes;
	public RendererModel Legs_1;
	public RendererModel Legs_2;
	public RendererModel Legs_3;
	public RendererModel Abdomen;
	public RendererModel Dart;
	public RendererModel shape11;

	public StaspModel() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.Trunk = new RendererModel(this, 18, 0);
		this.Trunk.setRotationPoint(1.5F, 1.5F, 6.0F);
		this.Trunk.addBox(0.0F, 0.0F, 0.0F, 1, 1, 8, 0.0F);

		this.Abdomen = new RendererModel(this, 36, 0);
		this.Abdomen.setRotationPoint(-1.0F, -1.0F, 6.0F);
		this.Abdomen.addBox(0.0F, 0.0F, 0.0F, 3, 3, 7, 0.0F);

		this.Legs_2 = new RendererModel(this, 0, 24);
		this.Legs_2.setRotationPoint(0.0F, 4.0F, 1.7F);
		this.Legs_2.addBox(0.0F, 0.0F, 0.0F, 4, 4, 0, 0.0F);

		this.Wing_1 = new RendererModel(this, 8, 17);
		this.Wing_1.setRotationPoint(-3.999999999999969F, 0.4000000000000077F, 4.8F);
		this.Wing_1.addBox(0.0F, 0.0F, 0.0F, 6, 0, 13, 0.0F);

		this.Wing_2 = new RendererModel(this, 8, 17);
		this.Wing_2.mirror = true;
		this.Wing_2.setRotationPoint(2.0F, 0.4F, 4.8F);
		this.Wing_2.addBox(0.0F, 0.0F, 0.0F, 6, 0, 13, 0.0F);

		this.Body = new RendererModel(this, 0, 8);
		this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Body.addBox(0.0F, 0.0F, 0.0F, 4, 4, 6, 0.0F);

		this.Eyes = new RendererModel(this, 0, 0);
		this.Eyes.setRotationPoint(-1.0F, -1.0F, -1.0F);
		this.Eyes.addBox(0.0F, 0.0F, 0.0F, 6, 4, 4, 0.0F);

		this.Legs_1 = new RendererModel(this, 0, 19);
		this.Legs_1.setRotationPoint(0.0F, 4.0F, 0.8F);
		this.Legs_1.addBox(0.0F, 0.0F, 0.0F, 4, 4, 0, 0.0F);

		this.Dart = new RendererModel(this, 53, 9);
		this.Dart.setRotationPoint(1.5F, 1.7F, 6.2F);
		this.Dart.addBox(0.0F, 0.0F, 0.0F, 0, 7, 1, 0.0F);

		this.shape11 = new RendererModel(this, 22, 17);
		this.shape11.setRotationPoint(-1.0F, -0.4F, -12.7F);
		this.shape11.addBox(0.0F, 0.0F, 0.0F, 8, 0, 13, 0.0F);

		this.Legs_3 = new RendererModel(this, 9, 24);
		this.Legs_3.setRotationPoint(0.0F, 4.0F, 2.6F);
		this.Legs_3.addBox(0.0F, 0.0F, 0.0F, 4, 4, 0, 0.0F);

		this.Body.addChild(this.Trunk);
		this.Trunk.addChild(this.Abdomen);
		this.Body.addChild(this.Legs_2);
		this.Body.addChild(this.Wing_1);
		this.Body.addChild(this.Wing_2);
		this.Body.addChild(this.Eyes);
		this.Body.addChild(this.Legs_1);
		this.Abdomen.addChild(this.Dart);
		this.Eyes.addChild(this.shape11);
		this.Body.addChild(this.Legs_3);
	}

	@Override
	public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {

		this.Body.render(scale);
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch, float scaleFactor) {

		this.Trunk.rotateAngleX = -0.3185225884889762F;
		this.Abdomen.rotateAngleX = -6.200655107570901E-17F;

		this.Legs_1.rotateAngleX = 0.18203784098300857F;
		this.Legs_2.rotateAngleX = 0.18203784098300857F;
		this.Legs_3.rotateAngleX = 0.18203784098300857F;

		this.Wing_1.rotateAngleX = 0.2275909344600604F;
		this.Wing_2.rotateAngleX = 0.2275909344600604F;

		this.Dart.rotateAngleX = 0.6373942428283295F;
		this.shape11.rotateAngleX = -0.04537856055185257F;
	}

}
