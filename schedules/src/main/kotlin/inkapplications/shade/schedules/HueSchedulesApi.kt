package inkapplications.shade.schedules

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.coroutines.Deferred
import org.threeten.bp.Instant
import org.threeten.bp.LocalTime
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * API Access for Hue's
 */
internal interface HueSchedulesApi {
    @GET("api/{token}/schedules")
    fun getSchedules(@Path("token") token: String): Deferred<Map<String, Schedule>>
}

/**
 * Attributes of a scheduled operation.
 *
 * @param name The name of the schedule.
 * @param description Description of the schedule.
 * @param command Request to execute when the scheduled event occurs.
 * @param localTime Time when the scheduled event will occur.
 * @param created Timestamp when the schedule was created
 * @param status The current execution status of the schedule
 * @param autoDelete If set to true, the schedule will be removed
 *        automatically if expired, if set to false it will be
 *        disabled. Default is true
 * @param startTime Timestamp that the timer was started. Only provided
 *        for timers.
 */
@JsonClass(generateAdapter = true)
data class Schedule(
    val name: String,
    val description: String,
    val command: Command,
    @Json(name = "localtime") val localTime: LocalTime,
    val created: Instant?,
    val status: Status?,
    @Json(name = "autodelete") val autoDelete: Boolean?,
    @Json(name = "starttime") val startTime: Instant?
)

/**
 * Status states for a schedule.
 *
 * Application is only allowed to set “enabled” or “disabled”.
 * Disabled causes a timer to reset when activated (i.e. stop & reset).
 */
enum class Status {
    @Json(name = "enabled") ENABLED,
    @Json(name = "disabled") DISABLED
}

/**
 * A Request to run to the Hue API when executing a schedule.
 *
 * Apparently this will just run anything. Seems like potential for a
 * hub security vulnerability here via privilege escalation. Have fun!
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
