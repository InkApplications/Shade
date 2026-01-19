package inkapplications.shade.entertainment.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Identifier for an entertainment channel.
 */
@JvmInline
@Serializable
value class EntertainmentChannelId(val value: Int) {
    override fun toString(): String = value.toString()
}
