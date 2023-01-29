package net.davido152.toovisegradforyou.block;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BeerMugBlock extends Block {
    public static final VoxelShape VOXEL_SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);

    public BeerMugBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return VOXEL_SHAPE;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack beerMug = new ItemStack(pState.getBlock());

        PlayerInfo playerinfo = Minecraft.getInstance().getConnection().getPlayerInfo(pPlayer.getGameProfile().getId());
        if(playerinfo != null && playerinfo.getGameMode() == GameType.ADVENTURE) {
          return super.use(pState, pLevel, pPos, pPlayer, pHand ,pHit);
        } else if(pPlayer.isShiftKeyDown() && pPlayer.isCreative()) {
            if(pPlayer.getInventory().findSlotMatchingItem(beerMug) == -1) {
                pPlayer.getInventory().add(beerMug);
            }
            pLevel.removeBlock(pPos, false);
            pLevel.playSound(null, pPos, soundType.getBreakSound(), SoundSource.BLOCKS, (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * 0.8F);
            return InteractionResult.SUCCESS;
        } else if(pPlayer.isShiftKeyDown()) {
            pPlayer.getInventory().add(beerMug);
            pLevel.removeBlock(pPos, false);
            pLevel.playSound(null, pPos, soundType.getBreakSound(), SoundSource.BLOCKS, (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * 0.8F);
            return InteractionResult.SUCCESS;
        } else {
            return super.use(pState, pLevel, pPos, pPlayer, pHand ,pHit);
        }
    }
}
