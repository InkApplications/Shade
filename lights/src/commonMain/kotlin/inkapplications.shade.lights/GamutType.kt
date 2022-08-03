package inkapplications.shade.lights

import inkapplications.shade.serialization.DelegateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

/**
 * Simple Gamut Type definition used by Hue products.
 */
@Serializable(with = GamutType.Serializer::class)
open class GamutType private constructor(val key: String) {
    /**
     * Gamut of early Philips color-only products
     */
    object A: GamutType("A")

    /**
     * Limited gamut of first Hue color products
     */
    object B: GamutType("B")

    /**
     * Richer color gamut of Hue white and color ambiance products
     */
    object C: GamutType("C")

    /**
     * Color gamut of non-hue products with non-hue gamuts resp w/o gamut
     */
    object Other: GamutType("other")

    override fun equals(other: Any?): Boolean = key == (other as? GamutType)?.key
    override fun hashCode(): Int = key.hashCode()
    override fun toString(): String = key

    companion object {
        fun values(): Array<GamutType> = arrayOf(A, B, C, Other)
        fun valueOf(key: String) = values().single { it.key == key }
    }

    internal object Serializer: DelegateSerializer<String, GamutType>(String.serializer()){
        override fun serialize(data: GamutType): String = data.key
        override fun deserialize(data: String): GamutType = GamutType(data)
    }
}
