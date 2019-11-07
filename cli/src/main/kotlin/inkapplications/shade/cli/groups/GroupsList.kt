package inkapplications.shade.cli.groups

import com.github.ajalt.clikt.core.CliktCommand
import dagger.Reusable
import inkapplications.shade.Shade
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@Reusable
class GroupsList @Inject constructor(
    private val shade: Shade
): CliktCommand(
    name = "groups:list",
    help = "List all groups known to the hub"
) {
    override fun run() {
        runBlocking {
            shade.groups.getGroups().forEach { (id, group) ->
                echo("$id:")
                echo("  name: ${group.name}")
                echo("  lights: [${group.lights?.joinToString(",") }]")
            }
        }
    }
}
