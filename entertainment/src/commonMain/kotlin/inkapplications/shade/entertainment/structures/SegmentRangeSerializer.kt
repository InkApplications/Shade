package inkapplications.shade.entertainment.structures

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Serialize Hue's segment object to/from an int range.
 */
internal object SegmentRangeSerializer: KSerializer<IntRange> {
    override val descriptor: SerialDescriptor = SegmentSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: IntRange) {
        val surrogate = SegmentSurrogate(
            start = value.first,
            length = value.count()
        )
        SegmentSurrogate.serializer().serialize(encoder, surrogate)
    }

    override fun deserialize(decoder: Decoder): IntRange {
        val surrogate = SegmentSurrogate.serializer().deserialize(decoder)
        return surrogate.toIntRange()
    }

    @Serializable
    @SerialName("Segment")
    private data class SegmentSurrogate(
        val start: Int,
        val length: Int,
    ) {
        fun toIntRange(): IntRange = start until (start + length)
    }
}
