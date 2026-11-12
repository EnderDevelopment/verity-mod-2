package com.enderdevelopment.verity;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public final class VerityCommands {
    private VerityCommands() {
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("verity")
                .then(Commands.literal("give")
                        .executes(context -> giveLens(context.getSource()))));
    }

    private static int giveLens(CommandSourceStack source) {
        ServerPlayer player = source.getPlayer();
        if (player == null) {
            source.sendFailure(Component.translatable("commands.verity.give.failure"));
            return 0;
        }

        ItemStack lens = new ItemStack(VerityItems.VERITY_LENS);
        if (!player.getInventory().add(lens)) {
            player.drop(lens, false);
        }
        source.sendSuccess(() -> Component.translatable(
                "commands.verity.give.success", player.getDisplayName()), true);
        return 1;
    }
}
