package inkapplications.shade.entertainment.structures

import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference
import inkapplications.shade.structures.ResourceType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * State and capabilities of an entertainment resource.
 *
 * Entertainment resources represent the entertainment streaming capabilities
 * of a light or device in the Hue system.
 */
@Serializable
data class Entertainment(
    /**
     * Unique identifier representing a specific entertainment resource instance.
     */
    val id: ResourceId,

    /**
     * Owner of the service.
     *
     * In case the owner service is deleted, the service also gets deleted.
     */
    val owner: ResourceReference,

    /**
     * Indicates if a lamp can be used for entertainment streaming as a renderer.
     */
    val renderer: Boolean,

    /**
     * Indicates which light service is linked to this entertainment service.
     */
    @SerialName("renderer_reference")
    val rendererReference: ResourceReference? = null,

    /**
     * Indicates if a lamp can be used for entertainment streaming as a proxy node.
     */
    val proxy: Boolean,

    /**
     * Indicates if a lamp can handle the equalization factor to dimming
     * maximum brightness in a stream.
     */
    val equalizer: Boolean,

    /**
     * Indicates the maximum number of parallel streaming sessions the bridge supports.
     */
    @SerialName("max_streams")
    val maxStreams: Int? = null,

    /**
     * Holds all parameters concerning the segmentation capabilities of a device.
     */
    val segments: Segments? = null,
)
