package net.davido152.toovisegradforyou.util;

import net.davido152.toovisegradforyou.TooVisegradForYou;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(TooVisegradForYou.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Items {
        public static TagKey<Item> PEPPER_CROPS = forgeTag("crops/pepper");
        public static TagKey<Item> CHILI_EPPER_CROPS = forgeTag("crops/chilipepper");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(TooVisegradForYou.MOD_ID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
