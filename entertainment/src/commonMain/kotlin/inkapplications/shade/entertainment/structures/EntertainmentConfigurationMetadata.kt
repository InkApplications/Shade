package inkapplications.shade.entertainment.structures

import kotlinx.serialization.Serializable

/**
 * Metadata for an entertainment configuration.
 */
@Serializable
data class EntertainmentConfigurationMetadata(
    /**
     * Friendly name of the entertainment configuration.
     */
    val name: String,
)
