package net.davido152.toovisegradforyou.item;

import net.davido152.toovisegradforyou.TooVisegradForYou;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TooVisegradForYou.MOD_ID);

    public static final RegistryObject<Item> HOT_PEPPER = ITEMS.register("hot_pepper",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TOOVISEGRADFORYOU_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
