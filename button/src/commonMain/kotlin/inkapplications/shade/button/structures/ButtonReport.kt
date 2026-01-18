package inkapplications.shade.button.structures

import kotlinx.serialization.Serializable
import kotlin.time.Instant

/**
 * Report of the last button event.
 */
@Serializable
data class ButtonReport(
    /**
     * Last time the value of this property was updated.
     */
    val updated: Instant,

    /**
     * Event which was sent by the button control.
     */
    val event: ButtonEvent,
)

