package inkapplications.shade.cli.lightlevels

import com.github.ajalt.clikt.core.CliktCommand
import inkapplications.shade.lightlevel.structures.LightLevel

fun CliktCommand.echoLightLevel(lightLevel: LightLevel) {
    echo("${lightLevel.id.value}:")
    echo("    Owner: ${lightLevel.owner}")
    echo("    Enabled: ${lightLevel.enabled}")

    val report = lightLevel.light.lightLevelReport
    if (report != null) {
        echo("    Level: ${report.lightLevel}")
        echo("    Changed: ${report.changed}")
    }
}
