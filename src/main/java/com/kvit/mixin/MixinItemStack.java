package com.kvit.mixin;

import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class MixinItemStack {

    /**
     * Force every ItemStack to report itself as enchantable so the enchanting
     * table UI accepts any item (not just those with the ENCHANTABLE component).
     */
    @Inject(method = "isEnchantable", at = @At("HEAD"), cancellable = true)
    private void simpleEnchantments$alwaysEnchantable(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
