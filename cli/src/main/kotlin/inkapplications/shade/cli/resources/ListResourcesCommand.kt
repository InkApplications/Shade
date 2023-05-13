package inkapplications.shade.cli.resources

import inkapplications.shade.cli.AuthorizedShadeCommand

object ListResourcesCommand: AuthorizedShadeCommand(
    help = "Get all resources for the bridge"
) {
    override suspend fun runCommand(): Int {
        val resources = shade.resources.listResources()

        logger.debug("Got Resources: $resources")
        resources.forEach { resource ->
            echo("${resource.id}:")
            echo("    Type: ${resource.type}")
        }

        return 0
    }
}
