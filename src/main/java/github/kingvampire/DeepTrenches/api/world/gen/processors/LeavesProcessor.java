package github.kingvampire.DeepTrenches.api.world.gen.processors;

import static net.minecraft.block.LeavesBlock.PERSISTENT;
import static net.minecraft.world.gen.feature.template.IStructureProcessorType.BLOCK_ROT;

import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.Template.BlockInfo;

public class LeavesProcessor extends StructureProcessor {

    @Override
    protected IStructureProcessorType getType() {
	return BLOCK_ROT;
    }

    @Override
    public BlockInfo process(IWorldReader worldIn, BlockPos pos, BlockInfo info, BlockInfo blockInfo,
	    PlacementSettings settings, Template template) {

	if (blockInfo.state.getBlock() instanceof LeavesBlock && blockInfo.state.has(PERSISTENT)) {
	    BlockInfo leaves = new BlockInfo(blockInfo.pos, blockInfo.state.with(PERSISTENT, false), blockInfo.nbt);

	    return super.process(worldIn, pos, info, leaves, settings, template);
	}

	return super.process(worldIn, pos, info, blockInfo, settings, template);
    }

    @Override
    protected <T> Dynamic<T> serialize0(DynamicOps<T> ops) {
	return new Dynamic<T>(ops);
    }

}
