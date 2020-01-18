package inkapplications.shade.lights

import inkapplications.shade.auth.TokenStorage
import inkapplications.shade.auth.UnauthorizedException
import inkapplications.shade.constructs.DeviceAttributes
import inkapplications.shade.constructs.Scan
import inkapplications.shade.constructs.throwOnFailure
import inkapplications.shade.serialization.encapsulateErrors

/**
 * Actions to control lights.
 */
interface ShadeLights {
    /**
     * Gets a list of all lights that have been discovered by the bridge.
     */
    suspend fun getLights(): Map<String, Light>

    /**
     * Get lights that were discovered the last time a search was performed.
     *
     * The list of new lights is always deleted when a new search is started.
     */
    suspend fun getNewLights(): Scan

    /**
     * Allows the user to turn the light on and off, modify the hue and effects.
     *
     * @param id The ID (not the uuid) of the light to modify.
     * @param state Arguments to change about the light.
     */
    suspend fun setState(id: String, state: LightStateModification)

    /**
     * Starts searching for new lights.
     *
     * This will search for devices for 40s. After which, devices that
     * were found can be retrieved through the `getNewLights` method.
     * The function will not suspend for the full 40s of searching.
     *
     * @see #getNewLights
     * @param deviceIds Serial numbers of devices to search for. (optional)
     */
    suspend fun search(vararg deviceIds: String)

    /**
     * Get the state of a specific light.
     *
     * @param id The local ID of the light to get the state of.
     * @return The current state of the light
     */
    suspend fun getLight(id: String): Light

    /**
     * Rename a light
     *
     * @param id The local ID of the light to be renamed.
     * @param name The user-readable name to assign to the light.
     */
    suspend fun rename(id: String, name: String)

    /**
     * Delete a light.
     *
     * @param id The local ID of the light to be deleted
     */
    suspend fun delete(id: String)
}

/**
 * API implementation of Shade's lights functionality.
 */
internal class ApiLights(
    private val lightsApi: HueLightsApi,
    private val storage: TokenStorage
): ShadeLights {
    private suspend fun getToken() = storage.getToken() ?: throw UnauthorizedException()

    override suspend fun getLights(): Map<String, Light> = encapsulateErrors {
        lightsApi.getLights(getToken())
    }

    override suspend fun getNewLights(): Scan = encapsulateErrors {
        lightsApi.getNewLights(getToken())
    }

    override suspend fun setState(id: String, state: LightStateModification) = encapsulateErrors {
        lightsApi.setState(getToken(), id, state).throwOnFailure()
    }

    override suspend fun getLight(id: String): Light = encapsulateErrors {
        lightsApi.getLightAttributes(getToken(), id)
    }

    override suspend fun search(vararg deviceIds: String) = encapsulateErrors {
        if (deviceIds.isEmpty()) {
            lightsApi.searchLights(getToken()).throwOnFailure()
        } else {
            val criteria = LightSearchCriteria(deviceIds.toList())
            lightsApi.searchLights(getToken(), criteria).throwOnFailure()
        }
    }

    override suspend fun rename(id: String, name: String) = encapsulateErrors {
        val attributes = DeviceAttributes(name = name)

        lightsApi.setLightAttributes(getToken(), id, attributes).throwOnFailure()
    }

    override suspend fun delete(id: String) = encapsulateErrors {
        lightsApi.delete(getToken(), id).throwOnFailure()
    }
}
