package inkapplications.shade.schedules

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import inkapplications.shade.constructs.*
import inkapplications.shade.serialization.converter.FirstInCollection
import org.threeten.bp.Instant
import retrofit2.http.*

/**
 * API Access for Hue's Schedules
 */
internal interface HueSchedulesApi {
    /**
     * Gets a list of all schedules that have been added to the bridge.
     */
    @GET("api/{token}/schedules")
    suspend fun getSchedules(@Path("token") token: String): Map<String, Schedule>

    /**
     * Allows the user to create new schedules.
     *
     * The bridge can store up to 100 schedules.
     */
    @POST("api/{token}/schedules")
    @FirstInCollection
    suspend fun createSchedule(@Path("token") token: String, @Body schedule: ScheduleCreation): IdToken

    /**
     * Allows the user to change attributes of a schedule.
     */
    @PUT("api/{token}/schedules/{schedule}")
    suspend fun updateSchedule(
        @Path("token") token: String,
        @Path("schedule") schedule: String,
        @Body modification: ScheduleModification
    ): HueResponse<HueProperties>

    /**
     * Gets all attributes for a schedule.
     */
    @GET("api/{token}/schedules/{schedule}")
    suspend fun getSchedule(@Path("token") token: String, @Path("schedule") schedule: String): Schedule

    /**
     * Deletes a schedule from the bridge.
     */
    @DELETE("api/{token}/schedules/{schedule}")
    suspend fun deleteSchedule(
        @Path("token") token: String,
        @Path("schedule") schedule: String
    ): HueResponse<String>
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
    @Json(name = "localtime") val localTime: TimePattern?,
    @Deprecated("Use LocalTime. This only exists for backwards compatibility")
    val time: TimePattern?,
    val created: Instant,
    val status: Status?,
    @Json(name = "autodelete") val autoDelete: Boolean?,
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
    @Json(name = "localtime") val localTime: TimePattern,
    val name: String? = null,
    val description: String? = null,
    val status: Status? = null,
    @Json(name = "autodelete") val autoDelete: Boolean? = null,
    val recycle: Boolean? = null
)

/**
 *Attributes for editing an existing schedule.
 *
 * @param name The name of the schedule.
 * @param description Description of the schedule.
 * @param command Request to execute when the scheduled event occurs.
 * @param localTime Time when the scheduled event will occur.
 * @param status The current execution status of the schedule
 * @param autoDelete If set to true, the schedule will be removed
 *        automatically if expired, if set to false it will be
 *        disabled. Default is true.
 */
internal data class ScheduleModification(
    val command: Command? = null,
    @Json(name = "localtime") val localTime: TimePattern? = null,
    val name: String? = null,
    val description: String? = null,
    val status: Status? = null,
    @Json(name = "autodelete") val autoDelete: Boolean? = null
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
