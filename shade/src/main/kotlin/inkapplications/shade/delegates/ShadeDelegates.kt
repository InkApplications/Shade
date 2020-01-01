package inkapplications.shade.delegates

import inkapplications.shade.InMemoryStorage
import inkapplications.shade.auth.TokenStorage
import okhttp3.OkHttpClient

/**
 * Container for the internal implementations of Shade's API's.
 *
 * @param initBaseUrl The base url to use for the endpoints initially. (optional)
 * @param appId The App ID to use for authentication.
 */
internal class ShadeDelegates(
    initBaseUrl: String?,
    appId: String,
    storage: TokenStorage = InMemoryStorage,
    client: OkHttpClient = OkHttpClient()
) {
    val auth = AuthDelegate(initBaseUrl, appId, client, storage)
    val lights = LightsDelegate(initBaseUrl, client, storage)
    val groups = GroupsDelegate(initBaseUrl, client, storage)
    val schedules = SchedulesDelegate(initBaseUrl, client, storage)
    val scenes = ScenesDelegate(initBaseUrl, client, storage)

    val all = listOf(auth, lights, groups, schedules, scenes)
}
