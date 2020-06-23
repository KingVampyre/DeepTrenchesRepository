package github.kingvampire.DeepTrenches.api.capabilities.pollen;

public class Pollen implements IPollen {

    protected final int maxAqueanSap;
    protected final int maxPollen;

    protected int aqueanSap;
    protected int pollen;
    protected int ticksSincePollination;

    public Pollen() {
	this.maxAqueanSap = 100;
	this.maxPollen = 100;
    }

    public Pollen(int maxAqueanSap, int maxPollen) {
	this.maxAqueanSap = maxAqueanSap;
	this.maxPollen = maxPollen;
    }

    @Override
    public int getAqueanSap() {
	return this.aqueanSap;
    }

    @Override
    public int getMaxAqueanSap() {
	return this.maxAqueanSap;
    }

    @Override
    public int getMaxPollen() {
	return this.maxPollen;
    }

    @Override
    public int getPollen() {
	return this.pollen;
    }

    @Override
    public int getTicksSincePollination() {
	return this.ticksSincePollination;
    }

    @Override
    public void setAqueanSap(int aqueanSap) {
	this.aqueanSap = aqueanSap;
    }

    @Override
    public void setPollen(int pollen) {
	this.pollen = pollen;
    }

    @Override
    public void setTicksSincePollination(int ticksSincePollination) {
	this.ticksSincePollination = ticksSincePollination;
    }

}
