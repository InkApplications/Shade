package inkapplications.shade.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.core.ProgramResult
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import inkapplications.shade.core.Shade
import inkapplications.shade.structures.*
import kimchi.logger.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.runBlocking

abstract class ShadeCommand(
    private val help: String,
    protected val fileProperties: HueConfigurationContainer = PropertiesFileConfiguration(),
): CliktCommand(), HueConfigurationContainer by fileProperties {
    private val host by option(
        help = "Hostname of the Hue bridge. "
    )
    override val hostname: StateFlow<String?> by lazy {
        host?.let(::MutableStateFlow) ?: fileProperties.hostname
    }

    private val insecure by option(
        help = "Use an insecure SSL connection for requests to the Hue Bridge"
    ).flag()

    override val securityStrategy: StateFlow<SecurityStrategy> by lazy {
        if (insecure) {
            (hostname.value ?: throw InvalidConfigurationException("Hostname required for insecure connection"))
                .let(SecurityStrategy::Insecure)
                .let(::MutableStateFlow)
        } else {
            fileProperties.securityStrategy
        }
    }

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

    open val shade: Shade by lazy {
        Shade(
            configuration = this,
            logger = logger,
        )
    }

    override fun help(context: Context): String {
        return help
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
                is ShadeException -> {
                    echo("Error: ${it.message}", err = true)
                    if (verbose) it.printStackTrace()
                }
                else -> throw it
            }
            throw ProgramResult(1)
        }
    }

    abstract suspend fun runCommand(): Int
}
