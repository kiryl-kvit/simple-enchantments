package com.kvit.mixin;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantable;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnchantmentHelper.class)
public class MixinEnchantmentHelper {

    /**
     * The enchanting table uses the ENCHANTABLE component in both cost generation
     * and enchantment selection. Items like dirt do not have that component, so
     * vanilla produces zero costs and no offers. We supply a fallback value only
     * for ENCHANTABLE lookups in those methods so arbitrary unenchanted items can
     * generate normal enchanting offers.
     */
    @Redirect(
            method = {"getEnchantmentCost", "selectEnchantment"},
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;get(Lnet/minecraft/core/component/DataComponentType;)Ljava/lang/Object;"
            )
    )
    private static Object simpleEnchantments$fallbackEnchantable(ItemStack stack, DataComponentType<?> type) {
        Object value = stack.get(type);
        if (value == null && type == DataComponents.ENCHANTABLE) {
            return new Enchantable(10);
        }
        return value;
    }
}
