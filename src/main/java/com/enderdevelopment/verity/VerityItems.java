package com.enderdevelopment.verity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public final class VerityItems {
    public static final Item VERITY_LENS = Registry.register(
            BuiltInRegistries.ITEM,
            ResourceLocation.fromNamespaceAndPath(VerityMod.MOD_ID, "verity_lens"),
            new VerityLensItem(new Item.Properties().stacksTo(1))
    );

    private VerityItems() {
    }

    public static void register() {
    }
}
