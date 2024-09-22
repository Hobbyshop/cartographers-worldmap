package io.github.hobbyshop.cw.items

import io.github.hobbyshop.cw.ui.handlers.WorldmapScreenHandler
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.screen.SimpleNamedScreenHandlerFactory
import net.minecraft.text.Text
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class WorldmapItem(settings: Settings) : Item(settings) {

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        if (!world.isClient) {
            user.openHandledScreen(SimpleNamedScreenHandlerFactory({ syncId, playerInv, player ->
                WorldmapScreenHandler(syncId, playerInv)
            }, Text.translatable("item.cartographers_worldmap.worldmap")))
        }

        return TypedActionResult.success(user.getStackInHand(hand))
    }

}