package almalexy.dtv.init;

import almalexy.dtv.DynamicTreesValoria;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * DTVClient — класс клиентской инициализации для аддона Dynamic Trees: Valoria.
 * Этот класс используется для клиентской инициализации (цвета листвы, визуальные настройки).
 */
@Mod.EventBusSubscriber(modid = DynamicTreesValoria.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class DTVClient {

    /**
     * Инициализирует клиентскую часть мода.
     * Регистрирует окраску листвы для Shade Vines.
     */
    public static void setup() {
        // Регистрируем окраску листвы для Shade Vines
        Minecraft.getInstance().getBlockColors().register(
            (state, level, pos, index) ->
                level != null && pos != null
                    ? BiomeColors.getAverageFoliageColor(level, pos)
                    : FoliageColor.getDefaultColor(),
            ModBlocks.SHADE_VINES.get(),
            ModBlocks.SHADE_VINES_PLANT.get()
        );
    }
    
    /**
     * Настраивает render layers для блоков (вызывается из FMLClientSetupEvent).
     */
    public static void setupRenderLayers() {
        // Устанавливаем cutout render layer для Shade Vines
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SHADE_VINES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SHADE_VINES_PLANT.get(), RenderType.cutout());
    }
    
    /**
     * Регистрирует цвета блоков (tint).
     */
    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        BlockColors blockColors = event.getBlockColors();
        
        // Регистрируем tint для Shade Vines (tintIndex=0 для tint-текстуры)
        blockColors.register((state, level, pos, tintIndex) -> {
            // Тинт ТОЛЬКО на tintindex=0 (будет использоваться в элементах модели по методу Valoria)
            if (tintIndex == 0) {
                if (level != null && pos != null) {
                    return BiomeColors.getAverageFoliageColor(level, pos);
                }
                return FoliageColor.getDefaultColor();
            }
            return -1;  // остальные слои НЕ окрашиваем
        }, ModBlocks.SHADE_VINES.get(), ModBlocks.SHADE_VINES_PLANT.get());
    }
    
}
