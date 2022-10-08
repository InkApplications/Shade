package inkapplications.shade.cli.lights

import com.github.ajalt.clikt.output.TermUi
import inkapplications.shade.lights.structures.Light
import inkapplications.spondee.scalar.toWholePercentage
import inkapplications.spondee.structure.format

fun echoLight(light: Light) {
    TermUi.echo("${light.id.value}:")
    TermUi.echo("    On: ${light.powerInfo.on}")
    TermUi.echo("    Mode: ${light.mode}")
    light.colorTemperatureInfo?.run {
        val temperatureString = temperature?.toKelvin()?.format() ?: "--"
        TermUi.echo("    Temperature: $temperatureString")
        TermUi.echo("    Temperature Range: ${range}")
    }
    light.dimmingInfo?.run {
        TermUi.echo("    Brightness: ${brightness.toWholePercentage().format()}")
    }
    light.colorInfo?.run {
        TermUi.echo("    Color (rgb): ${color.toSRGB().toHex()}")
        TermUi.echo("    Color (xy): [${color.toXYZ().toCIExyY().x},${color.toXYZ().toCIExyY().y}]")
    }
    light.dynamics?.run {
        TermUi.echo("    Current Dynamics: ${status}")
        TermUi.echo("    Available Dynamics: ${statusValues.joinToString()}")
        TermUi.echo("    Dynamics Speed: ${speed.toWholePercentage().format()}${"*".takeUnless { speedValid }.orEmpty()}")
    }
}
