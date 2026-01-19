package inkapplications.shade.entertainment.structures

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Locations of entertainment services within an entertainment configuration.
 */
@Serializable
data class EntertainmentLocations(
    /**
     * List of service locations for lights in the entertainment zone.
     */
    @SerialName("service_locations")
    val serviceLocations: List<ServiceLocation>,
)
