package inkapplications.shade.structures

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Information on Alert effects for a light
 */
@Serializable
data class AlertInfo(
    /**
     * Alert effects that the light supports.
     */
    @SerialName("action_values")
    val actionValues: List<AlertEffectType>
)
