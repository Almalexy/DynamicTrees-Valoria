package almalexy.dtv;

import almalexy.dtv.init.DTVClient;
import almalexy.dtv.init.DTVRegistries;
import almalexy.dtv.init.ModBlocks;
import com.ferreusveritas.dynamictrees.api.registry.RegistryHandler;
import com.ferreusveritas.dynamictrees.api.GatherDataHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * DynamicTreesValoria — главный класс мода-аддона Dynamic Trees: Valoria.
 * Добавляет поддержку дерева Eldritch из мода Valoria.
 * Все деревья описаны в JSON-файлах и регистрируются автоматически через Dynamic Trees API.
 */
@Mod(DynamicTreesValoria.MOD_ID)
public class DynamicTreesValoria {
    public static final String MOD_ID = "dtv";

    public DynamicTreesValoria() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        // Регистрация обработчиков событий
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::gatherData);
        
        // Инициализация регистраторов Dynamic Trees
        RegistryHandler.setup(MOD_ID);
        
        // Остальная инициализация
        DTVRegistries.setup();
        ModBlocks.register(modEventBus);
    }
    
    /**
     * Обработчик события общей инициализации.
     */
    private void commonSetup(final FMLCommonSetupEvent event) {
        // Common setup logic
    }
    
    /**
     * Обработчик события клиентской инициализации.
     */
    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            DTVClient.setup();
            DTVClient.setupRenderLayers();
        });
    }
    
    /**
     * Генерирует данные для деревьев из JSON-файлов.
     * Все деревья описаны в JSON и не требуют ручной регистрации в коде.
     */
    private void gatherData(final GatherDataEvent event) {
        GatherDataHelper.gatherAllData(MOD_ID, event);
    }
    
    /**
     * Создаёт ResourceLocation с modid "dtv".
     * @param name имя ресурса
     * @return ResourceLocation с modid "dtv" и указанным именем
     */
    public static ResourceLocation location(String name) {
        return new ResourceLocation(MOD_ID, name);
    }
}
