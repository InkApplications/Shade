package inkapplications.shade.entertainment.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Defines for which type of application an entertainment configuration channel assignment was optimized for.
 */
@JvmInline
@Serializable
value class EntertainmentConfigurationType(val key: String) {
    override fun toString(): String = key

    companion object {
        /**
         * Channels are organized around content from a screen.
         */
        val Screen = EntertainmentConfigurationType("screen")

        /**
         * Channels are organized around content from one or several monitors.
         */
        val Monitor = EntertainmentConfigurationType("monitor")

        /**
         * Channels are organized for music synchronization.
         */
        val Music = EntertainmentConfigurationType("music")

        /**
         * Channels are organized to provide 3D spatial effects.
         */
        val ThreeDSpace = EntertainmentConfigurationType("3dspace")

        /**
         * General use case.
         */
        val Other = EntertainmentConfigurationType("other")
    }
}
