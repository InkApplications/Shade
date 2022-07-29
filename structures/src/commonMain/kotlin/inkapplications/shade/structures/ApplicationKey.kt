package inkapplications.shade.structures

import kotlin.jvm.JvmInline

/**
 * Wraps an API key used for authenticating with the Hue API
 */
@JvmInline
value class ApplicationKey(val key: String) {
    override fun toString(): String {
        return "ApplicationKey{}"
    }
}
