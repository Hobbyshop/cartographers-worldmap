package io.github.hobbyshop.cw.ui.widgets

import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.text.Text
import java.awt.Color

class InkJarBackWidget(
    x: Int,y: Int, width: Int, height: Int,
    message: Text, onPress: PressAction
) : ButtonWidget(x, y, width, height, message, onPress, { text -> text.get() }) {

    override fun renderWidget(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        if (!hovered) return

        val color = Color(0, 0, 0, 100)
        context.fill(x, y, x + width, y + height, color.rgb)
        drawMessage(context, MinecraftClient.getInstance().textRenderer, -1)
    }

}