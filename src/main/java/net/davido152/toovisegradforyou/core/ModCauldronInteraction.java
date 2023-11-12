package net.davido152.toovisegradforyou.core;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.davido152.toovisegradforyou.block.ModBlocks;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Map;
import java.util.function.Predicate;

public interface ModCauldronInteraction extends CauldronInteraction {
    Map<Item, CauldronInteraction> MILK = newInteractionMap();
    Map<Item, CauldronInteraction> BUTTER = newInteractionMap();
    CauldronInteraction FILL_MILK = (pState, pLevel, pPos, pPlayer, pHand, pFilledStack) -> {
        return emptyBucket(pLevel, pPos, pPlayer, pHand, pFilledStack, ModBlocks.MILK_CAULDRON.get().defaultBlockState(), SoundEvents.BUCKET_EMPTY);
    };
    CauldronInteraction REAL = new CauldronInteraction() {
        @Override
        public InteractionResult interact(BlockState pBlockState, Level pLevel, BlockPos pBlockPos, Player pPlayer, InteractionHand pHand, ItemStack pStack) {
            return null;
        }
    };
    CauldronInteraction FILL_BUTTER = (pState, pLevel, pPos, pPlayer, pHand, pFilledStack) -> {
        return fillWithBlock(pState, pLevel, pPos, pPlayer, pHand, pFilledStack, ModBlocks.BUTTER_CAULDRON.get().defaultBlockState(), SoundEvents.SLIME_BLOCK_PLACE);
    };
    CauldronInteraction CHURN_BUTTER = (pState, pLevel, pPos, pPlayer, pHand, pFilledStack) -> {
        return churnButter(pLevel, pPos, pPlayer);
    };

    static Object2ObjectOpenHashMap<Item, CauldronInteraction> newInteractionMap() {
        return Util.make(new Object2ObjectOpenHashMap<>(), (p_175646_) -> {
            p_175646_.defaultReturnValue((pBlockState, pLevel, pPos, pPlayer, pHand, pFilledStack) -> {
                return takeBlock(pBlockState, pLevel, pPos, pPlayer, new ItemStack(ModBlocks.BUTTER_BLOCK.get()), (pState) -> {
                    return true;
                }, SoundEvents.SLIME_BLOCK_BREAK);
            });
        });
    }


    InteractionResult interact(BlockState pBlockState, Level pLevel, BlockPos pBlockPos, Player pPlayer, InteractionHand pHand, ItemStack pStack);

    static void bootStrap() {
        addDefaultInteractions(EMPTY);
        MILK.put(Items.STICK, CHURN_BUTTER);
        MILK.put(Items.BUCKET, (pBlockState, pLevel, pPos, pPlayer, pInteractionHand, pFilledStack) -> {
            return fillBucket(pBlockState, pLevel, pPos, pPlayer, pInteractionHand, pFilledStack, new ItemStack(Items.MILK_BUCKET), (pState) -> {
                return true;
            }, SoundEvents.BUCKET_FILL);
        });
        addDefaultInteractions(MILK);
        addDefaultInteractions(BUTTER);
    }

    static void addDefaultInteractions(Map<Item, CauldronInteraction> pInteractionsMap) {
        pInteractionsMap.put(Items.MILK_BUCKET, FILL_MILK);
        pInteractionsMap.put(ModBlocks.BUTTER_BLOCK.get().asItem(), FILL_BUTTER);

        pInteractionsMap.put(Items.LAVA_BUCKET, FILL_LAVA);
        pInteractionsMap.put(Items.WATER_BUCKET, FILL_WATER);
        pInteractionsMap.put(Items.POWDER_SNOW_BUCKET, FILL_POWDER_SNOW);
    }

    static InteractionResult fillBucket(BlockState pBlockState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, ItemStack pEmptyStack, ItemStack pFilledStack, Predicate<BlockState> pStatePredicate, SoundEvent pFillSound) {
        if (!pStatePredicate.test(pBlockState)) {
            return InteractionResult.PASS;
        } else {
            if (!pLevel.isClientSide) {
                Item item = pEmptyStack.getItem();
                pPlayer.setItemInHand(pHand, ItemUtils.createFilledResult(pEmptyStack, pPlayer, pFilledStack));
                pPlayer.awardStat(Stats.USE_CAULDRON);
                pPlayer.awardStat(Stats.ITEM_USED.get(item));
                pLevel.setBlockAndUpdate(pPos, Blocks.CAULDRON.defaultBlockState());
                pLevel.playSound((Player)null, pPos, pFillSound, SoundSource.BLOCKS, 1.0F, 1.0F);
                pLevel.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, pPos);
            }

            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        }
    }

    static InteractionResult emptyBucket(Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, ItemStack pFilledStack, BlockState pState, SoundEvent pEmptySound) {
        if (!pLevel.isClientSide) {
            Item item = pFilledStack.getItem();
            pPlayer.setItemInHand(pHand, ItemUtils.createFilledResult(pFilledStack, pPlayer, new ItemStack(Items.BUCKET)));
            pPlayer.awardStat(Stats.FILL_CAULDRON);
            pPlayer.awardStat(Stats.ITEM_USED.get(item));
            pLevel.setBlockAndUpdate(pPos, pState);
            pLevel.playSound((Player)null, pPos, pEmptySound, SoundSource.BLOCKS, 1.0F, 1.0F);
            pLevel.gameEvent((Entity)null, GameEvent.FLUID_PLACE, pPos);
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide);
    }

    static InteractionResult churnButter(Level pLevel, BlockPos pPos, Player pPlayer) {
        if (!pLevel.isClientSide) {
            pPlayer.awardStat(Stats.ITEM_USED.get(Items.STICK));
            pLevel.setBlockAndUpdate(pPos, ModBlocks.BUTTER_CAULDRON.get().defaultBlockState());
            pLevel.playSound((Player) null, pPos, SoundEvents.SLIME_BLOCK_HIT, SoundSource.BLOCKS, 1.0F, 1.0F);
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide);
    }

    static InteractionResult fillWithBlock(BlockState pBlockState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, ItemStack pItem, BlockState pState, SoundEvent pSound) {
        if (!pBlockState.is(Blocks.CAULDRON)) {
            return InteractionResult.PASS;
        } else {
            if (!pLevel.isClientSide) {
                pPlayer.getItemInHand(pHand).shrink(1);
                pPlayer.awardStat(Stats.FILL_CAULDRON);
                pPlayer.awardStat(Stats.ITEM_USED.get(pItem.getItem()));
                pLevel.setBlockAndUpdate(pPos, pState);
                pLevel.playSound((Player) null, pPos, pSound, SoundSource.BLOCKS, 1.0F, 1.0F);
                pLevel.gameEvent((Entity) null, GameEvent.BLOCK_PLACE, pPos);
            }

            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        }
    }

    static InteractionResult takeBlock(BlockState pBlockState, Level pLevel, BlockPos pPos, Player pPlayer, ItemStack pBlock, Predicate<BlockState> pStatePredicate, SoundEvent pSound) {
        if (!pStatePredicate.test(pBlockState)) {
            return InteractionResult.PASS;
        } else {
            if (!pLevel.isClientSide) {
                pPlayer.getInventory().add(new ItemStack(pBlock.getItem()));
                pPlayer.awardStat(Stats.USE_CAULDRON);
                pLevel.setBlockAndUpdate(pPos, Blocks.CAULDRON.defaultBlockState());
                pLevel.playSound((Player)null, pPos, pSound, SoundSource.BLOCKS, 1.0F, 1.0F);
                pLevel.gameEvent((Entity)null, GameEvent.BLOCK_DESTROY, pPos);
            }

            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        }
    }
}
