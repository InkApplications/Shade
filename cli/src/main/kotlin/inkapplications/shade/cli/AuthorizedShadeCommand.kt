package inkapplications.shade.cli

import com.github.ajalt.clikt.parameters.options.option
import inkapplications.shade.structures.AuthToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class AuthorizedShadeCommand(
    help: String,
): ShadeCommand(help) {
    private val key by option(
        help = "Application API Key/Token for connecting to the hue bridge"
    )

    override val authToken: StateFlow<AuthToken?> by lazy {
        key?.let { AuthToken(applicationKey = it) }?.let(::MutableStateFlow)
            ?: fileProperties.authToken
    }
}
