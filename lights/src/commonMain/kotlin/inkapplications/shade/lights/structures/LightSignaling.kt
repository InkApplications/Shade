package inkapplications.shade.lights.structures

import kotlinx.serialization.Serializable

/**
 * Signaling properties for a light.
 */
@Serializable
data class LightSignaling(
    /**
     * Indicates status of active signal. Not available when inactive.
     */
    val status: LightSignalStatus? = null,
)
