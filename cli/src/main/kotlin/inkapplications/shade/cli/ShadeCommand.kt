package inkapplications.shade.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.ProgramResult
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import inkapplications.shade.core.Shade
import inkapplications.shade.structures.*
import kimchi.logger.*
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

    protected val verbose by option(
        help = "Print all logging information in output"
    ).flag()

    protected val debug by option(
        help = "Print debug logging information in output"
    ).flag()

    protected val info by option(
        help = "Print logging information in output"
    ).flag()

    protected val logger by lazy {
        when {
            verbose -> ConsolidatedLogger(defaultWriter)
            debug -> ConsolidatedLogger(defaultWriter.withThreshold(LogLevel.DEBUG))
            info -> ConsolidatedLogger(defaultWriter.withThreshold(LogLevel.INFO))
            else -> EmptyLogger
        }
    }

    protected val shade by lazy {
        Shade(
            hostname = hostname,
            applicationKey = ApplicationKey(key),
            securityStrategy = if (insecure) {
                SecurityStrategy.Insecure(hostname)
            } else {
                SecurityStrategy.PlatformTrust
            },
            logger = logger,
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

    abstract suspend fun runCommand(): Int
}
