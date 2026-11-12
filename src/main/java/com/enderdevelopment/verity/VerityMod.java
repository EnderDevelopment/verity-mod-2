package com.enderdevelopment.verity;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;

public final class VerityMod implements ModInitializer {
    public static final String MOD_ID = "verity";

    @Override
    public void onInitialize() {
        VerityItems.register();
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries ->
                entries.accept(VerityItems.VERITY_LENS));
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
                VerityCommands.register(dispatcher));
    }
}
