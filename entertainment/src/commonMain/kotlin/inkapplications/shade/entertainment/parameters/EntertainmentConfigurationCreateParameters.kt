package inkapplications.shade.entertainment.parameters

import inkapplications.shade.entertainment.structures.EntertainmentConfigurationMetadata
import inkapplications.shade.entertainment.structures.EntertainmentConfigurationType
import inkapplications.shade.structures.ResourceType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data used for creating a new entertainment configuration via the bridge.
 */
@Serializable
data class EntertainmentConfigurationCreateParameters(
    /**
     * Metadata containing the friendly name of the entertainment configuration.
     */
    val metadata: EntertainmentConfigurationMetadata,

    /**
     * Defines for which type of application this channel assignment was optimized.
     */
    @SerialName("configuration_type")
    val configurationType: EntertainmentConfigurationType,

    /**
     * Stream proxy configuration for this entertainment group.
     */
    @SerialName("stream_proxy")
    val streamProxy: StreamProxyCreateParameters? = null,

    /**
     * Entertainment service locations for lights in the zone.
     */
    val locations: EntertainmentLocationsCreateParameters,
)
