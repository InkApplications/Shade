package inkapplications.shade.entertainment.structures

import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference
import inkapplications.shade.structures.ResourceType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Configuration for Hue Entertainment functionality.
 *
 * Entertainment configurations manage the setup for entertainment streaming,
 * including channel assignments and light positions.
 */
@Serializable
data class EntertainmentConfiguration(
    /**
     * Unique identifier representing a specific entertainment configuration instance.
     */
    val id: ResourceId,

    /**
     * Type of the resource (always entertainment_configuration).
     */
    val type: ResourceType = ResourceType.EntertainmentConfiguration,

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
     * Read-only field reporting if the stream is active or not.
     */
    val status: EntertainmentStatus,

    /**
     * Reference to the application streaming to this configuration.
     *
     * Only available if [status] is [EntertainmentStatus.Active].
     * Expected value is a ResourceIdentifier of type auth_v1 (an application ID).
     */
    @SerialName("active_streamer")
    val activeStreamer: ResourceReference? = null,

    /**
     * Stream proxy configuration for this entertainment group.
     */
    @SerialName("stream_proxy")
    val streamProxy: StreamProxy,

    /**
     * Channels in the entertainment configuration.
     *
     * Each channel groups segments of one or different lights.
     */
    val channels: List<EntertainmentChannel>,

    /**
     * Entertainment service locations for lights in the zone.
     */
    val locations: EntertainmentLocations,
)
