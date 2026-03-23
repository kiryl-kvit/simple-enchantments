package com.kvit.mixin;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantable;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnchantmentHelper.class)
public class MixinEnchantmentHelper {

    /**
     * In {@code selectEnchantment}, the method calls {@code ItemStack.get(DataComponents.ENCHANTABLE)}
     * and immediately returns an empty list if the result is null. We redirect that
     * call to return a fallback {@link Enchantable} with value 10 for items that
     * lack the component, allowing the enchanting table selection algorithm to
     * proceed for any item type.
     */
    @Redirect(
            method = "selectEnchantment",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;get(Lnet/minecraft/core/component/DataComponentType;)Ljava/lang/Object;"
            )
    )
    private static Object simpleEnchantments$fallbackEnchantable(ItemStack stack, DataComponentType<?> type) {
        Object value = stack.get(type);
        if (value == null) {
            return new Enchantable(10);
        }
        return value;
    }
}
