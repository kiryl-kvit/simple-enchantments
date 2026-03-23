package com.kvit.mixin;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class MixinItemStack {

    /**
     * Allow unenchanted items into the enchanting table even if they do not
     * have the vanilla ENCHANTABLE component, while still keeping vanilla's
     * rule that already enchanted items cannot be enchanted there again.
     */
    @Inject(method = "isEnchantable", at = @At("HEAD"), cancellable = true)
    private void simpleEnchantments$allowUnenchantedItems(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(!EnchantmentHelper.hasAnyEnchantments((ItemStack) (Object) this));
    }
}
