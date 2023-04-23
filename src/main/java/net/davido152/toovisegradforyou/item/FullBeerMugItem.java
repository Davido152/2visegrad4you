package net.davido152.toovisegradforyou.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class FullBeerMugItem extends BeerMugItem {
    public FullBeerMugItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        ItemStack beerMug = new ItemStack(ModItems.EMPTY_BEER_MUG.get());

        if(pLivingEntity instanceof Player && !((Player) pLivingEntity).isCreative()) {
            ((Player) pLivingEntity).getInventory().add(beerMug);
        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }

    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }

    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_DRINK;
    }
}
