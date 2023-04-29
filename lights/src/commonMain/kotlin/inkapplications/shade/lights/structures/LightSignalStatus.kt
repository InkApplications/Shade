package inkapplications.shade.lights.structures

import kotlinx.datetime.Instant
import kotlinx.datetime.serializers.InstantIso8601Serializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Status of active light signal.
 */
@Serializable
data class LightSignalStatus(
    /**
     * Indicates which signal is currently active.
     */
    val signal: LightSignal,
    /**
     * Timestamp indicating when the active signal is expected to end.
     * Value is not set if there is no_signal
     */
    @SerialName("estimated_end")
    @Serializable(with = InstantIso8601Serializer::class)
    val estimatedEnd: Instant? = null,
)
