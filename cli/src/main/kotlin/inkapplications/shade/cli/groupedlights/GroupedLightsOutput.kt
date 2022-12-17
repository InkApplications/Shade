package inkapplications.shade.cli.groupedlights

import com.github.ajalt.clikt.output.TermUi
import inkapplications.shade.groupedlights.structures.GroupedLight

fun echoGroup(group: GroupedLight) {
    TermUi.echo("${group.id.value}:")
    TermUi.echo("    Owner: ${group.owner}")
    group.powerInfo?.run {
        TermUi.echo("    On: $on")
    }
    group.dimmingInfo?.run {
        TermUi.echo("    Dimming: $brightness")
    }
    group.alertInfo?.run {
        TermUi.echo("    Alerts: ${actionValues.joinToString()}")
    }
}
