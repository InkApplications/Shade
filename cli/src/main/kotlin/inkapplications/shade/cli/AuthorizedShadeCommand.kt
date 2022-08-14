package inkapplications.shade.cli

import com.github.ajalt.clikt.parameters.arguments.argument
import inkapplications.shade.core.Shade
import inkapplications.shade.structures.AuthToken

abstract class AuthorizedShadeCommand(
    help: String,
): ShadeCommand(help) {
    private val key by argument(
        help = "Application API Key/Token for connecting to the hue bridge"
    )

    override val shade by lazy {
        Shade(
            hostname = hostname,
            authToken = AuthToken(key, null),
            securityStrategy = securityStrategy,
            logger = logger,
        )
    }
}
