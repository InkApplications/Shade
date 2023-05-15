package inkapplications.shade.scenes.structures

import inkapplications.shade.structures.ResourceType
import inkapplications.spondee.measure.toMireds
import inkapplications.spondee.scalar.toWholePercentage
import inkapplications.spondee.structure.toDouble
import inkapplications.spondee.structure.toFloat
import inkapplications.spondee.structure.toInt
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class SceneSerializerTest {
    val json = Json { ignoreUnknownKeys = true }

    @Test
    fun fullJsonDeserialization() {
        val data = """
            {
			"id": "test-id",
			"id_v1": "/scenes/test-id",
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
			"recall": {},
			"metadata": {
				"name": "Test Scene",
				"image": {
					"rid": "test-image-id",
					"rtype": "public_image"
				},
				"appdata": "test-appdata"
			},
			"group": {
				"rid": "test-group-id",
				"rtype": "room"
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
				],
				"effects": []
			},
			"speed": 0.123,
			"auto_dynamic": false,
			"status": {
				"active": "inactive"
			},
			"type": "scene"
		}
        """.trimIndent()

        val result = json.decodeFromString<Scene>(data)

        assertEquals("test-id", result.id.value)
        assertEquals("/scenes/test-id", result.v1Id)
        assertEquals("Test Scene", result.metadata.name)
        assertEquals("test-image-id", result.metadata.image?.id?.value)
        assertEquals(ResourceType.PublicImage, result.metadata.image?.type)
        assertEquals(1, result.actions.size)
        assertEquals("action-target-id", result.actions[0].target.id.value)
        assertEquals(ResourceType.Light, result.actions[0].target.type)
        assertEquals(46.85, result.actions[0].action.dimmingValue?.brightness?.toWholePercentage()?.toDouble() ?: 0.0, 1e-16)
        assertEquals(0.123f, result.actions[0].action.colorValue?.color?.toXYZ()?.toCIExyY()?.x ?: 0f, 1e-16f)
        assertEquals(0.234f, result.actions[0].action.colorValue?.color?.toXYZ()?.toCIExyY()?.y ?: 0f, 1e-16f)
        assertEquals(1, result.palette?.color?.size)
        assertEquals(46.85, result.palette?.color?.get(0)?.dimming?.brightness?.toWholePercentage()?.toDouble() ?: 0.0, 1e-16)
        assertEquals(0.1753f, result.palette?.color?.get(0)?.color?.color?.toXYZ()?.toCIExyY()?.x ?: 0f, 1e-16f)
        assertEquals(0.0585f, result.palette?.color?.get(0)?.color?.color?.toXYZ()?.toCIExyY()?.y ?: 0f, 1e-16f)
        assertEquals(1, result.palette?.dimming?.size)
        assertEquals(46.85, result.palette?.dimming?.get(0)?.brightness?.toWholePercentage()?.toDouble() ?: 0.0, 1e-16)
        assertEquals(1, result.palette?.colorTemperature?.size)
        assertEquals(500, result.palette?.colorTemperature?.get(0)?.colorTemperature?.temperature?.toMireds()?.toInt() ?: 0)
        assertEquals(46.85, result.palette?.colorTemperature?.get(0)?.dimming?.brightness?.toWholePercentage()?.toDouble() ?: 0.0, 1e-16)
        assertEquals(0.123f, result.speed.toDecimal().toFloat(), 1e-16f)
        assertEquals(false, result.autoDynamic)
    }

    @Test
    fun minimalJsonDeserialization() {
        val json = """
            {
                "id": "test-id",
                "actions": [],
                "metadata": {
                    "name": "Test Scene"
                },
                "group": {
                    "rid": "test-group-id",
                    "rtype": "room"
                },
                "speed": 0.123,
                "auto_dynamic": false
            }
        """.trimIndent()
    }
}
