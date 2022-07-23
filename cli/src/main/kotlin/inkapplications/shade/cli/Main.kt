package inkapplications.shade.cli

import com.github.ajalt.clikt.core.NoOpCliktCommand
import kotlin.system.exitProcess

class Main: NoOpCliktCommand() {
    init {
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
