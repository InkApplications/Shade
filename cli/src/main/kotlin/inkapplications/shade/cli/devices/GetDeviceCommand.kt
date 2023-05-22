package inkapplications.shade.cli.devices

import com.github.ajalt.clikt.parameters.arguments.argument
import inkapplications.shade.cli.AuthorizedShadeCommand
import inkapplications.shade.cli.resourceId

object GetDeviceCommand: AuthorizedShadeCommand(
    help = "Get a specific device by ID",
) {
    private val deviceId by argument().resourceId()

    override suspend fun runCommand(): Int {
        val device = shade.devices.getDevice(deviceId)

        logger.debug("Got Device: $device")
        echoDevice(device)

        return 0
    }
}
