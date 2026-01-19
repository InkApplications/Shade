package inkapplications.shade.entertainment.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Action that can be taken on an entertainment configuration to control streaming.
 *
 * If status is "inactive" -> write start to start streaming.
 * Writing start when it's already active does not change the ownership of the streaming.
 * If status is "active" -> write "stop" to end the current streaming.
 * In order to start streaming when other application is already streaming, first write "stop" and then "start".
 */
@JvmInline
@Serializable
value class EntertainmentConfigurationAction(val key: String) {
    override fun toString(): String = key

    companion object {
        /**
         * Start the entertainment stream.
         */
        val Start = EntertainmentConfigurationAction("start")

        /**
         * Stop the entertainment stream.
         */
        val Stop = EntertainmentConfigurationAction("stop")
    }
}
