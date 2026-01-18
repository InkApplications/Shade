package inkapplications.shade.cli.groupedlights

import com.github.ajalt.clikt.core.CliktCommand
import inkapplications.shade.groupedlights.structures.GroupedLight

fun CliktCommand.echoGroup(group: GroupedLight) {
    echo("${group.id.value}:")
    echo("    Owner: ${group.owner}")
    group.powerInfo?.run {
        echo("    On: $on")
    }
    group.dimmingInfo?.run {
        echo("    Dimming: $brightness")
    }
    group.alertInfo?.run {
        echo("    Alerts: ${actionValues.joinToString()}")
    }
}
