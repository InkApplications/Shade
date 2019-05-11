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

    /**
     * Create a new Group
     *
     * @param group The attributes of the new group to create.
     * @return The ID of the newly created group.
     */
    suspend fun createGroup(group: MutableGroupAttributes): String
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

    override suspend fun createGroup(group: MutableGroupAttributes): String {
        return groupsApi.createGroup(getToken(), group).await().id
    }
}
