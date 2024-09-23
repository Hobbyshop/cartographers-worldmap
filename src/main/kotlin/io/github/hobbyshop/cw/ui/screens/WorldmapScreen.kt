package io.github.hobbyshop.cw.ui.screens

import com.mojang.blaze3d.systems.RenderSystem
import io.github.hobbyshop.cw.utils.id
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.render.GameRenderer
import net.minecraft.text.Text
import java.awt.Color

class WorldmapScreen : Screen(Text.translatable("screen.cartographers_worldmap.map")) {

    val mapTexture = id("textures/gui/demo.png")

    var dragged = false
    var mapOffsetX = 0
    var mapOffsetY = 0

    override fun renderBackground(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        context.fill(0, 0, width, height, Color(0, 0, 0).rgb)

        RenderSystem.setShader { GameRenderer.getPositionTexProgram() }
        RenderSystem.setShaderTexture(0, mapTexture)
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
        context.drawTexture(mapTexture, mapOffsetX, mapOffsetY, 0f, 0f, width * 3, height * 3, width * 3, height * 3)
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        dragged = true
        return super.mouseClicked(mouseX, mouseY, button)
    }

    override fun mouseDragged(mouseX: Double, mouseY: Double, button: Int, deltaX: Double, deltaY: Double): Boolean {
        mapOffsetX += deltaX.toInt()
        mapOffsetY += deltaY.toInt()
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY)
    }

    override fun mouseReleased(mouseX: Double, mouseY: Double, button: Int): Boolean {
        dragged = false
        return super.mouseReleased(mouseX, mouseY, button)
    }

}