package inkapplications.shade.cli

import com.github.ajalt.clikt.core.NoRunCliktCommand
import com.github.ajalt.clikt.core.subcommands
import kotlin.system.exitProcess

class Main: NoRunCliktCommand() {
    init {
        subcommands(DaggerCliComponent.create().getCommands())
    }
}

fun main(args: Array<String>) {
    try {
        Main().main(args)
        exitProcess(0)
    } catch (error: Throwable) {
        println("Unknown Error: ${error.message}")
        error.printStackTrace()
        exitProcess(1)
    }
}
