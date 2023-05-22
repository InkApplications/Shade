package inkapplications.shade.scenes.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * When writing active, the actions in the scene are executed on the target.
 */
@JvmInline
@Serializable
value class SceneRecallAction(val key: String) {
    override fun toString(): String = key

    companion object {
        val Active = SceneRecallAction("active")

        /**
         * Starts dynamic scene with colors in the Palette object.
         */
        val DynamicPalette = SceneRecallAction("dynamic_palette")
        val Static = SceneRecallAction("static")
    }
}
