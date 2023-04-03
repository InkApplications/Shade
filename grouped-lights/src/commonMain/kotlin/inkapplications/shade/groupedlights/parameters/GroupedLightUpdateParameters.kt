package inkapplications.shade.groupedlights.parameters

import inkapplications.shade.lights.parameters.*
import inkapplications.shade.structures.parameters.PowerParameters
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * State and settings that can be modified on a grouped light.
 */
@Serializable
data class GroupedLightUpdateParameters(
    /**
     * Specify a new power state for the joined light group.
     */
    @SerialName("on")
    val power: PowerParameters? = null,

    /**
     * Specify a new dimming state for the joined light group.
     */
    val dimming: DimmingParameters? = null,

    /**
     * Specify a relative dimming state to adjust on the light group.
     */
    @SerialName("dimming_delta")
    val dimmingDelta: DimmingDeltaParameters? = null,

    /**
     * Specify a new color temperature state for the joined light group.
     */
    @SerialName("color_temperature")
    val colorTemperature: ColorTemperatureParameters? = null,

    /**
     * Specify a relative color temperature change for the light group.
     */
    @SerialName("color_temperature_delta")
    val colorTemperatureDelta: ColorTemperatureDeltaParameters? = null,

    /**
     * Specify a new color state for the joined light group.
     */
    val color: ColorParameters? = null,

    /**
     * Specify a new joined alert state for the light group.
     */
    val alert: AlertParameters? = null,

    /**
     * Specify the dynamics for the state change.
     */
    val dynamics: DynamicsParameters? = null,
)