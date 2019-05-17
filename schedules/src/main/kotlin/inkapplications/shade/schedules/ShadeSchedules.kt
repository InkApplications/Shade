package inkapplications.shade.schedules

import inkapplications.shade.auth.TokenStorage
import inkapplications.shade.auth.UnauthorizedException

/**
 * Provides access to Hue's Schedules
 */
interface ShadeSchedules {
    /**
     * Get a list of all schedules that have been added to the bridge.
     */
    suspend fun getSchedules(): Map<String, Schedule>
}

/**
 * Hue API Implementation of Shade's scheduling API.
 */
internal class ApiSchedules(
    private val schedulesApi: HueSchedulesApi,
    private val storage: TokenStorage
): ShadeSchedules {
    private suspend fun getToken() = storage.getToken() ?: throw UnauthorizedException()

    override suspend fun getSchedules(): Map<String, Schedule> = schedulesApi.getSchedules(getToken()).await()
}
