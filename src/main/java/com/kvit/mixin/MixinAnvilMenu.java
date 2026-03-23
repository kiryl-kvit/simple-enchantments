package com.kvit.mixin;

import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AnvilMenu.class)
public class MixinAnvilMenu {

    /**
     * In {@code createResult}, the anvil bails out early with an empty result
     * when {@code EnchantmentHelper.canStoreEnchantments(inputItem)} returns false
     * (i.e. the item has neither ENCHANTMENTS nor STORED_ENCHANTMENTS component).
     * We redirect that call to always return true so any item can receive
     * enchantments through the anvil.
     */
    @Redirect(
            method = "createResult",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;canStoreEnchantments(Lnet/minecraft/world/item/ItemStack;)Z"
            )
    )
    private boolean simpleEnchantments$alwaysCanStoreEnchantments(ItemStack stack) {
        return true;
    }
}
