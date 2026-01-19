package inkapplications.shade.entertainment.parameters

import inkapplications.shade.structures.Position
import inkapplications.shade.structures.ResourceReference
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Service location for updating an entertainment configuration.
 */
@Serializable
data class ServiceLocationUpdateParameters(
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
     * Relative equalization factor applied to the entertainment service,
     * to compensate for differences in brightness in the entertainment configuration.
     *
     * Value cannot be 0, writing 0 changes it to lowest possible value.
     * Default value is 1, maximum is 1.
     */
    @SerialName("equalization_factor")
    val equalizationFactor: Float? = null,
)
