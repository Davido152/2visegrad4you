package net.davido152.toovisegradforyou.item;

import net.davido152.toovisegradforyou.TooVisegradForYou;
import net.davido152.toovisegradforyou.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;
import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TooVisegradForYou.MOD_ID);

    public static final RegistryObject<Item> HOT_PEPPER = ITEMS.register("hot_pepper",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TOOVISEGRADFORYOU_TAB).food(ModFoods.HOT_PEPPER)));
    public static final RegistryObject<BeerMugItem> EMPTY_BEER_MUG = ITEMS.register("empty_beer_mug",
            () -> new BeerMugItem(ModBlocks.EMPTY_BEER_MUG.get(), new Item.Properties().tab(ModCreativeModeTab.TOOVISEGRADFORYOU_TAB)));
    public static final RegistryObject<FullBeerMugItem> FULL_BEER_MUG = ITEMS.register("full_beer_mug",
            () -> new FullBeerMugItem(ModBlocks.FULL_BEER_MUG.get(), new Item.Properties().tab(ModCreativeModeTab.TOOVISEGRADFORYOU_TAB).food(ModFoods.FULL_BEER_MUG)));
    public static final RegistryObject<LigniteItem> LIGNITE = ITEMS.register("lignite",
            () -> new LigniteItem(new Item.Properties().tab(ModCreativeModeTab.TOOVISEGRADFORYOU_TAB)));
    public static final RegistryObject<FullBeerCanItem> FULL_SARIS_CAN = ITEMS.register("full_saris_can",
            () -> new FullBeerCanItem(new Item.Properties().tab(ModCreativeModeTab.TOOVISEGRADFORYOU_TAB).food(ModFoods.FULL_BEER_CAN)));
    public static final RegistryObject<EmptyBeerCanItem> EMPTY_SARIS_CAN = ITEMS.register("empty_saris_can",
            () -> new EmptyBeerCanItem(new Item.Properties().tab(ModCreativeModeTab.TOOVISEGRADFORYOU_TAB)));
    public static final RegistryObject<Item> SCHNITZEL = ITEMS.register("schnitzel",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TOOVISEGRADFORYOU_TAB).food(ModFoods.SCHNITZEL)));
    public static final RegistryObject<Item> RAW_SCHNITZEL = ITEMS.register("raw_schnitzel",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TOOVISEGRADFORYOU_TAB).food(ModFoods.RAW_SCHNITZEL)));
    public static final RegistryObject<Item> BUTTER = ITEMS.register("butter",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TOOVISEGRADFORYOU_TAB).food(ModFoods.BUTTER)) {
                @Override
                public int getUseDuration(ItemStack pStack) {
                    return 24;
                }
            });

    public static final RegistryObject<Item> SALT = ITEMS.register("salt",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> SULFUR = ITEMS.register("sulfur",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
