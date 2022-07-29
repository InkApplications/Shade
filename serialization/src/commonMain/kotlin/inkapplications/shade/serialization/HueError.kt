package inkapplications.shade.serialization

import kotlinx.serialization.Serializable

/**
 * Error from the hue API
 */
@Serializable
data class HueError(
    val description: String,
)
