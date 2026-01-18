package inkapplications.shade.button.structures

import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference
import inkapplications.shade.structures.ResourceType
import kotlinx.serialization.Serializable

/**
 * State and capabilities of a button resource.
 */
@Serializable
data class Button(
    /**
     * Unique identifier representing a specific button resource instance.
     */
    val id: ResourceId,

    /**
     * Owner of the service.
     *
     * In case the owner service is deleted, the service also gets deleted.
     */
    val owner: ResourceReference,

    /**
     * Metadata describing this button resource.
     */
    val metadata: ButtonMetadata,

    /**
     * Button state and configuration.
     */
    val state: ButtonState,

    /**
     * Type of the supported resources.
     */
    val type: ResourceType = ResourceType.Button,
)
