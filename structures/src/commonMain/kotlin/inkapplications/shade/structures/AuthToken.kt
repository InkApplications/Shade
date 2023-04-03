package inkapplications.shade.structures

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Wraps an API key used for authenticating with the Hue API
 */
@Serializable
data class AuthToken(
    @SerialName("username")
    val applicationKey: String,
    @SerialName("clientkey")
    val clientKey: String? = null,
) {
    override fun toString(): String {
        return "AuthToken{}"
    }
}
