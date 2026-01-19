package inkapplications.shade.entertainment.structures

import inkapplications.shade.structures.Position
import inkapplications.shade.structures.ResourceReference
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Location information for an entertainment service within a configuration.
 */
@Serializable
data class ServiceLocation(
    /**
     * Reference to the entertainment service.
     */
    val service: ResourceReference,

    /**
     * Describes the location(s) of the service.
     *
     * Can contain 1-2 positions.
     */
    val positions: List<Position>,

    /**
     * Relative equalization factor applied to the entertainment service.
     *
     * This compensates for differences in brightness in the entertainment configuration.
     * Value cannot be 0; writing 0 changes it to the lowest possible value.
     */
    @SerialName("equalization_factor")
    val equalizationFactor: Double,
)
