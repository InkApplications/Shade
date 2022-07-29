package inkapplications.shade.structures

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A reference to another object in the API
 */
@Serializable
data class ResourceReference(
    /**
     * The unique id of the referenced resource
     */
    @SerialName("rid")
    val id: ResourceId,

    /**
     * The type of the referenced resource
     */
    @SerialName("rtype")
    val type: ResourceType,
)
