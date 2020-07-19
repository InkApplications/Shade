package inkapplications.shade.lights

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import inkapplications.shade.constructs.percent

/**
 * Convert internal hue representation to public object.
 */
internal object HueLightStateTransformer {
    @FromJson
    fun fromHue(state: HueLightState) = LightState(
        on = state.on,
        brightness = state.brightness ?: state.on.let { if (it) 100.percent else 0.percent },
        hue = state.hue,
        saturation = state.saturation,
        effect = state.effect,
        cieColorCoordinates = state.cieColorCoordinates,
        colorTemperature = state.colorTemperature,
        alert = state.alert,
        colorMode = state.colorMode,
        mode = state.mode,
        reachable = state.reachable
    )

    @ToJson
    fun toHue(state: LightState) = HueLightState(
        on = state.on,
        brightness = state.brightness,
        hue = state.hue,
        saturation = state.saturation,
        effect = state.effect,
        cieColorCoordinates = state.cieColorCoordinates,
        colorTemperature = state.colorTemperature,
        alert = state.alert,
        colorMode = state.colorMode,
        mode = state.mode,
        reachable = state.reachable
    )
}
