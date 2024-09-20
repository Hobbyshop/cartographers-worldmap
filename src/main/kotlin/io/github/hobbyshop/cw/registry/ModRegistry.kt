package io.github.hobbyshop.cw.registry

import io.github.hobbyshop.cw.utils.id
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Rarity

object ModRegistry {

    private val ITEM_GROUP = FabricItemGroup.builder()
        .icon { ItemStack(WORLDMAP) }
        .displayName(Text.translatable("itemGroup.cartographers_worldmap.name"))
        .entries { _, entries ->
            entries.add(CARTOGRAPHERS_PEN)
            entries.add(WORLDMAP)
            entries.add(CARTOGRAPHY_INK)
        }
        .build()

    private val CARTOGRAPHERS_PEN = Item(Item.Settings().rarity(Rarity.UNCOMMON))
    private val WORLDMAP = Item(Item.Settings().maxCount(1))
    private val CARTOGRAPHY_INK = Item(Item.Settings())

    fun createRegistry() {
        Registry.register(Registries.ITEM_GROUP, id("group"), ITEM_GROUP)

        Registry.register(Registries.ITEM, id("cartographers_pen"), CARTOGRAPHERS_PEN)
        Registry.register(Registries.ITEM, id("worldmap"), WORLDMAP)
        Registry.register(Registries.ITEM, id("cartography_ink"), CARTOGRAPHY_INK)
    }

}