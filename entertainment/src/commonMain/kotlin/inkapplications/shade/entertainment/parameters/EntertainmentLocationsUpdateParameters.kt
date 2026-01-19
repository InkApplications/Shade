package inkapplications.shade.entertainment.parameters

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Entertainment service locations for updating an entertainment configuration.
 */
@Serializable
data class EntertainmentLocationsUpdateParameters(
    /**
     * List of service locations for lights in the entertainment zone.
     */
    @SerialName("service_locations")
    val serviceLocations: List<ServiceLocationUpdateParameters>,
)
