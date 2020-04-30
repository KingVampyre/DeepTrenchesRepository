package github.kingvampire.DeepTrenches.core.entity;

import static github.kingvampire.DeepTrenches.core.blocks.StaspNestBlock.SAP;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.STASP;
import static github.kingvampire.DeepTrenches.core.init.TileEntities.STASP_NEST;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class StaspNestTileEntity extends TileEntity implements ITickableTileEntity {

	public static class Stasp {

		private CompoundNBT compound;
		private int ticksInside;

		public Stasp(CompoundNBT compound, int ticksInside) {
			this.ticksInside = ticksInside;
			this.compound = compound;
		}

		public Stasp(CompoundNBT compound, Random rand) {
			this.ticksInside = 6000 + rand.nextInt(3400);
			this.compound = compound;
		}

		public CompoundNBT getCompound() {
			return this.compound;
		}

		public int getTicksInside() {
			return this.ticksInside;
		}

		public void setTicksInside(int ticksInside) {
			this.ticksInside = ticksInside;
		}

	}

	private int aqueanSap;
	private List<Stasp> stasps;

	public StaspNestTileEntity() {
		super(STASP_NEST);

		this.stasps = Lists.newArrayList();
	}

	public void addStasp(StaspEntity entity) {
		CompoundNBT compound = new CompoundNBT();
		Random random = entity.getRNG();

		int aqueanSap = entity.getAqueanSap();

		entity.writeAdditional(compound);
		compound.remove("Pollen");

		if (this.stasps.add(new Stasp(compound, random))) {
			int curr = this.aqueanSap + aqueanSap;

			entity.remove();

			if (curr >= 300)
				this.setAqueanSap(300);
			else
				this.setAqueanSap(curr);

		}

	}

	public boolean canAddStasp() {
		return this.stasps.size() < 3;
	}

	public int geAqueanSap() {
		return this.aqueanSap;
	}

	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);

		ListNBT list = compound.getList("Stasps", 10);

		for (int i = 0; i <= list.size(); i++) {
			CompoundNBT compoundNBT = list.getCompound(i);

			CompoundNBT entity = compoundNBT.getCompound("Entity");
			int ticksInside = compoundNBT.getInt("TicksInside");

			this.stasps.add(new Stasp(entity, ticksInside));
		}

		this.setAqueanSap(compound.getInt("AqueanSap"));

	}

	public void setAqueanSap(int aqueanSap) {
		this.aqueanSap = aqueanSap;
	}

	public void spawnAllStasps(LivingEntity livingEntity) {

		for (Stasp stasp : this.stasps)
			this.spawnStasp(stasp, livingEntity);

		this.stasps.clear();

	}

	private void spawnStasp(Stasp stasp, @Nullable LivingEntity livingEntity) {
		StaspEntity staspEntity = new StaspEntity(STASP, this.world);
		CompoundNBT compound = stasp.getCompound();

		int ticks = compound.getInt("TicksSincePollination");
		int ticksInside = stasp.getTicksInside();

		if (livingEntity != null)
			staspEntity.setRevengeTarget(livingEntity);

		int x = this.pos.getX();
		int y = this.pos.getY();
		int z = this.pos.getZ();

		staspEntity.setPosition(x, y + 1, z);
		staspEntity.setTicksSincePollination(ticks + ticksInside);
		staspEntity.setWingColor(compound.getInt("WingColor"));

		this.world.addEntity(staspEntity);
	}

	@Override
	public void tick() {

		if (!this.world.isRemote()) {
			List<Stasp> stasps = Lists.newArrayList();

			for (Stasp stasp : this.stasps) {
				int ticks = stasp.getTicksInside();

				if (ticks == 0) {
					this.spawnStasp(stasp, null);

					stasps.add(stasp);
				} else
					stasp.setTicksInside(--ticks);
			}

			this.stasps.removeAll(stasps);

			BlockState state = this.world.getBlockState(this.pos);

			if (this.aqueanSap == 0 && state.get(SAP) || this.aqueanSap > 0 && !state.get(SAP))
				this.world.setBlockState(pos, state.with(SAP, this.aqueanSap > 0), 3);
		}
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		CompoundNBT compoundNBT = super.write(compound);
		ListNBT list = new ListNBT();

		for (Stasp stasp : this.stasps) {
			CompoundNBT staspCompound = new CompoundNBT();
			CompoundNBT entity = stasp.getCompound();

			staspCompound.put("Entity", entity);
			staspCompound.putInt("TicksInside", stasp.getTicksInside());

			list.add(staspCompound);
		}

		compoundNBT.put("Stasps", list);
		compoundNBT.putInt("AqueanSap", this.geAqueanSap());

		return compoundNBT;
	}

}
