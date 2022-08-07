package inkapplications.shade.serialization

import inkapplications.shade.serialization.DelegateSerializer
import kotlinx.serialization.builtins.serializer
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

/**
 * Serialize a Duration as a Long in milliseconds.
 */
object MillisecondDurationSerializer: DelegateSerializer<Long, Duration>(Long.serializer()) {
    override fun serialize(data: Duration): Long = data.inWholeMilliseconds
    override fun deserialize(data: Long): Duration = data.milliseconds
}
