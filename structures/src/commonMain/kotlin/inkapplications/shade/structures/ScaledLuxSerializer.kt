package inkapplications.shade.structures

import inkapplications.spondee.structure.roundToInt
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Serializes an illuminance value in hue's scaled logarithmic lux units.
 */
object ScaledLuxSerializer: KSerializer<Illuminance> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Illuminance", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: Illuminance) {
        encoder.encodeInt(value.toScaledLux().roundToInt())
    }

    override fun deserialize(decoder: Decoder): ScaledLux {
        return decoder.decodeInt().scaledLux
    }
}
