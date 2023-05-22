package inkapplications.shade.scenes.structures

import inkapplications.shade.serialization.FractionalPercentageSerializer
import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference
import inkapplications.spondee.scalar.Percentage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Scene data, used to store and recall settings for a group of lights.
 */
@Serializable
data class Scene(
    /**
     * Unique identifier representing a specific resource instance
     */
    val id: ResourceId,

    /**
     * Clip v1 resource identifier.
     */
    @Deprecated("V1 Resource. Left for migration purposes only, may be removed at any point by API or SDK.")
    @SerialName("id_v1")
    val v1Id: String? = null,

    /**
     * List of actions to be executed synchronously on recal.
     */
    val actions: List<SceneActionReference>,

    /**
     * User-configured metadata for the scene.
     */
    val metadata: SceneMetadata,

    /**
     *  Group associated with this Scene.
     *
     *  All services in the group are part of this scene. If the group is
     *  changed the scene is update (e.g. light added/removed)
     */
    val group: ResourceReference,

    /**
     * Speed of dynamic palette for this scene.
     */
    @Serializable(with = FractionalPercentageSerializer::class)
    val speed: Percentage,

    /**
     * Speed of dynamic palette for this scene.
     */
    @SerialName("auto_dynamic")
    val autoDynamic: Boolean,

    /**
     * Group of colors that describe the palette of colors to be used when
     * playing dynamics.
     */
    val palette: ScenePalette? = null,
)
