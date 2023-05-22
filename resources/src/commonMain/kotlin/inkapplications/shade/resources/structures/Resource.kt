package inkapplications.shade.resources.structures

import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Hue API resource.
 */
@Serializable
data class Resource(
    /**
     * Unique ID for the resource.
     */
    val id: ResourceId,

    /**
     * Resource object type.
     */
    val type: ResourceType? = null,

    @Deprecated("V1 Resource. Left for migration purposes only, may be removed at any point by API or SDK.")
    @SerialName("id_v1")
    val v1Id: String? = null,
)
