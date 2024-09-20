package io.github.hobbyshop.cw

import io.github.hobbyshop.cw.registry.ModRegistry
import net.fabricmc.api.ModInitializer
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