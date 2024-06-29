package inkapplications.shade.scenes.parameters

import com.github.ajalt.colormath.model.XYZ
import com.github.ajalt.colormath.model.xyY
import inkapplications.shade.lights.structures.*
import inkapplications.shade.scenes.structures.*
import inkapplications.shade.structures.PowerValue
import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference
import inkapplications.shade.structures.ResourceType
import inkapplications.spondee.measure.mireds
import inkapplications.spondee.scalar.percent
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.seconds

class SceneUpdateSerializerTest {
    val json = Json { ignoreUnknownKeys = true; prettyPrint = true }

    @Test
    fun fullSerialize() {
        val expected = """
            {
                "actions": [
                    {
                        "target": {
                            "rid": "action-target-id",
                            "rtype": "light"
                        },
                        "action": {
                            "on": {
                                "on": true
                            },
                            "dimming": {
                                "brightness": 46.85
                            },
                            "color": {
                                "xy": {
                                    "x": 0.123,
                                    "y": 0.234
                                }
                            }
                        }
                    }
                ],
                "recall": {
                    "action": "active",
                    "status": "dynamic_palette",
                    "duration": 2000,
                    "dimming": {
                        "brightness": 45.67
                    }
                },
                "metadata": {
                    "name": "Test Scene",
                    "image": {
                        "rid": "test-image-id",
                        "rtype": "public_image"
                    }
                },
                "palette": {
                    "color": [
                        {
                            "color": {
                                "xy": {
                                    "x": 0.1753,
                                    "y": 0.0585
                                }
                            },
                            "dimming": {
                                "brightness": 46.85
                            }
                        }
                    ],
                    "dimming": [
                        {
                            "brightness": 46.85
                        }
                    ],
                    "color_temperature": [
                        {
                            "color_temperature": {
                                "mirek": 500
                            },
                            "dimming": {
                                "brightness": 46.85
                            }
                        }
                    ]
                }
            }
        """.trimIndent()

        val parameters = SceneUpdateParameters(
            actions = listOf(
                SceneActionReference(
                    target = ResourceReference(
                        id = ResourceId("action-target-id"),
                        type = ResourceType.Light
                    ),
                    action = SceneAction(
                        powerValue = PowerValue(
                            on = true,
                        ),
                        dimmingValue = DimmingValue(
                            brightness = 46.85.percent,
                        ),
                        colorValue = ColorValue(
                            color = xyY(0.123, 0.234).let { XYZ(x = it.X, y = it.Y, z = it.Z) },
                        )
                    )
                )
            ),
            recall = SceneRecall(
                action = SceneRecallAction.Active,
                status = SceneRecallStatus.DynamicPalette,
                duration = 2.seconds,
                dimming = DimmingValue(45.67.percent)
            ),
            metadata = SceneMetadata(
                name = "Test Scene",
                image = ResourceReference(
                    id = ResourceId("test-image-id"),
                    type = ResourceType.PublicImage
                )
            ),
            palette = ScenePalette(
                color = listOf(
                    ColorPalette(
                        color = ColorValue(
                            color = xyY(0.1753, 0.0585).let { XYZ(x = it.X, y = it.Y, z = it.Z) },
                        ),
                        dimming = DimmingValue(
                            brightness = 46.85.percent,
                        )
                    )
                ),
                dimming = listOf(
                    DimmingValue(
                        brightness = 46.85.percent,
                    )
                ),
                colorTemperature = listOf(
                    ColorTemperaturePalette(
                        colorTemperature = ColorTemperatureValue(500.mireds),
                        dimming = DimmingValue(
                            brightness = 46.85.percent,
                        )
                    )
                )
            )
        )

        val result = json.encodeToString(parameters)

        assertEquals(expected, result)
    }

    @Test
    fun minimalJsonSerialization() {
        val expected = """
            {}
        """.trimIndent()

        val parameters = SceneUpdateParameters()

        val result = json.encodeToString(parameters)

        assertEquals(expected, result)
    }
}
