package github.kingvampire.DeepTrenches.api.enums;

import net.minecraft.util.IStringSerializable;

public enum TreeSize implements IStringSerializable {
	SMALL, MEDIUM, LARGE;

	@Override
	public String getName() {
		return this.name().toLowerCase();
	}

	@Override
	public String toString() {
		return this.getName();
	}

}
