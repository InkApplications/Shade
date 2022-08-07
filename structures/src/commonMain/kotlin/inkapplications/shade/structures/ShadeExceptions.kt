package inkapplications.shade.structures

/**
 * Base/General class for all exceptions specific to shade.
 */
open class ShadeException internal constructor(message: String? = null, cause: Throwable? = null): RuntimeException(message, cause)

/**
 * Exception thrown if something within the Shade SDK has unexpectedly failed.
 */
open class InternalErrorException internal constructor(message: String, cause: Throwable? = null): ShadeException(message, cause)

/**
 * Exception thrown if an error occurs making API requests to the Hue API.
 */
class NetworkException(message: String, cause: Throwable): ShadeException(message, cause)

/**
 * Exception thrown if an unexpected internal SDK state was reached.
 */
class UnexpectedStateException(message: String, cause: Throwable? = null): InternalErrorException(message, cause)

/**
 * Exception thrown if the SDK was unable to properly handle a response from
 * the Hue API.
 */
class SerializationError(message: String, cause: Throwable?): InternalErrorException(message, cause)

/**
 * Exception thrown if a status error is received from the Hue API
 */
open class ApiStatusError(
    val code: Int,
    message: String = "API Responded with code $code",
): ShadeException(
    message = message
)

/**
 * Exception thrown if an error is received from the Hue API
 */
class ApiError(code: Int, errors: List<String>): ApiStatusError(
    code = code,
    message = "($code): API Responded with errors: [${errors.joinToString()}]"
)

/**
 * Thrown for errors that are caused by the user of the Shade SDK, typically
 * a configuration error.
 */
open class UserError internal constructor(message: String, cause: Throwable? = null): ShadeException(message, cause)

/**
 * Thrown to indicate that the SDK was used before setting a hostname for the
 * bridge.
 */
object HostnameNotSetException: UserError(
    message = "No hostname was set."
)

/**
 * Exception thrown when an application is unauthorized to perform the action.
 *
 * Note: Hue incorrectly uses a 403 error for unauthorized applications, so it
 * is not possible to tell whether an application is unauthorized or forbidden
 * from performing the action
 */
class UnauthorizedException(cause: Throwable? = null): ShadeException(
    message = "Authorization error. Is your application key unset or expired?",
    cause = cause
)
