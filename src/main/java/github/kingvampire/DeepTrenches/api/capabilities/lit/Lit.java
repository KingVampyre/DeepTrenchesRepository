package github.kingvampire.DeepTrenches.api.capabilities.lit;

import static github.kingvampire.DeepTrenches.api.enums.LitState.ALL_UNLIT;

import github.kingvampire.DeepTrenches.api.enums.LitState;

public class Lit implements ILit {

    protected LitState litState;

    public Lit() {
	// Defualt Constructor
	this.litState = ALL_UNLIT;
    }

    public Lit(LitState litState) {
	this.litState = litState;
    }

    @Override
    public LitState getLitState() {
	return this.litState;
    }

    @Override
    public void setLitState(LitState litState) {
	this.litState = litState;
    }

}
