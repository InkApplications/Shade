package inkapplications.shade.cli

import com.github.ajalt.clikt.core.NoOpCliktCommand
import com.github.ajalt.clikt.core.subcommands
import inkapplications.shade.cli.connection.AuthorizeCommand
import inkapplications.shade.cli.connection.DiscoverCommand
import inkapplications.shade.cli.devices.*
import inkapplications.shade.cli.events.EventsCommand
import inkapplications.shade.cli.groupedlights.GetGroupedLightCommand
import inkapplications.shade.cli.groupedlights.ListGroupedLightsCommand
import inkapplications.shade.cli.groupedlights.UpdateGroupedLightCommand
import inkapplications.shade.cli.lights.GetLightCommand
import inkapplications.shade.cli.lights.ListLightsCommand
import inkapplications.shade.cli.lights.UpdateLightCommand
import inkapplications.shade.cli.resources.ListResourcesCommand
import inkapplications.shade.cli.rooms.*
import inkapplications.shade.cli.scenes.*
import inkapplications.shade.cli.zones.*
import kotlin.system.exitProcess

class Main: NoOpCliktCommand() {
    init {
        subcommands(
            AuthorizeCommand,
            CreateRoomCommand,
            CreateSceneCommand,
            CreateZoneCommand,
            DeleteDeviceCommand,
            DeleteRoomCommand,
            DeleteSceneCommand,
            DeleteZoneCommand,
            DiscoverCommand,
            EventsCommand,
            GetDeviceCommand,
            GetGroupedLightCommand,
            GetLightCommand,
            GetRoomCommand,
            GetSceneCommand,
            GetZoneCommand,
            IdentifyDeviceCommand,
            ListDevicesCommand,
            ListGroupedLightsCommand,
            ListLightsCommand,
            ListResourcesCommand,
            ListRoomsCommand,
            ListScenesCommand,
            ListZonesCommand,
            UpdateDeviceCommand,
            UpdateGroupedLightCommand,
            UpdateLightCommand,
            UpdateRoomCommand,
            UpdateSceneCommand,
            UpdateZoneCommand,
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
