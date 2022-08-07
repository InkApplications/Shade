package inkapplications.shade.lights.parameters

import inkapplications.shade.serialization.MiredSerializer
import inkapplications.spondee.measure.ColorTemperature
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Relative color temperature change for a light
 */
@Serializable
data class ColorTemperatureDeltaParameters(
    /**
     * The type of delta defined
     */
    val action: DeltaAction,

    /**
     * Delta of color temperature to be added or removed from the light's
     * current state.
     */
    @SerialName("mirek_delta")
    @Serializable(with = MiredSerializer::class)
    val temperatureDelta: ColorTemperature? = null,
)
