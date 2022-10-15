package inkapplications.shade.cli

import com.github.ajalt.clikt.core.NoOpCliktCommand
import com.github.ajalt.clikt.core.subcommands
import inkapplications.shade.cli.connection.AuthorizeCommand
import inkapplications.shade.cli.connection.DiscoverCommand
import inkapplications.shade.cli.lights.GetLightCommand
import inkapplications.shade.cli.lights.ListLightsCommand
import inkapplications.shade.cli.lights.UpdateLightCommand
import inkapplications.shade.cli.rooms.CreateRoomCommand
import inkapplications.shade.cli.rooms.GetRoomCommand
import inkapplications.shade.cli.rooms.ListRoomsCommand
import inkapplications.shade.cli.rooms.UpdateRoomCommand
import kotlin.system.exitProcess

class Main: NoOpCliktCommand() {
    init {
        subcommands(
            DiscoverCommand,
            GetLightCommand,
            ListLightsCommand,
            UpdateLightCommand,
            AuthorizeCommand,
            GetRoomCommand,
            ListRoomsCommand,
            CreateRoomCommand,
            UpdateRoomCommand,
        )
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
