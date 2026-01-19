package inkapplications.shade.structures

import kotlinx.serialization.Serializable

/**
 * XYZ position coordinates.
 */
@Serializable
data class Position(
    /**
     * X coordinate of the position.
     */
    val x: Double,

    /**
     * Y coordinate of the position.
     */
    val y: Double,

    /**
     * Z coordinate of the position.
     */
    val z: Double,
)
