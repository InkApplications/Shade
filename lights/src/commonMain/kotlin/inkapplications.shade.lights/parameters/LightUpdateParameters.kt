package inkapplications.shade.lights.parameters

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data available to be updated on a light object via the bridge.
 *
 * All these fields are optional and if left blank will not be changed.
 */
@Serializable
data class LightUpdateParameters(
    /**
     * Power state of the light
     */
    @SerialName("on")
    val power: PowerParameters? = null,

    /**
     * Specify the brightness state of the light
     */
    val dimming: DimmingParameters? = null,

    /**
     * Specify a relative brightness change to implement on the light.
     */
    @SerialName("dimming_delta")
    val dimmingDelta: DimmingDeltaParameters? = null,

    /**
     * Specify the color temperature of the light
     */
    @SerialName("color_temperature")
    val colorTemperature: ColorTemperatureParameters? = null,

    /**
     * Specify a relative color temperature change to implement on the light.
     */
    @SerialName("color_temperature_delta")
    val colorTemperatureDelta: ColorTemperatureDeltaParameters? = null,

    /**
     * Specify a color to change the light to.
     */
    val color: ColorParameters? = null,

    /**
     * Specify the dynamics of the state change
     */
    val dynamics: DynamicsParameters? = null,

    /**
     * Specify an alert state on the light
     */
    val alert: AlertParameters? = null,

    /**
     * Specify a gradient to implement on the light.
     */
    val gradient: GradientParameters? = null,

    /**
     * Specify a lighting effect for the light
     */
    val effects: EffectsParameters? = null,

    /**
     * Specify a timed lighting effect for the light.
     */
    @SerialName("timed_effects")
    val timedEffects: TimedEffectsParameters? = null,
)

