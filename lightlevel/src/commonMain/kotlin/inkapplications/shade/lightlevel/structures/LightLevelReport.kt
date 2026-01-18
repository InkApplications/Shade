package inkapplications.shade.lightlevel.structures

import inkapplications.shade.structures.Illuminance
import inkapplications.shade.structures.ScaledLuxSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant

/**
 * Report containing light level sensor data.
 */
@Serializable
data class LightLevelReport(
    /**
     * Last time the value of this property was changed.
     */
    val changed: Instant,

    /**
     * Light level measured by sensor.
     */
    @SerialName("light_level")
    @Serializable(with = ScaledLuxSerializer::class)
    val lightLevel: Illuminance,
)
