package github.kingvampire.DeepTrenches.core;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

import net.minecraftforge.fml.common.Mod;

@Mod(MODID)
public class DeepTrenches {

	public DeepTrenches() {
		EVENT_BUS.register(this);
	}

}
