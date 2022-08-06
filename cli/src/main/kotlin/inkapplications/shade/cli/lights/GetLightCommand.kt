package inkapplications.shade.cli.lights

import com.github.ajalt.clikt.parameters.arguments.argument
import inkapplications.shade.cli.ShadeCommand
import inkapplications.shade.cli.resourceId
import inkapplications.spondee.measure.Kelvin
import inkapplications.spondee.measure.toTemperature
import inkapplications.spondee.scalar.WholePercentage

class GetLightCommand: ShadeCommand(
    help = "Get data for a specific light"
) {
    private val lightId by argument().resourceId()

    override suspend fun runCommand(): Int {
        val light = shade.lights.getLight(lightId)

        debug { echo(light) }

        echo("${light.id.value}:")
        echo("    On: ${light.powerInfo.on}")
        light.colorTemperatureInfo?.run {
            val temperatureString = temperature?.toTemperature()?.let(Kelvin::format) ?: "--"
            echo("    Temperature: $temperatureString")
            echo("    Temperature Range: ${range}")
        }
        light.dimmingInfo?.run {
            echo("    Brightness: ${WholePercentage.format(brightness)}")
        }
        light.colorInfo?.run {
            echo("    Color (rgb): ${color.toSRGB().toHex()}")
            echo("    Color (xy): [${color.toXYZ().toCIExyY().x},${color.toXYZ().toCIExyY().y}]")
        }
        light.dynamics?.run {
            echo("    Current Dynamics: ${status}")
            echo("    Available Dynamics: ${statusValues.joinToString()}")
            echo("    Dynamics Speed: ${WholePercentage.format(speed)}${"*".takeUnless { speedValid }.orEmpty()}")
        }

        return 0
    }
}
