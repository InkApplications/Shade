package inkapplications.shade.constructs

import com.squareup.moshi.JsonClass

/**
 * A Request to run to the Hue API.
 *
 * These are used for schedules, but are created privately throughout
 * the Shade modules.
 *
 * @param address The URL to hit, relative to the baseUrl of the Hue API
 * @param method HTTP method to use when sending the request
 * @param body Body to post with the hue request.
 */
@JsonClass(generateAdapter = true)
data class Command(
    val address: String,
    val method: String,
    val body: Any?
)
