package inkapplications.shade.structures

import inkapplications.spondee.structure.Dimension
import inkapplications.spondee.structure.format
import kotlin.jvm.JvmInline

/**
 * SI unit of illuminance.
 */
@JvmInline
value class Lux(override val value: Number): Illuminance, Dimension<Lux> {
    override fun toLux(): Lux = this
    override fun withValue(value: Number): Lux = Lux(value)
    override val symbol: String get() = "lx"
    override fun toString(): String = format()
}

/**
 * Express this number as an illuminance unit in Lux.
 */
val Number.lux get() = Lux(this)
