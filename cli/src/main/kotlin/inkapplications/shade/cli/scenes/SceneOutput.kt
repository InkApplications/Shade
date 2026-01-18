package inkapplications.shade.cli.scenes

import com.github.ajalt.clikt.core.CliktCommand
import inkapplications.shade.scenes.structures.Scene
import inkapplications.spondee.scalar.toWholePercentage
import inkapplications.spondee.structure.format

fun CliktCommand.echoScene(scene: Scene) {
    echo("${scene.id}:")
    echo("    Name: ${scene.metadata.name}")
    echo("    Group: ${scene.group}")
    echo("    Speed: ${scene.speed.toWholePercentage().format()}")
    echo("    Auto Dynamic: ${scene.autoDynamic}")
    echo("    Actions:")
    scene.actions.forEach { action ->
        echo("         - ${action.target}:")
        echo("            Power: ${action.action.powerValue}")
        echo("            Brightness: ${action.action.dimmingValue?.brightness?.toWholePercentage()?.format()}")
        echo("            Color: ${action.action.colorValue?.color?.toSRGB()?.toHex()}")
        echo("            Color Temperature: ${action.action.colorTemperatureValue?.temperature?.toKelvin()?.format()}")
        echo("            Effect: ${action.action.effect}")
        echo("            Duration: ${action.action.dynamicsDuration}")
    }
}
