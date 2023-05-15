package inkapplications.shade.scenes.structures

import inkapplications.shade.structures.ResourceReference
import kotlinx.serialization.Serializable

/**
 * User-configured metadata for the scene.
 */
@Serializable
data class SceneMetadata(
    /**
     * Human readable name of a resource
     */
    val name: String,

    /**
     * Reference with unique identifier for the image representing the scene only accepting “rtype”: “public_image” on creation
     */
    val image: ResourceReference? = null,
)
