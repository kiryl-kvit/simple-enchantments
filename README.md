# Simple Enchantments

## Changes

- Removes mutual exclusivity checks between enchantments, so normally incompatible enchantments can exist on the same item.
- Removes supported-item checks, so any enchantment can be applied to any item.
- Treats any unenchanted item as enchantable in the enchanting table UI, while still blocking already enchanted items there.
- Gives items without a vanilla enchantability component a fallback enchantability value so the enchanting table can still roll offers for them.
- Lets anvils apply enchantments to items that vanilla normally refuses to store enchantments on.
- Keeps vanilla max enchantment levels, enchanting costs, and the anvil level cap unchanged.

## Compatibility

- Minecraft `1.21.11`
- Fabric Loader `0.18.4+`
- Java `21`

## License

This project currently uses the example project's `CC0-1.0` license. See `LICENSE` for the full text.
