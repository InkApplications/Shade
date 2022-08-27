package inkapplications.shade.lights

import com.github.ajalt.colormath.model.XYZ
import com.github.ajalt.colormath.model.xyY
import inkapplications.shade.lights.parameters.*
import inkapplications.shade.lights.structures.*
import inkapplications.spondee.measure.Mireds
import inkapplications.spondee.scalar.WholePercentage
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.milliseconds

class LightUpdateParametersSerializationTest {
    val json = Json { ignoreUnknownKeys = true; prettyPrint = true }

    @Test
    fun fullJsonDeserialization() {
        val data = """
            {
                "on": {
                    "on": true
                },
                "dimming": {
                    "brightness": 12.0
                },
                "dimming_delta": {
                    "action": "up",
                    "brightness_delta": 34.0
                },
                "color_temperature": {
                    "mirek": 12
                },
                "color_temperature_delta": {
                    "action": "down",
                    "mirek_delta": 12
                },
                "color": {
                    "xy": {
                        "x": 0.2,
                        "y": 0.4
                    }
                },
                "dynamics": {
                    "duration": 12,
                    "speed": 0.34
                },
                "alert": {
                    "action": "breathe"
                },
                "gradient": {
                    "points": [
                        {
                            "color": {
                                "xy": {
                                    "x": 0.2,
                                    "y": 0.4
                                }
                            }
                        },
                        {
                            "color": {
                                "xy": {
                                    "x": 0.8,
                                    "y": 0.4
                                }
                            }
                        }
                    ]
                },
                "effects": {
                    "effect": "candle"
                },
                "timed_effects": {
                    "effect": "sunrise",
                    "duration": 123
                }
            }
        """.trimIndent()


        val parameters = LightUpdateParameters(
            power = PowerParameters(
                on = true,
            ),
            dimming = DimmingParameters(
                brightness = WholePercentage.of(12),
            ),
            dimmingDelta = DimmingDeltaParameters(
                action = DeltaAction.Up,
                brightnessDelta = WholePercentage.of(34),
            ),
            colorTemperature = ColorTemperatureParameters(
                temperature = Mireds.of(12),
            ),
            colorTemperatureDelta = ColorTemperatureDeltaParameters(
                action = DeltaAction.Down,
                temperatureDelta = Mireds.of(12),
            ),
            color = ColorParameters(
                color = xyY(.2f, .4f).let { XYZ(x = it.X, y = it.Y, z = it.Z) },
            ),
            dynamics = DynamicsParameters(
                duration = 12.milliseconds,
                speed = WholePercentage.of(34),
            ),
            alert = AlertParameters(
                action = AlertEffectType.Breathe,
            ),
            gradient = GradientParameters(
                points = listOf(
                    GradientPoint(
                        colorInfo = GradientColorInfo(
                            color = xyY(.2f, .4f).let { XYZ(x = it.X, y = it.Y, z = it.Z) },
                        )
                    ),
                    GradientPoint(
                        colorInfo = GradientColorInfo(
                            color = xyY(.8f, .4f).let { XYZ(x = it.X, y = it.Y, z = it.Z) },
                        )
                    ),
                )
            ),
            effects = EffectsParameters(
                effect = LightEffect.Candle,
            ),
            timedEffects = TimedEffectsParameters(
                effect = TimedLightEffect.Sunrise,
                duration = 123.milliseconds,
            ),
        )

        val serialized = json.encodeToString(parameters)

        assertEquals(data, serialized)
    }

    @Test
    fun minimalJsonDeserialization() {
        val data = """
            {
            }
        """.trimIndent()

        val serialized = json.encodeToString(LightUpdateParameters())

        assertEquals(data, serialized)
    }
}