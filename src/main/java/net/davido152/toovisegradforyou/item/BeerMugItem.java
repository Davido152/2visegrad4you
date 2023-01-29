package net.davido152.toovisegradforyou.item;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;

public class BeerMugItem extends BlockItem {
    public BeerMugItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Player player = pContext.getPlayer();
        if(player.isShiftKeyDown()) {
            return super.useOn(pContext);
        } else {
            return InteractionResult.PASS;
        }
    }
}
