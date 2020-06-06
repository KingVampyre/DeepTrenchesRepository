package github.kingvampire.DeepTrenches.api.enums;

import javax.annotation.Nullable;

import net.minecraft.util.IStringSerializable;

public enum LitState implements IStringSerializable {
    ALL_LIT, ALL_UNLIT, BODY, FLANK, FLANK_AND_LURE, FLANK_AND_SUBORBITAL, LURE, RECOGNITION, SUBORBITAL,
    SUBORBITAL_AND_LURE;

    @Nullable
    public static LitState getState(String name) {

	for (LitState type : LitState.values())
	    if (type.getName().equals(name))
		return type;

	return null;
    }

    @Override
    public String getName() {
	return this.name().toLowerCase();
    }

    @Override
    public String toString() {
	return this.getName();
    }

}
