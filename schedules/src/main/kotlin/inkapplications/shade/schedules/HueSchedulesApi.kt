package inkapplications.shade.schedules

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import inkapplications.shade.constructs.Command
import inkapplications.shade.constructs.IdToken
import inkapplications.shade.serialization.converter.FirstInCollection
import kotlinx.coroutines.Deferred
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * API Access for Hue's Schedules
 */
internal interface HueSchedulesApi {
    /**
     * Gets a list of all schedules that have been added to the bridge.
     */
    @GET("api/{token}/schedules")
    fun getSchedules(@Path("token") token: String): Deferred<Map<String, Schedule>>

    /**
     * Allows the user to create new schedules.
     *
     * The bridge can store up to 100 schedules.
     */
    @POST("api/{token}/schedules")
    @FirstInCollection
    fun createSchedule(@Path("token") token: String, @Body schedule: ScheduleCreation): Deferred<IdToken>

    /**
     * Gets all attributes for a schedule.
     */
    @GET("api/{token}/schedules/{schedule}")
    fun getSchedule(@Path("token") token: String, @Path("schedule") schedule: String): Deferred<Schedule>
}

/**
 * Attributes of a scheduled operation.
 *
 * @param name The name of the schedule.
 * @param description Description of the schedule.
 * @param command Request to execute when the scheduled event occurs.
 * @param localTime Time when the scheduled event will occur.
 * @param time Time in UTC when the scheduled event will occur.
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
    @Json(name = "localtime") val localTime: LocalDateTime?,
    @Deprecated("Use LocalTime. This only exists for backwards compatibility")
    val time: Instant?,
    val created: Instant,
    val status: Status?,
    @Json(name = "autodelete") val autoDelete: Boolean,
    @Json(name = "starttime") val startTime: Instant?
)

/**
 * Attributes for creating a schedule.
 *
 * @param name The name of the schedule.
 * @param description Description of the schedule.
 * @param command Request to execute when the scheduled event occurs.
 * @param localTime Time when the scheduled event will occur.
 * @param status The current execution status of the schedule
 * @param autoDelete If set to true, the schedule will be removed
 *        automatically if expired, if set to false it will be
 *        disabled. Default is true.
 * @param recycle When true: Resource is automatically deleted when not
 *        referenced anymore in any resource link. Only on creation of
 *        resource. “false” when omitted.
 */
@JsonClass(generateAdapter = true)
internal data class ScheduleCreation(
    val command: Command,
    @Json(name = "localtime") val localTime: LocalDateTime,
    val name: String? = null,
    val description: String? = null,
    val status: Status? = null,
    @Json(name = "autodelete") val autoDelete: Boolean? = null,
    val recycle: Boolean? = null
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
