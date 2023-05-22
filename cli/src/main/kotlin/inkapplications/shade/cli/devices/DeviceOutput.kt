package inkapplications.shade.cli.devices

import com.github.ajalt.clikt.output.TermUi
import inkapplications.shade.devices.structures.Device

/**
 * Echo a device's properties in a human readable format.
 */
fun echoDevice(device: Device) {
    TermUi.echo("${device.id.value}:")
    TermUi.echo("    Name: ${device.metadata.name}")
    TermUi.echo("    Archetype: ${device.metadata.archetype}")
    TermUi.echo("    Model ID: ${device.productData.modelId}")
    TermUi.echo("    Manufacturer: ${device.productData.manufacturerName}")
    TermUi.echo("    Product Name: ${device.productData.productName}")
    TermUi.echo("    Product Archetype: ${device.productData.productArchetype}")
    TermUi.echo("    Certified: ${device.productData.certified}")
    TermUi.echo("    Software Version: ${device.productData.softwareVersion}")
    device.productData.hardwarePlatformType?.run {
        TermUi.echo("    Hardware Platform Type: $this")
    }
    TermUi.echo("    Services:")
    device.services.forEach {
        TermUi.echo("         - $it")
    }
}
