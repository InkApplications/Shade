package inkapplications.shade.cli.devices

import inkapplications.shade.cli.AuthorizedShadeCommand

object ListDevicesCommand: AuthorizedShadeCommand(
    help = "Get all of the devices configured on the hue bridge",
) {
    override suspend fun runCommand(): Int {
        val devices = shade.devices.listDevices()

        logger.debug("Got Devices: $devices")
        devices.forEach(::echoDevice)

        return 0
    }
}
