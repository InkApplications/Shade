package inkapplications.shade.button.structures

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Metadata describing a button resource.
 */
@Serializable
data class ButtonMetadata(
    /**
     * Control identifier of the switch which is unique per device.
     *
     * Meaning in combination with type:
     * - dots: Number of dots
     * - number: Number printed on device
     * - other: A logical order of controls in switch
     */
    @SerialName("control_id")
    val controlId: Int,
)
