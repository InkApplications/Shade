package inkapplications.shade.serialization

import inkapplications.spondee.measure.Mireds
import inkapplications.spondee.measure.mireds
import inkapplications.spondee.structure.convert
import kotlinx.serialization.builtins.serializer
import kotlin.math.roundToInt

/**
 * Convert a mired color temperature to/from an int value
 *
 * This is used for color temperature delta values that require the unit in
 * mireds. Since mireds and kelvin do not have a linear relationship, a delta
 * value cannot be translated between the two units. So this converter
 * takes and produces an explicit mired unit only.
 */
object ExplicitMiredSerializer: DelegateSerializer<Int, Mireds>(Int.serializer()) {
    override fun serialize(data: Mireds): Int = data.convert { roundToInt() }
    override fun deserialize(data: Int): Mireds = data.mireds
}
