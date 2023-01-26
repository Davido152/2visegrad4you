package net.davido152.toovisegradforyou.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab TOOVISEGRADFORYOU_TAB = new CreativeModeTab("toovisegradforyoutab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.HOT_PEPPER.get());
        }
    };
}
