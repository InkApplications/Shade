package inkapplications.shade.cli.lights

import com.github.ajalt.clikt.output.TermUi
import inkapplications.shade.lights.structures.Light
import inkapplications.spondee.measure.Kelvin
import inkapplications.spondee.measure.toTemperature
import inkapplications.spondee.scalar.WholePercentage

fun echoLight(light: Light) {
    TermUi.echo("${light.id.value}:")
    TermUi.echo("    On: ${light.powerInfo.on}")
    TermUi.echo("    Mode: ${light.mode}")
    light.colorTemperatureInfo?.run {
        val temperatureString = temperature?.toTemperature()?.let(Kelvin::format) ?: "--"
        TermUi.echo("    Temperature: $temperatureString")
        TermUi.echo("    Temperature Range: ${range}")
    }
    light.dimmingInfo?.run {
        TermUi.echo("    Brightness: ${WholePercentage.format(brightness)}")
    }
    light.colorInfo?.run {
        TermUi.echo("    Color (rgb): ${color.toSRGB().toHex()}")
        TermUi.echo("    Color (xy): [${color.toXYZ().toCIExyY().x},${color.toXYZ().toCIExyY().y}]")
    }
    light.dynamics?.run {
        TermUi.echo("    Current Dynamics: ${status}")
        TermUi.echo("    Available Dynamics: ${statusValues.joinToString()}")
        TermUi.echo("    Dynamics Speed: ${WholePercentage.format(speed)}${"*".takeUnless { speedValid }.orEmpty()}")
    }
}
