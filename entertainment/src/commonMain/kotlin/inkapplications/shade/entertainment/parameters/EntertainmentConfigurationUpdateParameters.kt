package inkapplications.shade.entertainment.parameters

import inkapplications.shade.entertainment.structures.EntertainmentConfigurationAction
import inkapplications.shade.entertainment.structures.EntertainmentConfigurationMetadata
import inkapplications.shade.entertainment.structures.EntertainmentConfigurationType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data used for updating an existing entertainment configuration via the bridge.
 *
 * All properties are optional - only the specified fields will be updated.
 */
@Serializable
data class EntertainmentConfigurationUpdateParameters(
    /**
     * Metadata containing the friendly name of the entertainment configuration.
     */
    val metadata: EntertainmentConfigurationMetadata? = null,

    /**
     * Defines for which type of application this channel assignment was optimized.
     */
    @SerialName("configuration_type")
    val configurationType: EntertainmentConfigurationType? = null,

    /**
     * Action to control streaming.
     *
     * If status is "inactive" -> write start to start streaming.
     * If status is "active" -> write "stop" to end the current streaming.
     * In order to start streaming when other application is already streaming,
     * first write "stop" and then "start".
     */
    val action: EntertainmentConfigurationAction? = null,

    /**
     * Stream proxy configuration for this entertainment group.
     */
    @SerialName("stream_proxy")
    val streamProxy: StreamProxyCreateParameters? = null,

    /**
     * Entertainment service locations for lights in the zone.
     */
    val locations: EntertainmentLocationsUpdateParameters? = null,
)
