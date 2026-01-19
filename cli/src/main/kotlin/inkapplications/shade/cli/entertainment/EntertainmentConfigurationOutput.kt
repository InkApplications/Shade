package inkapplications.shade.cli.entertainment

import com.github.ajalt.clikt.core.CliktCommand
import inkapplications.shade.entertainment.structures.EntertainmentConfiguration

fun CliktCommand.echoEntertainmentConfiguration(config: EntertainmentConfiguration) {
    echo("${config.id.value}:")
    echo("    Name: ${config.metadata.name}")
    echo("    Configuration Type: ${config.configurationType}")
    echo("    Status: ${config.status}")
    val activeStreamer = config.activeStreamer
    if (activeStreamer != null) {
        echo("    Active Streamer: $activeStreamer")
    }
    echo("    Stream Proxy Mode: ${config.streamProxy.mode}")
    echo("    Stream Proxy Node: ${config.streamProxy.node}")
    echo("    Channels:")
    config.channels.forEach { channel ->
        echo("        ${channel.channelId}:")
        echo("            Position: (${channel.position.x}, ${channel.position.y}, ${channel.position.z})")
        echo("            Members:")
        channel.members.forEach { member ->
            echo("                - ${member.service} [${member.index}]")
        }
    }
    echo("    Locations:")
    config.locations.serviceLocations.forEach { location ->
        echo("        ${location.service}:")
        echo("            Positions: ${location.positions.joinToString { "(${it.x}, ${it.y}, ${it.z})" }}")
        echo("            Equalization Factor: ${location.equalizationFactor}")
    }
}
