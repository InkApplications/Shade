package inkapplications.shade.cli.connection

import inkapplications.shade.cli.ShadeCommand

object DiscoverCommand: ShadeCommand(
    help = "Discover hue bridges on the network",
) {
    override suspend fun runCommand(): Int {
        val devices = shade.onlineDiscovery.getDevices()

        devices.forEach {
            echo("${it.id}:")
            echo("    ip: ${it.localIp}")
            echo("    port: ${it.port}")
        }

        return 0
    }
}
