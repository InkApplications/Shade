package inkapplications.shade.serialization

import inkapplications.spondee.measure.ColorTemperature
import inkapplications.spondee.measure.mireds
import inkapplications.spondee.measure.toMireds
import inkapplications.spondee.structure.convert
import kotlinx.serialization.builtins.serializer
import kotlin.math.roundToInt

/**
 * Convert standard ColorTemperatures to/from Mireds
 *
 * Hue's API calls this a 'mirek' in an effort to make the most obscure
 * term for this unit, but it seems to be identical to a Mired.
 * Now you don't have to deal with it.
 */
object MiredSerializer: DelegateSerializer<Int, ColorTemperature>(Int.serializer()) {
    override fun serialize(data: ColorTemperature): Int {
        return data.toMireds().convert { roundToInt() }
    }

    override fun deserialize(data: Int): ColorTemperature {
        return data.mireds
    }
}
