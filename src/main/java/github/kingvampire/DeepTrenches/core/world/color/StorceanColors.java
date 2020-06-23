package github.kingvampire.DeepTrenches.core.world.color;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StorceanColors {

    private int[] colorBuffer;
    private int defaultColor;

    public StorceanColors(int defaultColor) {
	this.defaultColor = defaultColor;
    }

    public int get(double temperature, double humidity) {
	humidity = humidity * temperature;

	int i = (int) ((1 - temperature) * 255);
	int j = (int) ((1 - humidity) * 255);
	int k = j << 8 | i;

	return k > this.colorBuffer.length ? -65281 : this.colorBuffer[k];
    }

    public int getDefaultColor() {
	return this.defaultColor;
    }

    public void setColorizer(int[] colorBufferIn) {
	this.colorBuffer = colorBufferIn;
    }

}
