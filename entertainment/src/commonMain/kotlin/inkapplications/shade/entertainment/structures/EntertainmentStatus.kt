package inkapplications.shade.entertainment.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Reports if an entertainment stream is active or not.
 */
@JvmInline
@Serializable
value class EntertainmentStatus(val key: String) {
    override fun toString(): String = key

    companion object {
        /**
         * The entertainment stream is currently active.
         */
        val Active = EntertainmentStatus("active")

        /**
         * The entertainment stream is currently inactive.
         */
        val Inactive = EntertainmentStatus("inactive")
    }
}
