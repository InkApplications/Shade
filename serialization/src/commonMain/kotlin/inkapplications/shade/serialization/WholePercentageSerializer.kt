package inkapplications.shade.serialization

import inkapplications.spondee.scalar.Percentage
import inkapplications.spondee.scalar.percent
import inkapplications.spondee.scalar.toWholePercentage
import kotlinx.serialization.builtins.serializer

/**
 * Serialize a percentage value as an integer.
 */
object WholePercentageSerializer: DelegateSerializer<Double, Percentage>(Double.serializer()) {
    override fun serialize(data: Percentage): Double = data.toWholePercentage().value.toDouble()
    override fun deserialize(data: Double): Percentage = data.percent
}

