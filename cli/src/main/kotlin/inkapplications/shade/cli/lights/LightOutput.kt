package inkapplications.shade.cli.lights

import com.github.ajalt.clikt.core.CliktCommand
import inkapplications.shade.lights.structures.Light
import inkapplications.spondee.scalar.toWholePercentage
import inkapplications.spondee.structure.format

fun CliktCommand.echoLight(light: Light) {
    echo("${light.id.value}:")
    echo("    On: ${light.powerInfo.on}")
    echo("    Mode: ${light.mode}")
    light.colorTemperatureInfo?.run {
        val temperatureString = temperature?.toKelvin()?.format() ?: "--"
        echo("    Temperature: $temperatureString")
        echo("    Temperature Range: ${range}")
    }
    light.dimmingInfo?.run {
        echo("    Brightness: ${brightness.toWholePercentage().format()}")
    }
    light.colorInfo?.run {
        echo("    Color (rgb): ${color.toSRGB().toHex()}")
        echo("    Color (xy): [${color.toXYZ().toCIExyY().x},${color.toXYZ().toCIExyY().y}]")
    }
    light.dynamics?.run {
        echo("    Current Dynamics: ${status}")
        echo("    Available Dynamics: ${statusValues.joinToString()}")
        echo("    Dynamics Speed: ${speed.toWholePercentage().format()}${"*".takeUnless { speedValid }.orEmpty()}")
    }
}
