package inkapplications.shade.auth.structures

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request used when authenticating with the hue API
 */
@Serializable
internal class AuthRequest(
    @SerialName("devicetype")
    val appId: AppId,
    @SerialName("generateclientkey")
    val generateClientKey: Boolean,
)
