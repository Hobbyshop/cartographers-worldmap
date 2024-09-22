package io.github.hobbyshop.cw.registry

import io.github.hobbyshop.cw.items.WorldmapItem
import io.github.hobbyshop.cw.ui.handlers.WorldmapScreenHandler
import io.github.hobbyshop.cw.utils.id
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.`object`.builder.v1.trade.TradeOfferHelper
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.ContainerComponent
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.resource.featuretoggle.FeatureSet
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.text.Text
import net.minecraft.util.Rarity
import net.minecraft.village.TradeOffer
import net.minecraft.village.TradedItem
import net.minecraft.village.VillagerProfession

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

    val WORLDMAP_SCREEN_HANDLER = ScreenHandlerType({ syncId, playerInv -> WorldmapScreenHandler(syncId, playerInv) }, FeatureSet.empty())

    private val CARTOGRAPHERS_PEN = Item(Item.Settings().rarity(Rarity.UNCOMMON))
    private val WORLDMAP = WorldmapItem(Item.Settings().maxCount(1).component(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT))
    private val CARTOGRAPHY_INK = Item(Item.Settings())

    fun createRegistry() {
        Registry.register(Registries.ITEM_GROUP, id("group"), ITEM_GROUP)

        Registry.register(Registries.SCREEN_HANDLER, id("worldmap_screen"), WORLDMAP_SCREEN_HANDLER)

        Registry.register(Registries.ITEM, id("cartographers_pen"), CARTOGRAPHERS_PEN)
        Registry.register(Registries.ITEM, id("worldmap"), WORLDMAP)
        Registry.register(Registries.ITEM, id("cartography_ink"), CARTOGRAPHY_INK)
        this.registerVillagerTrades()
    }

    private fun registerVillagerTrades() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 5) { offers ->
            val item = TradedItem(Items.EMERALD, 20)
            val offer = TradeOffer(item, ItemStack(CARTOGRAPHERS_PEN), 4, 1, 0.2f)
            offers.add { _, _ -> offer }
        }
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 3) { offers ->
            val item = TradedItem(Items.EMERALD, 2)
            val offer = TradeOffer(item, ItemStack(CARTOGRAPHY_INK, 4), 16, 1, 0.08f)
            offers.add { _, _ -> offer }
        }
    }

}