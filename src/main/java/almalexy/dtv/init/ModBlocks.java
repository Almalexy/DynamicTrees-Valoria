package almalexy.dtv.init;

import almalexy.dtv.DynamicTreesValoria;
import almalexy.dtv.block.ShadeVinesBlock;
import almalexy.dtv.block.ShadeVinesPlantBlock;
import almalexy.dtv.item.ShadeVinesPlaceItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * ModBlocks — класс регистрации блоков для аддона Dynamic Trees: Valoria.
 */
public class ModBlocks {
    
    public static final DeferredRegister<Block> BLOCKS = 
            DeferredRegister.create(ForgeRegistries.BLOCKS, DynamicTreesValoria.MOD_ID);
    
    public static final DeferredRegister<Item> ITEMS = 
            DeferredRegister.create(ForgeRegistries.ITEMS, DynamicTreesValoria.MOD_ID);
    
    // Блоки лиан Shade Vines
    public static final RegistryObject<Block> SHADE_VINES = BLOCKS.register("shade_vines", 
            ShadeVinesBlock::new);
    
    public static final RegistryObject<Block> SHADE_VINES_PLANT = BLOCKS.register("shade_vines_plant", 
            ShadeVinesPlantBlock::new);
    
    // Блок цветка Shade Blossom
    public static final RegistryObject<Block> SHADE_BLOSSOM = BLOCKS.register("shade_blossom", 
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .instabreak()
                    .sound(net.minecraft.world.level.block.SoundType.GRASS)
                    .pushReaction(PushReaction.DESTROY)
                    .offsetType(BlockBehaviour.OffsetType.XZ)));
    
    // Предмет Shade Vines (обычный Item, не BlockItem)
    public static final RegistryObject<Item> SHADE_VINES_ITEM = ITEMS.register("shade_vines_item",
            () -> new ShadeVinesPlaceItem(new Item.Properties().stacksTo(64)));
    
    /**
     * Регистрирует все блоки и предметы.
     */
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
    }
}

