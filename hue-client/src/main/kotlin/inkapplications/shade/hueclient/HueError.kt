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
 * Error codes thrown by the Hue API.
 */
object ErrorCodes {
    const val LINK_REQUIRED = 101
    const val DHCP_DISABLED = 110
    const val UPDATE_INVALID_STATE = 111
    const val LIGHT_PARAMETER_NOT_MODIFIABLE = 201
    const val LIGHT_RADIO_FULL = 203
    const val GROUP_LIST_FULL = 301
    const val GROUP_UPDATE_NOT_ALLOWED = 305
    const val GROUP_LIGHT_ALREADY_IN_ROOM = 306
    const val SCENE_LIST_FULL = 402
    const val SCENE_LOCKED = 403
    const val SCENE_GROUP_EMPTY = 404
    const val SENSOR_NOT_ALLOWED = 501
    const val SENSOR_LIST_FULL = 502
    const val SENSOR_RADIO_FULL = 503
    const val RULE_LIST_FULL = 601
    const val RULE_CONDITION_NOT_ALLOWED = 607
    const val RULE_ACTION_ERROR = 608
    const val RULE_NOT_ACTIVATED = 609
    const val SCHEDULE_FULL = 701
    const val SCHEDULE_TIMEZONE_INVALID = 702
    const val SCHEDULE_TIME_AMBIGUOUS = 703
    const val SCHEDULE_TAG_INVALID = 704
    const val SCHEDULE_TIME_PAST = 705
    const val SCHEDULE_COMMAND_ERROR = 706
    const val BACKUP_SOURCE_MODEL_INVALID = 801
    const val BACKUP_SOURCE_FACTORY_NEW = 802
    const val BACKUP_INVALID_STATE = 803
}

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
