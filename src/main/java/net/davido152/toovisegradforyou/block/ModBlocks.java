package net.davido152.toovisegradforyou.block;

import net.davido152.toovisegradforyou.TooVisegradForYou;
import net.davido152.toovisegradforyou.item.ModCreativeModeTab;
import net.davido152.toovisegradforyou.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TooVisegradForYou.MOD_ID);

    public static final RegistryObject<BeerMugBlock> EMPTY_BEER_MUG = BLOCKS.register("empty_beer_mug",
            () -> new BeerMugBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(0.7f).noOcclusion()));
    public static final RegistryObject<BeerMugBlock> FULL_BEER_MUG = BLOCKS.register("full_beer_mug",
            () -> new BeerMugBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(0.7f).noOcclusion()));
    public static final RegistryObject<MilkCauldronBlock> MILK_CAULDRON = BLOCKS.register("milk_cauldron",
            () -> new MilkCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON)));
    public static final RegistryObject<ButterCauldronBlock> BUTTER_CAULDRON = BLOCKS.register("butter_cauldron",
            () -> new ButterCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON)));
    public static final RegistryObject<Block> BUTTER_BLOCK = registerBlock("butter_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.CLAY, MaterialColor.QUARTZ).speedFactor(0.8f).sound(SoundType.SLIME_BLOCK)),
            ModCreativeModeTab.TOOVISEGRADFORYOU_TAB);


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
