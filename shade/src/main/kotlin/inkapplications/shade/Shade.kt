package inkapplications.shade

import inkapplications.shade.auth.ShadeAuth
import inkapplications.shade.auth.TokenStorage
import inkapplications.shade.delegates.ShadeDelegates
import inkapplications.shade.discover.DiscoverModule
import inkapplications.shade.groups.*
import inkapplications.shade.lights.ShadeLights
import inkapplications.shade.scenes.ShadeScenes
import inkapplications.shade.schedules.ShadeSchedules
import okhttp3.OkHttpClient

/**
 * Provides services for accessing the Hue API
 *
 * @param storage A service for storing Hue's API token. By default,
 *        this token is only stored in memory during the application run.
 *        However, this parameter can be provided to store the token
 *        on disk permanently.
 * @param client The HTTP Client to use for requests. This can
 *        optionally be provided in order to modify how the HTTP
 *        requests are made. Useful for logging and debugging.
 */
open class Shade(
    appId: String = "Shade#Shade",
    initBaseUrl: String? = null,
    storage: TokenStorage = InMemoryStorage,
    client: OkHttpClient = OkHttpClient()
) {
    private val delegates = ShadeDelegates(initBaseUrl, appId, storage, client)

    /**
     * Services for finding bridges on the network.
     */
    open val discovery = DiscoverModule().createDiscoverclient(client)

    /**
     * Services for Authenticating with Hue.
     */
    open val auth: ShadeAuth = delegates.auth

    /**
     * Services for controlling Lights.
     */
    open val lights: ShadeLights = delegates.lights

    /**
     * Services for controlling Light-Groups
     */
    open val groups: ShadeGroups = delegates.groups

    /**
     * Services for setting custom light schedules.
     */
    open val schedules: ShadeSchedules = delegates.schedules

    /**
     * Services for bridge scenes.
     */
    val scenes: ShadeScenes = delegates.scenes

    /**
     * Set the BaseURL of the Hue bridge.
     */
    fun setBaseUrl(baseUrl: String) {
        delegates.all.forEach { it.setUrl(baseUrl) }
    }
}
