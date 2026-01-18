package inkapplications.shade.button.structures

import inkapplications.shade.serialization.MillisecondDurationSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Duration

/**
 * State of the button with last event and configuration.
 */
@Serializable
data class ButtonState(
    /**
     * Report of the last button event with timestamp.
     */
    @SerialName("button_report")
    val report: ButtonReport? = null,

    /**
     * Duration between repeat events when holding the button in milliseconds.
     */
    @SerialName("repeat_interval")
    @Serializable(with = MillisecondDurationSerializer::class)
    val repeatInterval: Duration? = null,

    /**
     * List of all button events that this device supports.
     */
    @SerialName("event_values")
    val events: List<ButtonEvent> = emptyList(),
)
