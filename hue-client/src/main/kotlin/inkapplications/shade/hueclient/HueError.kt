package inkapplications.shade.hueclient

import com.squareup.moshi.JsonClass
import java.io.PrintStream
import java.io.PrintWriter
import java.lang.RuntimeException

/**
 * Errors from the Hue API.
 *
 * How wonderful that these are often sent back as 200's.
 *
 * So far they look like this:
 *
 *     {
 *       "type": 101,
 *       "address": "",
 *       "description": "link button not pressed"
 *     }
 *
 * @property type A unique code for the error
 * @property address Good lord, who knows. Maybe a reference ID?
 * @property description A user-readable description of the error.
 */
@JsonClass(generateAdapter = true)
data class HueError(
    val type: Int,
    val address: String,
    val description: String
)

/**
 * Base/General class for all exceptions specific to shade.
 */
open class ShadeException(message: String? = null, cause: Throwable? = null): RuntimeException(message, cause)

/**
 * Error returned by the Hue API.
 */
open class ShadeApiError(val hueError: HueError): ShadeException(hueError.description)

/**
 * Wrapper for multiple errors returned by the API
 */
open class ShadeCompositeApiError(val hueErrors: List<ShadeApiError>): ShadeException("Multiple Hue API Errors occurred") {
    override fun printStackTrace() {
        super.printStackTrace()
        hueErrors.forEach { printStackTrace() }
    }

    override fun printStackTrace(stream: PrintStream?) {
        super.printStackTrace(stream)
        hueErrors.forEach { printStackTrace(stream) }
    }

    override fun printStackTrace(writer: PrintWriter?) {
        super.printStackTrace(writer)
        hueErrors.forEach { printStackTrace(writer) }
    }
}

/**
 * Convert a list of API errors into an API Exception.
 */
internal fun List<HueError>.asException(): ShadeException = when {
    isEmpty() -> ShadeException("Unknown Error: Empty Error list thrown.")
    size == 1 -> ShadeApiError(get(0))
    else -> ShadeCompositeApiError(map { ShadeApiError(it) })
}
