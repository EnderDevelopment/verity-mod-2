package com.enderdevelopment.verity;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;

public final class VerityLensItem extends Item {
    public VerityLensItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.verity.verity_lens.tooltip"));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockState state = context.getLevel().getBlockState(context.getClickedPos());
        MutableComponent header = Component.translatable("verity.inspect.header");
        MutableComponent block = Component.translatable(
                "verity.inspect.block",
                state.getBlock().builtInRegistryHolder().key().location().toString()
        );
        MutableComponent hardness = Component.translatable(
                "verity.inspect.hardness",
                formatHardness(state.getDestroySpeed(context.getLevel(), context.getClickedPos()))
        );
        MutableComponent position = Component.translatable(
                "verity.inspect.position",
                context.getClickedPos().toShortString()
        );

        if (!context.getLevel().isClientSide()) {
            context.getPlayer().displayClientMessage(header, false);
            context.getPlayer().displayClientMessage(block, false);
            context.getPlayer().displayClientMessage(hardness, false);
            context.getPlayer().displayClientMessage(position, false);
        }
        return InteractionResult.sidedSuccess(context.getLevel().isClientSide());
    }

    private static String formatHardness(float hardness) {
        if (hardness < 0.0F) {
            return "unbreakable";
        }
        return String.format(java.util.Locale.ROOT, "%.2f", hardness);
    }
}
