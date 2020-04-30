package github.kingvampire.DeepTrenches.core.entity.models;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AdaiggerModel extends Model {
	public RendererModel Handle;
	public RendererModel round_thing;
	public RendererModel Middle;
	public RendererModel Guard_1;
	public RendererModel Guard_5;
	public RendererModel Large_blade_thing4;
	public RendererModel Guard_2;
	public RendererModel Guard_3;
	public RendererModel Guard_4;
	public RendererModel Guard_6;
	public RendererModel Guard_7;
	public RendererModel Guard_8;
	public RendererModel shape13;

	public AdaiggerModel() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.Guard_4 = new RendererModel(this, 33, 15);
		this.Guard_4.setRotationPoint(0.0F, 1.0F, 3.0F);
		this.Guard_4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
		this.Guard_2 = new RendererModel(this, 11, 15);
		this.Guard_2.setRotationPoint(0.0F, 2.0F, 2.0F);
		this.Guard_2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
		this.Handle = new RendererModel(this, 0, 7);
		this.Handle.setRotationPoint(0.0F, 18.0F, 4.0F);
		this.Handle.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
		this.Guard_3 = new RendererModel(this, 23, 12);
		this.Guard_3.setRotationPoint(0.0F, -1.0F, 1.0F);
		this.Guard_3.addBox(0.0F, 0.0F, 0.0F, 1, 2, 3, 0.0F);
		this.shape13 = new RendererModel(this, 0, 26);
		this.shape13.setRotationPoint(0.0F, 1.0F, -4.0F);
		this.shape13.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
		this.Large_blade_thing4 = new RendererModel(this, 0, 18);
		this.Large_blade_thing4.setRotationPoint(0.0F, 0.0F, -4.0F);
		this.Large_blade_thing4.addBox(0.0F, 0.0F, 0.0F, 1, 3, 4, 0.0F);
		this.round_thing = new RendererModel(this, 0, 0);
		this.round_thing.setRotationPoint(0.0F, -1.0F, 1.0F);
		this.round_thing.addBox(0.0F, 0.0F, 0.0F, 1, 3, 3, 0.0F);
		this.Guard_7 = new RendererModel(this, 23, 18);
		this.Guard_7.setRotationPoint(0.0F, 2.0F, 1.0F);
		this.Guard_7.addBox(0.0F, 0.0F, 0.0F, 1, 2, 3, 0.0F);
		this.Middle = new RendererModel(this, 0, 10);
		this.Middle.setRotationPoint(0.0F, -1.0F, -4.0F);
		this.Middle.addBox(0.0F, 0.0F, 0.0F, 1, 3, 4, 0.0F);
		this.Guard_5 = new RendererModel(this, 16, 18);
		this.Guard_5.setRotationPoint(0.0F, 3.0F, 0.0F);
		this.Guard_5.addBox(0.0F, 0.0F, 0.0F, 1, 3, 2, 0.0F);
		this.Guard_8 = new RendererModel(this, 33, 21);
		this.Guard_8.setRotationPoint(0.0F, 0.0F, 3.0F);
		this.Guard_8.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
		this.Guard_6 = new RendererModel(this, 11, 21);
		this.Guard_6.setRotationPoint(0.0F, 0.0F, 2.0F);
		this.Guard_6.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
		this.Guard_1 = new RendererModel(this, 16, 12);
		this.Guard_1.setRotationPoint(0.0F, -3.0F, 0.0F);
		this.Guard_1.addBox(0.0F, 0.0F, 0.0F, 1, 3, 2, 0.0F);
		this.Guard_3.addChild(this.Guard_4);
		this.Guard_1.addChild(this.Guard_2);
		this.Guard_1.addChild(this.Guard_3);
		this.Large_blade_thing4.addChild(this.shape13);
		this.Middle.addChild(this.Large_blade_thing4);
		this.Handle.addChild(this.round_thing);
		this.Guard_5.addChild(this.Guard_7);
		this.Handle.addChild(this.Middle);
		this.Middle.addChild(this.Guard_5);
		this.Guard_7.addChild(this.Guard_8);
		this.Guard_5.addChild(this.Guard_6);
		this.Middle.addChild(this.Guard_1);
	}

	public void renderer() {
		this.Handle.render(0.0625F);
	}

	public void setRotateAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
