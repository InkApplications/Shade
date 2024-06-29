package inkapplications.shade.cli.devices

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.output.TermUi
import inkapplications.shade.devices.structures.Device

/**
 * Echo a device's properties in a human readable format.
 */
fun CliktCommand.echoDevice(device: Device) {
    echo("${device.id.value}:")
    echo("    Name: ${device.metadata.name}")
    echo("    Archetype: ${device.metadata.archetype}")
    echo("    Model ID: ${device.productData.modelId}")
    echo("    Manufacturer: ${device.productData.manufacturerName}")
    echo("    Product Name: ${device.productData.productName}")
    echo("    Product Archetype: ${device.productData.productArchetype}")
    echo("    Certified: ${device.productData.certified}")
    echo("    Software Version: ${device.productData.softwareVersion}")
    device.productData.hardwarePlatformType?.run {
        echo("    Hardware Platform Type: $this")
    }
    echo("    Services:")
    device.services.forEach {
        echo("         - $it")
    }
}
