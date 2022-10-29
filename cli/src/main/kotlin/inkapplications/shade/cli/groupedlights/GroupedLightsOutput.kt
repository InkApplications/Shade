package inkapplications.shade.cli.groupedlights

import com.github.ajalt.clikt.output.TermUi
import inkapplications.shade.groupedlights.structures.GroupedLight

fun echoGroup(group: GroupedLight) {
    TermUi.echo("${group.id.value}:")
    group.powerInfo?.run {
        TermUi.echo("    On: $on")
    }
    group.alertInfo?.run {
        TermUi.echo("    Alerts: ${actionValues.joinToString()}")
    }
}
