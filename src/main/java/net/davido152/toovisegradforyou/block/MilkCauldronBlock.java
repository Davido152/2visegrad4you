package net.davido152.toovisegradforyou.block;

import net.davido152.toovisegradforyou.core.ModCauldronInteraction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

public class MilkCauldronBlock extends AbstractCauldronBlock {
    public MilkCauldronBlock(Properties pProperties) {
        super(pProperties, ModCauldronInteraction.MILK);
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
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return new ItemStack(Blocks.CAULDRON);
    }
}
