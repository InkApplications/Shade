package inkapplications.shade.cli.connection

import com.github.ajalt.clikt.parameters.options.convert
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import inkapplications.shade.auth.structures.AppId
import inkapplications.shade.cli.ShadeCommand
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

object AuthorizeCommand: ShadeCommand(
    help = "Retrieve an access token from the hue bridge",
) {
    private val appName by option(
        help = "Override the default app ID's Name. (default: ShadeCli)",
    ).default("ShadeCli")

    private val instanceId by option(
        help = "Override the default app ID's Instance. (default: default)",
    ).default("default")

    private val retries by option(
        help = "Set the limit of retries before failing. (default: 50)",
    ).int().default(50)

    private val timeout by option(
        help = "Amount of time to wait between retries, in milliseconds. (default: 1000)",
    ).int().convert { it.milliseconds }.default(1.seconds)

    @OptIn(ExperimentalTime::class)
    override suspend fun runCommand(): Int {

        echo("Press button on hue bridge to complete")
        val token = shade.auth.bridgeAuth.awaitToken(
            appId = AppId(appName, instanceId),
            retries = retries,
            timeout = timeout,
        )
        echo("Application Key: ${token.applicationKey}")
        echo("Client Key: ${token.clientKey}")

        return 0
    }
}
