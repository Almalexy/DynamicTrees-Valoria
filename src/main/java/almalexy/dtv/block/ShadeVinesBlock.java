package almalexy.dtv.block;

import almalexy.dtv.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

/**
 * ShadeVinesBlock — независимая реализация головы лианы Shade Vines.
 * Управляет ростом лианы вниз, используя собственную систему блоков без зависимости от ванильных Weeping Vines.
 */
public class ShadeVinesBlock extends GrowingPlantHeadBlock implements BonemealableBlock {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_25;
    private static final VoxelShape SHAPE = Shapes.block(); // стандартная форма лозы

    public ShadeVinesBlock() {
        super(BlockBehaviour.Properties.of()
                        .mapColor(MapColor.COLOR_BLACK)
                        .noCollission()
                        .randomTicks()
                        .strength(0.2F)
                        .sound(SoundType.WEEPING_VINES)
                        .pushReaction(PushReaction.DESTROY),
                Direction.DOWN,
                SHAPE,
                false,
                0.1D);
    }

    @Override
    protected int getBlocksToGrowWhenBonemealed(RandomSource random) {
        return 1 + random.nextInt(2);
    }

    @Override
    protected boolean canGrowInto(BlockState state) {
        return state.isAir() || state.is(ModBlocks.SHADE_VINES_PLANT.get());
    }

    @Override
    protected GrowingPlantBodyBlock getBodyBlock() {
        return (GrowingPlantBodyBlock) ModBlocks.SHADE_VINES_PLANT.get();
    }

    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    protected int getMaxAge() {
        return 25;
    }

    /**
     * Автоматический рост вниз при randomTick.
     * С шансом 10% проверяет нижний блок и растёт, если там воздух.
     */
    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // шанс роста (0.1 = 10%)
        if (random.nextFloat() > 0.05f) {
            return;
        }

        BlockPos below = pos.below();

        // если снизу воздух — растём
        if (level.isEmptyBlock(below)) {
            level.setBlock(below, this.defaultBlockState(), 3);
        }
    }

    /**
     * Проверка, можно ли использовать костную муку на этом блоке.
     */
    @Override
    public boolean isValidBonemealTarget(LevelReader reader, BlockPos pos, BlockState state, boolean isClient) {
        return reader.isEmptyBlock(pos.below());
    }

    /**
     * Всегда успешно при использовании костной муки.
     */
    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    /**
     * Рост от костной муки - удлиняет лиану вниз.
     */
    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        BlockPos below = pos.below();
        if (level.isEmptyBlock(below)) {
            level.setBlock(below, this.defaultBlockState(), 3);
        }
    }

}
