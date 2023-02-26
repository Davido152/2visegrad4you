package net.davido152.toovisegradforyou.block;

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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class MilkCauldronBlock extends AbstractCauldronBlock {
    public MilkCauldronBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties, null);
    }

    @Override
    protected double getContentHeight(BlockState pState) {
        return 0.9375D;
    }

    @Override
    public boolean isFull(BlockState pState) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState pState, Level pLevel, BlockPos pPos) {
        return 3;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(pPlayer.getItemInHand(pHand).is(Items.BUCKET)) {
            if (!pPlayer.isCreative()) {
                pPlayer.setItemInHand(pHand, new ItemStack(Items.MILK_BUCKET));
            } else if(pPlayer.isCreative() && !pPlayer.getInventory().contains(new ItemStack(Items.MILK_BUCKET)))
            {
                pPlayer.getInventory().add(new ItemStack(Items.MILK_BUCKET));
            }
            pLevel.setBlockAndUpdate(pPos, Blocks.CAULDRON.defaultBlockState());
            pLevel.playSound(pPlayer, pPos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * 0.8F);
            return InteractionResult.SUCCESS;
        } else if(pPlayer.getItemInHand(pHand).is(Items.STICK)) {
            pLevel.setBlockAndUpdate(pPos, ModBlocks.BUTTER_CAULDRON.get().defaultBlockState());
            pLevel.playSound(pPlayer, pPos, SoundEvents.SLIME_BLOCK_HIT, SoundSource.BLOCKS, (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * 0.8F);
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.PASS;
        }
    }
}
