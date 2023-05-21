package inkapplications.shade.scenes.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * When writing active, the actions in the scene are executed on the target.
 */
@JvmInline
@Serializable
value class SceneRecallStatus(val key: String) {
    override fun toString(): String = key

    companion object {
        val Active = SceneRecallStatus("active")

        /**
         * Starts dynamic scene with colors in the Palette object.
         */
        val DynamicPalette = SceneRecallStatus("dynamic_palette")
    }
}
