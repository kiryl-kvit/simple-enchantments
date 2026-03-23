package com.kvit.mixin;

import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnchantmentMenu.class)
public class MixinEnchantmentMenu {

    /**
     * The enchanting table only refreshes offers when the input stack reports
     * itself as enchantable. Redirect that check here so arbitrary unenchanted
     * items behave like valid table inputs regardless of whether the player puts
     * the item or lapis in first.
     */
    @Redirect(
            method = "slotsChanged",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;isEnchantable()Z"
            )
    )
    private boolean simpleEnchantments$allowUnenchantedItems(ItemStack stack) {
        return !EnchantmentHelper.hasAnyEnchantments(stack);
    }
}
