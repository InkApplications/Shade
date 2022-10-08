package inkapplications.shade.cli

import com.github.ajalt.clikt.parameters.arguments.ProcessedArgument
import com.github.ajalt.clikt.parameters.arguments.convert
import com.github.ajalt.clikt.parameters.options.NullableOption
import com.github.ajalt.clikt.parameters.options.convert
import com.github.ajalt.clikt.parameters.types.choice
import com.github.ajalt.clikt.parameters.types.int
import inkapplications.shade.structures.ResourceId
import inkapplications.spondee.measure.Mireds
import inkapplications.spondee.measure.metric.Kelvin
import inkapplications.spondee.measure.metric.kelvin
import inkapplications.spondee.measure.mireds
import inkapplications.spondee.scalar.WholePercentage
import inkapplications.spondee.scalar.percent

/**
 * Convert a string argument into a resource ID
 */
fun ProcessedArgument<String, String>.resourceId() = convert { ResourceId(it) }

/**
 * Convert an argument into a percentage object
 */
fun NullableOption<String, String>.percentage(): NullableOption<WholePercentage, WholePercentage> {
    return convert { it.trimEnd(' ', '%') }
        .int()
        .convert { it.percent }
}

/**
 * Convert an argument into an on/off boolean representation
 */
fun NullableOption<String, String>.power(): NullableOption<Boolean, Boolean> {
    return choice("on", "off", ignoreCase = true)
        .convert {
            it == "on"
        }
}

/**
 * Convert an argument into a color temperature in kelvin
 */
fun NullableOption<String, String>.kelvin(): NullableOption<Kelvin, Kelvin> {
    return convert { it.trimEnd('K', 'k', ' ') }
        .int()
        .convert { it.kelvin }
}

/**
 * Convert an argument into a color temperature in mireds
 */
fun NullableOption<String, String>.mireds(): NullableOption<Mireds, Mireds> {
    return int().convert { it.mireds }
}
