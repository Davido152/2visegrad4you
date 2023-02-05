package net.davido152.toovisegradforyou.mixin;

import net.davido152.toovisegradforyou.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(AbstractCauldronBlock.class)
public abstract class MilkCauldronMixin {
    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    public void milkBucketUse(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit, CallbackInfoReturnable<InteractionResult> cir){
        if(pPlayer.getItemInHand(pHand).is(Items.MILK_BUCKET)) {
            pLevel.setBlockAndUpdate(pPos, ModBlocks.MILK_CAULDRON.get().defaultBlockState());
            pPlayer.setItemInHand(pHand, new ItemStack(Items.BUCKET));
            pLevel.playSound(pPlayer, pPos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0f, 1.0f);
            cir.setReturnValue(InteractionResult.SUCCESS);
        } else if(pPlayer.getItemInHand(pHand).is(ModBlocks.BUTTER_BLOCK.get().asItem())) {
            pLevel.setBlockAndUpdate(pPos, ModBlocks.BUTTER_CAULDRON.get().defaultBlockState());
            pPlayer.getItemInHand(pHand).shrink(1);
            pLevel.playSound(pPlayer, pPos, SoundEvents.SLIME_BLOCK_PLACE, SoundSource.BLOCKS, 0.8f, 1.0f);
            cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }
}
