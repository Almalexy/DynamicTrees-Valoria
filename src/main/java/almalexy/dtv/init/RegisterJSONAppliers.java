package almalexy.dtv.init;

import com.ferreusveritas.dynamictrees.api.applier.ApplierRegistryEvent;
import com.ferreusveritas.dynamictrees.deserialisation.PropertyAppliers;
import com.ferreusveritas.dynamictrees.tree.family.Family;
import com.google.gson.JsonElement;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * RegisterJSONAppliers — класс регистрации JSON-апплаеров для аддона Dynamic Trees: Valoria.
 * В этом аддоне JSON-апплаеры пока не используются, так как все деревья описаны в JSON-файлах.
 * Структура класса оставлена для будущего использования, если понадобятся кастомные поля для других деревьев.
 */
@Mod.EventBusSubscriber(modid = "dtv", bus = Mod.EventBusSubscriber.Bus.MOD)
public final class RegisterJSONAppliers {

    @SubscribeEvent
    public static void registerFamilyAppliers(ApplierRegistryEvent.Reload<Family, JsonElement> event) {
        registerFamilyAppliers(event.getAppliers());
    }

    @SubscribeEvent
    public static void registerFamilyAppliers(ApplierRegistryEvent.GatherData<Family, JsonElement> event) {
        registerFamilyAppliers(event.getAppliers());
    }

    private static void registerFamilyAppliers(PropertyAppliers<Family, JsonElement> appliers) {
        // добавить кастомные апплаеры при необходимости
    }
}
