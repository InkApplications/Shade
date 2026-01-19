package inkapplications.shade.entertainment.parameters

import inkapplications.shade.structures.Position
import inkapplications.shade.structures.ResourceReference
import kotlinx.serialization.Serializable

/**
 * Service location for creating an entertainment configuration.
 */
@Serializable
data class ServiceLocationCreateParameters(
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
)
