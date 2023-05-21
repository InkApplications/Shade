package inkapplications.shade.scenes.parameters

import inkapplications.shade.scenes.structures.SceneActionReference
import inkapplications.shade.scenes.structures.SceneMetadata
import inkapplications.shade.scenes.structures.ScenePalette
import inkapplications.shade.scenes.structures.SceneRecall
import inkapplications.shade.serialization.FractionalPercentageSerializer
import inkapplications.spondee.scalar.Percentage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SceneUpdateParameters(
    /**
     * Actions to be applied to each device in the scene's group.
     */
    val actions: List<SceneActionReference>? = null,

    val recall: SceneRecall? = null,

    /**
     * User-supplied configuration info for the scene.
     */
    val metadata: SceneMetadata? = null,

    /**
     * Group of colors that describe the palette of colors used to be used
     * when playing dynamics.
     */
    val palette: ScenePalette? = null,

    /**
     * The speed of the dynamic palette.
     */
    @Serializable(with = FractionalPercentageSerializer::class)
    val speed: Percentage? = null,

    /**
     * Indicates whether to automatically start the scene dynamically on
     * active recall.
     */
    @SerialName("auto_dynamic")
    val autoDynamic: Boolean? = null,
)
