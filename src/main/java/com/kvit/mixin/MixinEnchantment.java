package com.kvit.mixin;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Enchantment.class)
public class MixinEnchantment {

    /**
     * @author simple-enchantments
     * @reason Remove all mutual exclusivity restrictions between enchantments.
     *         Returning true unconditionally means no two enchantments are ever
     *         considered incompatible, which fixes the anvil penalty branch,
     *         the enchanting-table pool pruning, and the /enchant command check.
     */
    @Overwrite
    public static boolean areCompatible(Holder<Enchantment> a, Holder<Enchantment> b) {
        return true;
    }

    /**
     * @author simple-enchantments
     * @reason Allow any enchantment to be applied to any item type via the anvil
     *         and the /enchant command, bypassing the supportedItems tag check.
     */
    @Overwrite
    public boolean canEnchant(ItemStack stack) {
        return true;
    }

    /**
     * @author simple-enchantments
     * @reason Allow any enchantment to appear as a primary candidate in the
     *         enchanting table pool for any item, bypassing the primaryItems check.
     */
    @Overwrite
    public boolean isPrimaryItem(ItemStack stack) {
        return true;
    }
}
