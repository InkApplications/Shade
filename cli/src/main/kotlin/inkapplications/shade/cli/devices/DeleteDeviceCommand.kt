package inkapplications.shade.cli.devices

import com.github.ajalt.clikt.parameters.arguments.argument
import inkapplications.shade.cli.AuthorizedShadeCommand
import inkapplications.shade.cli.resourceId

object DeleteDeviceCommand: AuthorizedShadeCommand(
    help = "Delete a device from the hue bridge",
) {
    private val deviceId by argument().resourceId()

    override suspend fun runCommand(): Int {
        val response = shade.devices.deleteDevice(deviceId)

        logger.debug("Got response: $response")

        return 0
    }
}
