package inkapplications.shade.cli

import com.github.ajalt.clikt.parameters.arguments.ProcessedArgument
import com.github.ajalt.clikt.parameters.arguments.convert
import com.github.ajalt.clikt.parameters.options.NullableOption
import com.github.ajalt.clikt.parameters.options.convert
import com.github.ajalt.clikt.parameters.types.choice
import com.github.ajalt.clikt.parameters.types.int
import com.github.ajalt.colormath.Color
import com.github.ajalt.colormath.parse
import inkapplications.shade.lights.structures.*
import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference
import inkapplications.shade.structures.ResourceType
import inkapplications.shade.structures.SegmentArchetype
import inkapplications.spondee.measure.ColorTemperature
import inkapplications.spondee.measure.Mireds
import inkapplications.spondee.measure.metric.Kelvin
import inkapplications.spondee.measure.metric.kelvin
import inkapplications.spondee.measure.mireds
import inkapplications.spondee.scalar.WholePercentage
import inkapplications.spondee.scalar.percent
import kotlin.time.Duration

/**
 * Convert a string argument into a resource ID
 */
fun ProcessedArgument<String, String>.resourceId() = convert { ResourceId(it) }

/**
 * Convert an argument to a timed light effect value.
 */
fun ProcessedArgument<String, String>.segmentArchetype(): ProcessedArgument<SegmentArchetype, SegmentArchetype> {
    return choice(choices = SegmentArchetype.values().map { it.key }.toTypedArray())
        .convert { SegmentArchetype.valueOf(it) }
}

/**
 * Convert an argument to a timed light effect value.
 */
fun NullableOption<String, String>.segmentArchetype(): NullableOption<SegmentArchetype, SegmentArchetype> {
    return choice(choices = SegmentArchetype.values().map { it.key }.toTypedArray())
        .convert { SegmentArchetype.valueOf(it) }
}

/**
 * Convert a comma separated argument to a list of resource references for devices.
 */
fun NullableOption<String, String>.deviceResourceReferences(): NullableOption<List<ResourceReference>, List<ResourceReference>> {
    return convert {
        it.split(',')
            .map { ResourceId(it.trim()) }
            .map { ResourceReference(id = it, type = ResourceType.Device) }
    }
}

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
 * Convert an argument into a color temperature in kelvin
 */
fun NullableOption<String, String>.colorTemperature(): NullableOption<ColorTemperature, ColorTemperature> {
    return convert {
        val sanitized = it.trim()
        val isKelvin = it.endsWith("k", ignoreCase = true)

        return@convert sanitized
            .trimEnd('K', 'k')
            .toInt()
            .let { if (isKelvin) it.kelvin else it.mireds }
    }
}

/**
 * Convert an argument into a color temperature in mireds
 */
fun NullableOption<String, String>.mireds(): NullableOption<Mireds, Mireds> {
    return int().convert { it.mireds }
}

/**
 * Convert an argument into a color.
 */
fun NullableOption<String, String>.color(): NullableOption<Color, Color> {
    return convert { Color.parse(it) }
}

/**
 * Convert an argument into a color.
 */
fun NullableOption<String, String>.duration(): NullableOption<Duration, Duration> {
    return convert { Duration.parse(it) }
}

/**
 * Convert an argument to an alert effect value.
 */
fun NullableOption<String, String>.alertEffect(): NullableOption<AlertEffectType, AlertEffectType> {
    return choice(choices = AlertEffectType.values().map { it.key }.toTypedArray())
        .convert { AlertEffectType.valueOf(it) }
}

/**
 * Convert an argument to a light effect value.
 */
fun NullableOption<String, String>.lightEffect(): NullableOption<LightEffect, LightEffect> {
    return choice(choices = LightEffect.values().map { it.key }.toTypedArray())
        .convert { LightEffect.valueOf(it) }
}

/**
 * Convert an argument to a timed light effect value.
 */
fun NullableOption<String, String>.timedLightEffect(): NullableOption<TimedLightEffect, TimedLightEffect> {
    return choice(choices = TimedLightEffect.values().map { it.key }.toTypedArray())
        .convert { TimedLightEffect.valueOf(it) }
}

/**
 * Convert an argument to an alert effect value.
 */
fun NullableOption<String, String>.gradientPoints(): NullableOption<List<GradientPoint>, List<GradientPoint>> {
    return convert {
        it.split(",")
            .map { it.trim() }
            .map { Color.parse(it) }
            .map { GradientColorInfo(color = it) }
            .map { GradientPoint(colorInfo = it) }
    }
}
