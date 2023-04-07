package net.davido152.toovisegradforyou.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
public class FullBeerCanItem extends Item {
    private ItemStack beerCan;

    public FullBeerCanItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        beerCan = pLivingEntity.getItemInHand(pLivingEntity.getUsedItemHand());

        if(pLivingEntity instanceof Player && !((Player) pLivingEntity).isCreative()) {
            ((Player) pLivingEntity).getInventory().add(beerCan);
        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }
}
