package inkapplications.shade.auth.structures

import inkapplications.shade.serialization.DelegateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

/**
 * Identifies an app connection upon auth
 */
@Serializable(with = AppId.Serializer::class)
data class AppId(
    val appName: String,
    val instanceName: String,
) {
    internal object Serializer: DelegateSerializer<String, AppId>(String.serializer()) {
        override fun serialize(data: AppId): String = "${data.appName}#${data.instanceName}"
        override fun deserialize(data: String): AppId = data.split('#').let {
            AppId(
                appName = it[0],
                instanceName = it[1],
            )
        }
    }
}
