package inkapplications.shade.config

/**
 * Settings for the Shade Hue SDK
 *
 * @property baseUrl The HTTP Url of the Hue bridge to hit.
 *           e.g. `http://192.168.0.2/`. This needs to end with a /
 * @property appId A Unique Identifier for the app used during auth.
 */
data class ShadeConfig(
    val baseUrl: String,
    val appId: String
)
