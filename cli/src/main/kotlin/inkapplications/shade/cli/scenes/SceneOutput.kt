package inkapplications.shade.cli.scenes

import com.github.ajalt.clikt.output.TermUi
import inkapplications.shade.scenes.structures.Scene
import inkapplications.spondee.scalar.toWholePercentage
import inkapplications.spondee.structure.format

fun echoScene(scene: Scene) {
    TermUi.echo("${scene.id}:")
    TermUi.echo("    Name: ${scene.metadata.name}")
    TermUi.echo("    Group: ${scene.group}")
    TermUi.echo("    Speed: ${scene.speed.toWholePercentage().format()}")
    TermUi.echo("    Auto Dynamic: ${scene.autoDynamic}")
    TermUi.echo("    Actions:")
    scene.actions.forEach { action ->
        TermUi.echo("         - ${action.target}:")
        TermUi.echo("            Power: ${action.action.powerValue}")
        TermUi.echo("            Brightness: ${action.action.dimmingValue?.brightness?.toWholePercentage()?.format()}")
        TermUi.echo("            Color: ${action.action.colorValue?.color?.toSRGB()?.toHex()}")
        TermUi.echo("            Color Temperature: ${action.action.colorTemperatureValue?.temperature?.toKelvin()?.format()}")
        TermUi.echo("            Effect: ${action.action.effect}")
        TermUi.echo("            Duration: ${action.action.dynamicsDuration}")
    }
}
