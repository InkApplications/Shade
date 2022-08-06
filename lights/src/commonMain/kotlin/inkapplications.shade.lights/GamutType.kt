package inkapplications.shade.lights

import inkapplications.shade.serialization.DelegateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlin.jvm.JvmInline

/**
 * Simple Gamut Type definition used by Hue products.
 */
@Serializable(with = GamutType.Serializer::class)
@JvmInline
value class GamutType private constructor(val key: String) {
    override fun toString(): String = key

    companion object {
        /**
         * Gamut of early Philips color-only products
         */
        val A = GamutType("A")

        /**
         * Limited gamut of first Hue color products
         */
        val B = GamutType("B")

        /**
         * Richer color gamut of Hue white and color ambiance products
         */
        val C = GamutType("C")

        /**
         * Color gamut of non-hue products with non-hue gamuts resp w/o gamut
         */
        val Other = GamutType("other")

        fun values(): Array<GamutType> = arrayOf(A, B, C, Other)
        fun valueOf(key: String) = values().single { it.key == key }
    }

    internal object Serializer: DelegateSerializer<String, GamutType>(String.serializer()){
        override fun serialize(data: GamutType): String = data.key
        override fun deserialize(data: String): GamutType = GamutType(data)
    }
}
