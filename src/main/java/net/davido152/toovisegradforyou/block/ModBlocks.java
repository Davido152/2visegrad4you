package net.davido152.toovisegradforyou.block;

import net.davido152.toovisegradforyou.TooVisegradForYou;
import net.davido152.toovisegradforyou.item.ModCreativeModeTab;
import net.davido152.toovisegradforyou.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TooVisegradForYou.MOD_ID);

    public static final RegistryObject<Block> EMPTY_BEER_MUG = registerBlock("empty_beer_mug",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(1f).noOcclusion()), ModCreativeModeTab.TOOVISEGRADFORYOU_TAB);

    public static final RegistryObject<Block> FULL_BEER_MUG = registerBlock("full_beer_mug",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(1f).noOcclusion()), ModCreativeModeTab.TOOVISEGRADFORYOU_TAB);

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }
    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                           CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
