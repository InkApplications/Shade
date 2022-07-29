package inkapplications.shade.serialization

import inkapplications.spondee.scalar.Percentage
import inkapplications.spondee.scalar.WholePercentage
import inkapplications.spondee.structure.value
import kotlinx.serialization.builtins.serializer

/**
 * Serialize a percentage value as an integer.
 */
object PercentageSerializer: DelegateSerializer<Double, Percentage>(Double.serializer()) {
    override fun serialize(data: Percentage): Double = data.value(WholePercentage)
    override fun deserialize(data: Double): Percentage = WholePercentage.of(data)
}
