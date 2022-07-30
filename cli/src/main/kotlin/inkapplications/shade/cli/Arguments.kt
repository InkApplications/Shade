package inkapplications.shade.cli

import com.github.ajalt.clikt.parameters.arguments.ProcessedArgument
import com.github.ajalt.clikt.parameters.arguments.convert
import inkapplications.shade.structures.ResourceId

/**
 * Convert a string argument into a resource ID
 */
fun ProcessedArgument<String, String>.resourceId() = convert { ResourceId(it) }
