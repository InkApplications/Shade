package inkapplications.shade.scenes.structures

import inkapplications.shade.lights.structures.DimmingValue
import inkapplications.shade.serialization.MillisecondDurationSerializer
import kotlinx.serialization.Serializable
import kotlin.time.Duration

@Serializable
data class SceneRecall(
    val action: SceneRecallAction,
    val status: SceneRecallStatus,
    @Serializable(with = MillisecondDurationSerializer::class)
    val duration: Duration,
    val dimming: DimmingValue,
)
