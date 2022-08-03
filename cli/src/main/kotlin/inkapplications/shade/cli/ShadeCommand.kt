package inkapplications.shade.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.ProgramResult
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import inkapplications.shade.core.Shade
import inkapplications.shade.structures.*
import kotlinx.coroutines.runBlocking

abstract class ShadeCommand(
    help: String,
): CliktCommand(
    help = help,
) {
    private val hostname by argument(
        help = "Hostname of the Hue bridge. "
    )

    private val key by argument(
        help = "Application API Key/Token for connecting to the hue bridge"
    )

    private val insecure by option(
        help = "Use an insecure SSL connection for requests to the Hue Bridge"
    ).flag()

    protected val debug by option(
        help = "Print debugging information in output"
    ).flag()

    protected val shade by lazy {
        Shade(
            hostname = hostname,
            applicationKey = ApplicationKey(key),
            securityStrategy = if (insecure) {
                SecurityStrategy.Insecure(hostname)
            } else {
                SecurityStrategy.PlatformTrust
            }
        )
    }

    final override fun run() {
        val result = runBlocking {
            runCatching {
                runCommand()
            }
        }
        result.onSuccess {
            throw ProgramResult(it)
        }
        result.onFailure {
            if (debug) it.printStackTrace()
            when (it) {
                is UnauthorizedException -> echo("API Token Invalid", err = true)
                is ShadeException -> echo("Error: ${it.message}", err = true)
                else -> throw it
            }
            throw ProgramResult(1)
        }
    }

    /**
     * Execute a block of code only if the debug flag is enabled
     */
    protected inline fun debug(action: () -> Unit) {
        if (debug) action()
    }

    abstract suspend fun runCommand(): Int
}
