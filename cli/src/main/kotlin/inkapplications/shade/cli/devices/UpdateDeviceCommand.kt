package inkapplications.shade.cli.devices

import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.convert
import com.github.ajalt.clikt.parameters.options.option
import inkapplications.shade.cli.AuthorizedShadeCommand
import inkapplications.shade.cli.resourceId
import inkapplications.shade.devices.parameters.DeviceMetadataParameters
import inkapplications.shade.devices.parameters.UpdateDeviceParameters
import inkapplications.shade.devices.structures.ProductArchetype

object UpdateDeviceCommand: AuthorizedShadeCommand(
    help = "Update a device on the hue bridge",
) {
    private val deviceId by argument().resourceId()
    private val name by option(help = "Human readable name to assign to the device")
    private val archetype by option(help = "The product type of device to assign to the device")
        .convert { ProductArchetype(it) }

    override suspend fun runCommand(): Int {
        val response = shade.devices.updateDevice(
            deviceId = deviceId,
            parameters = UpdateDeviceParameters(
                metadata = DeviceMetadataParameters(
                    name = name,
                    archetype = archetype,
                ),
            ),
        )

        logger.debug("Got response: $response")

        return 0
    }
}
