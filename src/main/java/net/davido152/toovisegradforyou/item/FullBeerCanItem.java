package net.davido152.toovisegradforyou.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
public class FullBeerCanItem extends Item {
    private ItemStack beerMug;

    public FullBeerCanItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if(pStack.is(ModItems.FULL_SARIS_CAN.get())) {
            beerMug = new ItemStack(ModItems.EMPTY_SARIS_CAN.get());
        }

        if(pLivingEntity instanceof Player && !((Player) pLivingEntity).isCreative()) {
            ((Player) pLivingEntity).getInventory().add(beerMug);
        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }
}
