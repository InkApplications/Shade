package inkapplications.shade.groups

import inkapplications.shade.auth.TokenStorage
import inkapplications.shade.auth.UnauthorizedException

/**
 * Access for Hue's various Groups.
 */
interface ShadeGroups {
    /**
     * Gets a list of all groups that have been added to the bridge.
     */
    suspend fun getGroups(): Map<String, Group>
}

/**
 * API implementation of Shade's group functionality.
 */
internal class ApiGroups(
    private val groupsApi: HueGroupsApi,
    private val storage: TokenStorage
): ShadeGroups {
    private suspend fun getToken(): String = storage.getToken() ?: throw UnauthorizedException()

    override suspend fun getGroups(): Map<String, Group> = groupsApi.getAll(getToken()).await()
}
