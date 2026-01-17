package inkapplications.shade.homekit.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Status indicating whether homekit is paired, currently open for pairing, or unpaired.
 *
 * Transitions:
 * - unpaired > pairing – pushlink button press or power cycle
 * - pairing > paired – through HAP
 * - pairing > unpaired – 10 minutes
 * - paired > unpaired – homekit reset
 */
@JvmInline
@Serializable
value class HomekitStatus(val key: String) {
    override fun toString(): String = key

    companion object {
        /**
         * Homekit is paired with the bridge.
         */
        val Paired = HomekitStatus("paired")

        /**
         * Homekit is currently in pairing mode.
         */
        val Pairing = HomekitStatus("pairing")

        /**
         * Homekit is not paired with the bridge.
         */
        val Unpaired = HomekitStatus("unpaired")
    }
}
