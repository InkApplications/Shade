package inkapplications.shade.auth

/**
 * Long term storage of Hue's bearer tokens.
 *
 * The implementation of this should be reasonably secure.
 * You probably don't want these floating around.
 */
interface TokenStorage {
    /**
     * Write a new token to storage.
     *
     * @param token API token to use on all future requests, or null to erase.
     */
    suspend fun setToken(token: String?)

    /**
     * Get token to be used for requests
     *
     * @return API token to use on all future requests, if available.
     */
    suspend fun getToken(): String?
}
