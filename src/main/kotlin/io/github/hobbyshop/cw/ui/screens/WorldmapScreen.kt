package io.github.hobbyshop.cw.ui.screens

import com.mojang.blaze3d.systems.RenderSystem
import io.github.hobbyshop.cw.ui.handlers.WorldmapScreenHandler
import io.github.hobbyshop.cw.ui.widgets.InkJarBackWidget
import io.github.hobbyshop.cw.utils.id
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.render.GameRenderer
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text

class WorldmapScreen(
    handler: WorldmapScreenHandler, inventory: PlayerInventory, title: Text
) : HandledScreen<WorldmapScreenHandler>(handler, inventory, title) {

    private val texture = id("textures/gui/map_background.png")

    override fun init() {
        super.init()

        addDrawableChild(InkJarBackWidget(
            x + 106,
            y + 16,
            54, 54,
            Text.translatable("screen.cartographers_worldmap.ink_jar.back")
        ) { button ->
            println("hello worldmap")
        })
    }

    override fun drawBackground(context: DrawContext, delta: Float, mouseX: Int, mouseY: Int) {
        RenderSystem.setShader { GameRenderer.getPositionTexProgram() }
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
        RenderSystem.setShaderTexture(0, texture)

        context.drawTexture(texture, x, y, 0, 0, backgroundWidth, backgroundHeight)
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        drawBackground(context, delta, mouseX, mouseY)
        super.render(context, mouseX, mouseY, delta)
        drawMouseoverTooltip(context, mouseX, mouseY)
    }

}