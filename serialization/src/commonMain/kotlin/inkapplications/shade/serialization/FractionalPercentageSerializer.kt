package inkapplications.shade.serialization

import inkapplications.spondee.scalar.DecimalPercentage
import inkapplications.spondee.scalar.Percentage
import inkapplications.spondee.structure.value
import kotlinx.serialization.builtins.serializer

/**
 * Serialize a percentage as a fraction of of a decimal (0.0-1.0)
 */
object FractionalPercentageSerializer: DelegateSerializer<Double, Percentage>(Double.serializer()) {
    override fun serialize(data: Percentage): Double = data.value(DecimalPercentage)
    override fun deserialize(data: Double): Percentage = DecimalPercentage.of(data)
}
