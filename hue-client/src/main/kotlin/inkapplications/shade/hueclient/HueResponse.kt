package inkapplications.shade.hueclient

import com.squareup.moshi.JsonClass

/**
 * Responses from the Hue API.
 *
 * Responses spit out an array of either success or error objects.
 * Maybe both? Who knows!
 */
@JsonClass(generateAdapter = true)
internal data class HueResponse<T>(
    val success: T?,
    val error: HueError?
)
