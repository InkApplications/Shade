package inkapplications.shade.groupedlights

import com.github.ajalt.colormath.model.XYZ
import com.github.ajalt.colormath.model.xyY
import inkapplications.shade.groupedlights.parameters.GroupedLightUpdateParameters
import inkapplications.shade.lights.parameters.*
import inkapplications.shade.lights.structures.AlertEffectType
import inkapplications.shade.structures.parameters.PowerParameters
import inkapplications.spondee.measure.mireds
import inkapplications.spondee.scalar.percent
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.milliseconds

class GroupedLightUpdateParametersSerializationTest {
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
                "alert": {
                    "action": "breathe"
                },
                "dynamics": {
                    "duration": 12,
                    "speed": 0.34
                }
            }
        """.trimIndent()


        val parameters = GroupedLightUpdateParameters(
            power = PowerParameters(
                on = true,
            ),
            dimming = DimmingParameters(
                brightness = 12.percent,
            ),
            dimmingDelta = DimmingDeltaParameters(
                action = DeltaAction.Up,
                brightnessDelta = 34.percent,
            ),
            colorTemperature = ColorTemperatureParameters(
                temperature = 12.mireds,
            ),
            colorTemperatureDelta = ColorTemperatureDeltaParameters(
                action = DeltaAction.Down,
                temperatureDelta = 12.mireds,
            ),
            color = ColorParameters(
                color = xyY(.2f, .4f).let { XYZ(x = it.X, y = it.Y, z = it.Z) },
            ),
            dynamics = DynamicsParameters(
                duration = 12.milliseconds,
                speed = 34.percent,
            ),
            alert = AlertParameters(
                action = AlertEffectType.Breathe,
            ),
        )

        val serialized = json.encodeToString(parameters)

        assertEquals(data, serialized)
    }

    @Test
    fun minimalJsonDeserialization() {
        val data = """
            {}
        """.trimIndent()

        val serialized = json.encodeToString(GroupedLightUpdateParameters())

        assertEquals(data, serialized)
    }
}
