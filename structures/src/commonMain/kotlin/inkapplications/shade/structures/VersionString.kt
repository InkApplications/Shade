package inkapplications.shade.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Software major/minor/patch version string.
 */
@JvmInline
@Serializable
value class VersionString(val full: String) {
    private val pattern get() = Regex("^(\\d+)\\.(\\d+)\\.(\\d+)$")
    private val groups get() = pattern.matchEntire(full)?.groups
    val major get() = groups?.get(1)
    val minor get() = groups?.get(2)
    val patch get() = groups?.get(3)

    init {
        if (!pattern.matches(full)) {
            throw IllegalArgumentException("Version string does not match expected pattern. Got: $full")
        }
    }

    override fun toString(): String = full
}
