package almalexy.dtv.block;

import almalexy.dtv.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * ShadeVinesPlantBlock — тело лианы Shade Vines.
 */
public class ShadeVinesPlantBlock extends GrowingPlantBodyBlock {

    private static final VoxelShape SHAPE = Shapes.block(); // форма блока лозы

    public ShadeVinesPlantBlock() {
        super(BlockBehaviour.Properties.of()
                        .mapColor(MapColor.COLOR_BLACK)
                        .noCollission()
                        .randomTicks()
                        .strength(0.2F)
                        .sound(SoundType.WEEPING_VINES)
                        .pushReaction(PushReaction.DESTROY),
                Direction.DOWN,
                SHAPE,
                false);
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) ModBlocks.SHADE_VINES.get();
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos abovePos = pos.above();
        BlockState aboveState = level.getBlockState(abovePos);
        // Тело выживает, если над ним тело, голова, листва или твёрдый блок
        return aboveState.is(this)
                || aboveState.is(this.getHeadBlock())
                || aboveState.is(net.minecraft.tags.BlockTags.LEAVES)
                || aboveState.isFaceSturdy(level, abovePos, Direction.DOWN);
    }
}
