package inkapplications.shade.internals

/**
 * Builds the base path of API requests
 */
internal object BaseUrl {
    private val API_V2 = arrayOf("clip", "v2")
    private val API_V1 = arrayOf("api")

    /**
     * Build a V1 API Request's base-url
     *
     * @param pathSegments The path segments in the request to add after the base-url
     */
    fun v1(vararg pathSegments: String): Array<String> = API_V1 + pathSegments

    /**
     * Build a V2 API Request's base-url
     *
     * @param pathSegments The path segments in the request to add after the base-url
     */
    fun v2(vararg pathSegments: String): Array<String> = API_V2 + pathSegments
}
