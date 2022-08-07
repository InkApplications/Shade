package inkapplications.shade.cli.discover

import com.github.ajalt.clikt.core.CliktCommand
import inkapplications.shade.core.Shade
import kotlinx.coroutines.runBlocking

object DiscoverCommand: CliktCommand(
    help = "Discover hue bridges on the network"
) {
    override fun run() {
        runBlocking {
            val devices = Shade().discover.onlineDiscovery.getDevices()

            devices.forEach {
                echo("${it.id}:")
                echo("    ip: ${it.localIp}")
                echo("    port: ${it.port}")
            }
        }
    }
}
