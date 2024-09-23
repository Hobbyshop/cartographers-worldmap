package io.github.hobbyshop.cw

import io.github.hobbyshop.cw.registry.ModRegistry
import io.github.hobbyshop.cw.ui.screens.InkJarScreen
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.api.ModInitializer
import net.minecraft.client.gui.screen.ingame.HandledScreens
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Main class.
 */
object CartographersWorldmap : ModInitializer {

    val LOGGER: Logger = LoggerFactory.getLogger("CartographersWorldmap")

    override fun onInitialize() {
        ModRegistry.createRegistry()
    }

}

@Environment(EnvType.CLIENT)
object CartographersWorldmapClient : ClientModInitializer {

    override fun onInitializeClient() {
        HandledScreens.register(ModRegistry.INK_JAR_SCREEN_HANDLER) { handler, playerInv, name ->
            InkJarScreen(handler, playerInv, name)
        }
    }

}