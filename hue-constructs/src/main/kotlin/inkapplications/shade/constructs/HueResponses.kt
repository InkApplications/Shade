package inkapplications.shade.constructs

import com.squareup.moshi.JsonClass

/**
 * Responses from the Hue API.
 *
 * Responses spit out an array of either success or error objects.
 * Maybe both? Who knows!
 */
@JsonClass(generateAdapter = true)
data class HueResult<T>(
    val success: T?,
    val error: HueError?
)

/**
 * A List of results, as seems standard for Hue to respond with.
 */
typealias HueResponse<T> = @JvmSuppressWildcards List<HueResult<T>>

/**
 * Throw an exception if the result contains an error.
 */
fun <T> HueResult<T>.throwOnFailure() {
    if (error != null) throw ShadeApiError(error)
}

/**
 * Get the result if it does not contain an error.
 *
 * This will throw if there is no result available.
 */
fun <T> HueResult<T>.getOrThrow(): T {
    throwOnFailure()
    return success ?: throw ShadeException("Response contained no success or error.")
}

/**
 * Throw an exception if there are *any* errors in the response.
 */
fun <T: Any> HueResponse<T>.throwOnFailure() {
    val errors = mapNotNull { it.error }
    if (errors.isEmpty()) return

    throw errors.asException()
}

/**
 * Get the list of results if there were no errors included in the response.
 */
fun <T: Any> HueResponse<T>.getOrThrow(): List<T> {
    throwOnFailure()
    return mapNotNull { it.success }
}

/**
 * A Key/Value map of property paths to their value.
 *
 * This is used a lot in response bodies from the Hue API, but isn't very
 * useful since it's polymorphic and tightly coupled to the request path.
 */
typealias HueProperties = Map<String, Any?>
