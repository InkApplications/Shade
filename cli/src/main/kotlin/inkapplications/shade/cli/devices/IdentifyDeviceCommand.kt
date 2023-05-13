package inkapplications.shade.cli.devices

import com.github.ajalt.clikt.parameters.arguments.argument
import inkapplications.shade.cli.AuthorizedShadeCommand
import inkapplications.shade.cli.resourceId

object IdentifyDeviceCommand: AuthorizedShadeCommand(
    help = "Trigger a visual identification sequence on a specified device",
) {
    private val deviceId by argument().resourceId()

    override suspend fun runCommand(): Int {
        val response = shade.devices.identifyDevice(deviceId)

        logger.debug("Got response: $response")

        return 0
    }
}
