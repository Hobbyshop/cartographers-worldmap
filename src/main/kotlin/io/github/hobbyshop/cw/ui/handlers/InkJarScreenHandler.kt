package io.github.hobbyshop.cw.ui.handlers

import io.github.hobbyshop.cw.registry.ModRegistry
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.slot.Slot

class InkJarScreenHandler(syncId: Int, playerInv: PlayerInventory, private val inventory: Inventory) : ScreenHandler(ModRegistry.INK_JAR_SCREEN_HANDLER, syncId) {

    constructor(syncId: Int, playerInv: PlayerInventory) : this(syncId, playerInv, SimpleInventory(1))

    private val stack: ItemStack
        get() = inventory.getStack(0)

    init {
        checkSize(inventory, 1)
        inventory.onOpen(playerInv.player)

        // container inv
        addSlot(Slot(inventory, 0, 55, 35))

        // player inv
        for (y in 0..<3) {
            for (x in 0..<9) {
                addSlot(Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18))
            }
        }

        // player hotbar
        for (x in 0..<9) {
            addSlot(Slot(playerInv, x, 8 + x * 18, 142))
        }
    }

    override fun quickMove(player: PlayerEntity, index: Int): ItemStack {
        val slot = slots.getOrNull(index)
        if (slot == null || !slot.hasStack())
            return ItemStack.EMPTY

        val newStack = slot.stack.copy()
        if (index < inventory.size()) {
            if (!insertItem(slot.stack, inventory.size(), slots.size, true))
                return ItemStack.EMPTY
        } else if (insertItem(slot.stack, 0, inventory.size(), false))
            return ItemStack.EMPTY

        if (slot.stack.isEmpty)
            slot.stack = ItemStack.EMPTY
        else
            slot.markDirty()

        return newStack
    }

    override fun canUse(player: PlayerEntity) = inventory.canPlayerUse(player)

    override fun onClosed(player: PlayerEntity) {
        super.onClosed(player)

    }

}