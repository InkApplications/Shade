package inkapplications.shade.scenes.structures

import inkapplications.shade.structures.ResourceReference
import kotlinx.serialization.Serializable

/**
 * Action to be applied to a specified resource for a scene.
 */
@Serializable
data class SceneActionReference(
    /**
     * The resource to apply the action to.
     */
    val target: ResourceReference,

    /**
     * The action to be applied to the resource.
     */
    val action: SceneAction,
)
