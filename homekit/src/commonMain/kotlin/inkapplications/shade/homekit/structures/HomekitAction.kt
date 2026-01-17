package inkapplications.shade.homekit.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Action that can be performed on a homekit resource.
 */
@JvmInline
@Serializable
value class HomekitAction(val key: String) {
    override fun toString(): String = key

    companion object {
        /**
         * Reset homekit.
         *
         * Removes all pairings and reset state and Bonjour service to
         * factory settings.
         *
         * The Homekit will start functioning after approximately 10 seconds.
         */
        val Reset = HomekitAction("homekit_reset")
    }
}
