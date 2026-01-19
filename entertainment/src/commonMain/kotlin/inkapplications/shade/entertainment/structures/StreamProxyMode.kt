package inkapplications.shade.entertainment.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Proxy mode used for an entertainment configuration group.
 */
@JvmInline
@Serializable
value class StreamProxyMode(val key: String) {
    override fun toString(): String = key

    companion object {
        /**
         * The bridge will select a proxy node automatically.
         */
        val Auto = StreamProxyMode("auto")

        /**
         * The proxy node has been set manually.
         */
        val Manual = StreamProxyMode("manual")
    }
}
