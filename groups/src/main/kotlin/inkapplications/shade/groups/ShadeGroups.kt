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

    /**
     * Get a single group by ID
     *
     * @param id The ID of the group to fetch
     * @return The Group data
     */
    suspend fun getGroup(id: String): Group

    /**
     * Update the attributes of a group
     *
     * @param id The ID of the group to modify.
     * @param attributes The attributes to change. If null, these will
     *        be left unchanged. The type of group is not modifiable.
     *        If a different type is provided, it will remain unchanged.
     */
    suspend fun updateGroup(id: String, attributes: MutableGroupAttributes)

    /**
     * Set the light state for an entire group.
     *
     * @param id The ID of the group of lights to set the state of
     * @param state The state to assign to all the lights in the group.
     */
    suspend fun setState(id: String, state: GroupStateModification)

    /**
     * Deletes the specified group from the bridge.
     *
     * @param id The ID of the group to be permanently deleted.
     */
    suspend fun deleteGroup(id: String)
}

/**
 * API implementation of Shade's group functionality.
 */
internal class ApiGroups(
    private val groupsApi: HueGroupsApi,
    private val storage: TokenStorage
): ShadeGroups {
    private suspend fun getToken(): String = storage.getToken() ?: throw UnauthorizedException()

    override suspend fun getGroups(): Map<String, Group> = groupsApi.getAll(getToken())

    override suspend fun createGroup(group: MutableGroupAttributes): String {
        return groupsApi.createGroup(getToken(), group).id
    }

    override suspend fun getGroup(id: String): Group = groupsApi.getGroup(getToken(), id)

    override suspend fun updateGroup(id: String, attributes: MutableGroupAttributes) {
        groupsApi.updateGroup(getToken(), id, attributes)
    }

    override suspend fun setState(id: String, state: GroupStateModification) {
        groupsApi.setState(getToken(), id, state)
    }

    override suspend fun deleteGroup(id: String) {
        groupsApi.deleteGroup(getToken(), id)
    }
}
