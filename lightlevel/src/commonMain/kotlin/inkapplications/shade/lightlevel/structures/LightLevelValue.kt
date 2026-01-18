package inkapplications.shade.lightlevel.structures

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Light level sensor data.
 */
@Serializable
data class LightLevelValue(
    /**
     * Detailed light level report with timestamp.
     */
    @SerialName("light_level_report")
    val lightLevelReport: LightLevelReport? = null,
)
