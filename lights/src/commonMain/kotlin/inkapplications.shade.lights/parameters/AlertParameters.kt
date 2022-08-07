package inkapplications.shade.lights.parameters

import inkapplications.shade.lights.structures.AlertEffectType
import kotlinx.serialization.Serializable

/**
 * Change alert state of the light
 */
@Serializable
data class AlertParameters(
    /**
     * Alert effect to set on the light.
     */
    val action: AlertEffectType,
)
