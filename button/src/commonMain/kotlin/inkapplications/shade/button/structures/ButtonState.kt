package inkapplications.shade.button.structures

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * State of the button with last event and configuration.
 */
@Serializable
data class ButtonState(
    /**
     * Report of the last button event with timestamp.
     */
    @SerialName("button_report")
    val buttonReport: ButtonReport? = null,

    /**
     * Duration between repeat events when holding the button in milliseconds.
     */
    @SerialName("repeat_interval")
    val repeatInterval: Int? = null,

    /**
     * List of all button events that this device supports.
     */
    @SerialName("event_values")
    val eventValues: List<ButtonEvent>? = null,
)
