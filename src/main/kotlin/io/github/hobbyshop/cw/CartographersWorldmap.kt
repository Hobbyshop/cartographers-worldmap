package io.github.hobbyshop.cw

import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object CartographersWorldmap : ModInitializer {

    val LOGGER: Logger = LoggerFactory.getLogger("CartographersWorldmap")

    override fun onInitialize() {
        LOGGER.info("Mod started yay")
    }

}