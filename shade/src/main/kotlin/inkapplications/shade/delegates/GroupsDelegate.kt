package inkapplications.shade.delegates

import inkapplications.shade.auth.TokenStorage
import inkapplications.shade.groups.*
import okhttp3.OkHttpClient

internal class GroupsDelegate(
    initialUrl: String?,
    private val client: OkHttpClient,
    private val storage: TokenStorage
): EndpointDelegate<ShadeGroups>(initialUrl), ShadeGroups {
    override fun createEndpoint(baseUrl: String): ShadeGroups {
        return ShadeGroupsModule().createGroups(baseUrl, client, storage)
    }

    override suspend fun getGroups(): Map<String, Group> = delegate.getGroups()
    override suspend fun createGroup(group: MutableGroupAttributes): String = delegate.createGroup(group)
    override suspend fun getGroup(id: String): Group = delegate.getGroup(id)
    override suspend fun updateGroup(id: String, attributes: MutableGroupAttributes) = delegate.updateGroup(id, attributes)
    override suspend fun setState(id: String, state: GroupStateModification) = delegate.setState(id, state)
    override suspend fun deleteGroup(id: String) = delegate.deleteGroup(id)
}
