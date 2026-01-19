package inkapplications.shade.entertainment.parameters

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Entertainment service locations for creating an entertainment configuration.
 */
@Serializable
data class EntertainmentLocationsCreateParameters(
    /**
     * List of service locations for lights in the entertainment zone.
     */
    @SerialName("service_locations")
    val serviceLocations: List<ServiceLocationCreateParameters>,
)
