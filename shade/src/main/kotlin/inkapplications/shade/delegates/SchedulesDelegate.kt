package inkapplications.shade.delegates

import inkapplications.shade.auth.TokenStorage
import inkapplications.shade.constructs.Command
import inkapplications.shade.constructs.TimePattern
import inkapplications.shade.groups.GroupStateModification
import inkapplications.shade.lights.LightStateModification
import inkapplications.shade.schedules.Schedule
import inkapplications.shade.schedules.ShadeSchedules
import inkapplications.shade.schedules.ShadeSchedulesModule
import inkapplications.shade.schedules.Status
import okhttp3.OkHttpClient

internal class SchedulesDelegate(
    initialUrl: String?,
    private val client: OkHttpClient,
    private val storage: TokenStorage
): EndpointDelegate<ShadeSchedules>(initialUrl), ShadeSchedules {

    override fun createEndpoint(baseUrl: String): ShadeSchedules {
        return ShadeSchedulesModule().createSchedule(baseUrl, client, storage)
    }

    override suspend fun getSchedules(): Map<String, Schedule> = delegate.getSchedules()
    override suspend fun getSchedule(schedule: String): Schedule = delegate.getSchedule(schedule)
    override suspend fun deleteSchedule(schedule: String) = delegate.deleteSchedule(schedule)

    override suspend fun createGroupSchedule(
        group: String,
        modification: GroupStateModification,
        time: TimePattern,
        name: String?,
        description: String?,
        autoDelete: Boolean?,
        status: Status?,
        recycle: Boolean?
    ): String = delegate.createGroupSchedule(group, modification, time, name, description, autoDelete, status, recycle)

    override suspend fun createLightSchedule(
        light: String,
        modification: LightStateModification,
        time: TimePattern,
        name: String?,
        description: String?,
        autoDelete: Boolean?,
        status: Status?,
        recycle: Boolean?
    ): String = delegate.createLightSchedule(light, modification, time, name, description, autoDelete, status, recycle)

    override suspend fun createSchedule(
        command: Command,
        time: TimePattern,
        name: String?,
        description: String?,
        status: Status?,
        autoDelete: Boolean?,
        recycle: Boolean?
    ): String = delegate.createSchedule(command, time, name, description, status, autoDelete, recycle)

    override suspend fun updateGroupSchedule(
        schedule: String,
        group: String?,
        modification: GroupStateModification?,
        time: TimePattern?,
        name: String?,
        description: String?,
        autoDelete: Boolean?,
        status: Status?
    ) = delegate.updateGroupSchedule(schedule, group, modification, time, name, description, autoDelete, status)

    override suspend fun updateLightSchedule(
        schedule: String,
        light: String?,
        modification: LightStateModification?,
        time: TimePattern?,
        name: String?,
        description: String?,
        autoDelete: Boolean?,
        status: Status?
    ) = delegate.updateLightSchedule(schedule, light, modification, time, name, description, autoDelete, status)

    override suspend fun updateSchedule(
        schedule: String,
        command: Command?,
        time: TimePattern?,
        name: String?,
        description: String?,
        status: Status?,
        autoDelete: Boolean?
    ) = delegate.updateSchedule(schedule, command, time, name, description, status, autoDelete)
}
