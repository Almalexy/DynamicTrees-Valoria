package almalexy.dtv.item;

import almalexy.dtv.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class ShadeVinesPlaceItem extends Item {

    public ShadeVinesPlaceItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction face = context.getClickedFace();

        // Разрешаем ставить ТОЛЬКО на потолок
        if (face != Direction.DOWN) {
            return InteractionResult.FAIL;
        }

        BlockPos placePos = pos.relative(Direction.DOWN);

        // Можно ли разместить блок
        if (!level.getBlockState(placePos).canBeReplaced()) {
            return InteractionResult.FAIL;
        }

        // Ставим головной блок лианы
        level.setBlock(placePos, ModBlocks.SHADE_VINES.get().defaultBlockState(), 3);

        // Тратим предмет
        ItemStack stack = context.getItemInHand();
        stack.shrink(1);

        return InteractionResult.SUCCESS;
    }
}

