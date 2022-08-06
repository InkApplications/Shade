package inkapplications.shade.lights

import inkapplications.shade.structures.ResourceType
import inkapplications.spondee.measure.Mireds
import inkapplications.spondee.scalar.WholePercentage
import inkapplications.spondee.structure.value
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.math.roundToInt
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class LightSerializationTest {
    val json = Json { ignoreUnknownKeys = true }

    @Test
    fun fullJsonDeserialization() {
        val data = """
            {
                "alert": {
                    "action_values": [
                        "breathe"
                    ]
                },
                "color_temperature": {
                    "mirek": 161,
                    "mirek_schema": {
                        "mirek_maximum": 454,
                        "mirek_minimum": 153
                    },
                    "mirek_valid": true
                },
                "color_temperature_delta": {},
                "color": {
                    "gamut": {
                        "blue": {
                            "x": 0.1532,
                            "y": 0.0475
                        },
                        "green": {
                            "x": 0.17,
                            "y": 0.7
                        },
                        "red": {
                            "x": 0.6915,
                            "y": 0.3083
                        }
                    },
                    "gamut_type": "C",
                    "xy": {
                        "x": 0.3171,
                        "y": 0.3327
                    }
                },
                "dimming": {
                    "brightness": 50.0,
                    "min_dim_level": 0.20000000298023224
                },
                "dimming_delta": {},
                "dynamics": {
                    "speed": 0.123,
                    "speed_valid": false,
                    "status": "none",
                    "status_values": [
                        "none"
                    ]
                },
                "effects": {
                    "effect_values": [
                        "no_effect",
                        "candle"
                    ],
                    "status": "candle",
                    "status_values": [
                        "no_effect",
                        "candle"
                    ]
                },
                "timed_effects": {
                    "effect_values": [
                        "no_effect"
                    ],
                    "status": "no_effect",
                    "status_values": [
                        "no_effect"
                    ]
                },
                "id": "test-id",
                "id_v1": "/lights/2",
                "metadata": {
                    "archetype": "sultan_bulb",
                    "name": "test light"
                },
                "mode": "normal",
                "on": {
                    "on": true
                },
                "owner": {
                    "rid": "owner-id",
                    "rtype": "device"
                },
                "gradient": {
                    "points": [{
                        "color": {
                            "xy": {
                                "x": 0.12,
                                "y": 0.34
                            }
                        }
                    }],
                    "points_capable": 5
                },
                "signaling": {},
                "type": "light"
            }
        """.trimIndent()

        val light = json.decodeFromString<Light>(data)

        assertEquals("test-id", light.id.value)
        assertEquals("owner-id", light.owner.id.value)
        assertEquals(ResourceType.Device, light.owner.type)
        assertTrue(light.powerInfo.on)
        assertEquals(LightMode.Normal, light.mode)

        assertEquals(50.0, light.dimmingInfo?.brightness?.value(WholePercentage) ?: 0.0, 1e-16)
        assertEquals(0.20000000298023224, light.dimmingInfo?.minimum?.value(WholePercentage) ?: 0.0, 1e-16)
        assertEquals(161, light.colorTemperatureInfo?.temperature?.value(Mireds)?.roundToInt())
        assertEquals(153, light.colorTemperatureInfo?.range?.coolest?.value(Mireds)?.roundToInt())
        assertEquals(454, light.colorTemperatureInfo?.range?.warmest?.value(Mireds)?.roundToInt())
        assertEquals(true, light.colorTemperatureInfo?.valid)
        assertEquals(0.3171f, light.colorInfo?.color?.toXYZ()?.toCIExyY()?.x)
        assertEquals(0.3327f, light.colorInfo?.color?.toXYZ()?.toCIExyY()?.y)
        assertEquals(GamutType.C, light.colorInfo?.gamutType)
        assertEquals(0.6915f, light.colorInfo?.gamut?.red?.x)
        assertEquals(0.3083f, light.colorInfo?.gamut?.red?.y)
        assertEquals(0.17f, light.colorInfo?.gamut?.green?.x)
        assertEquals(0.7f, light.colorInfo?.gamut?.green?.y)
        assertEquals(0.1532f, light.colorInfo?.gamut?.blue?.x)
        assertEquals(0.0475f, light.colorInfo?.gamut?.blue?.y)
        assertEquals("/lights/2", light.v1Id)
        assertEquals(12.3, light.dynamics?.speed?.value(WholePercentage) ?: 0.0, 1e-16)
        assertEquals(false, light.dynamics?.speedValid)
        assertEquals(DynamicsStatus.None, light.dynamics?.status)
        assertEquals(listOf(DynamicsStatus.None), light.dynamics?.statusValues)
        assertEquals(listOf(AlertEffectType.Breathe), light.alertInfo?.actionValues)
        assertEquals(5, light.gradient?.pointsCapable)
        assertEquals(0.12f, light.gradient?.points?.single()?.colorInfo?.color?.toXYZ()?.toCIExyY()?.x)
        assertEquals(0.34f, light.gradient?.points?.single()?.colorInfo?.color?.toXYZ()?.toCIExyY()?.y)
        assertEquals(listOf(LightEffect.None, LightEffect.Candle), light.effects?.values)
        assertEquals(LightEffect.Candle, light.effects?.status)
        assertEquals(listOf(TimedLightEffect.None), light.timedEffects?.values)
        assertEquals(TimedLightEffect.None, light.timedEffects?.status)
    }

    @Test
    fun minimalJsonDeserialization() {
        val data = """
            {
                "id": "test-id",
                "owner": {
                    "rid": "owner-id",
                    "rtype": "device"
                },
                "metadata": {
                    "archetype": "sultan_bulb",
                    "name": "test light"
                },
                "on": {
                    "on": true
                },
                "mode": "normal"
            }
        """.trimIndent()

        val light = json.decodeFromString<Light>(data)

        assertEquals("test-id", light.id.value)
        assertEquals("owner-id", light.owner.id.value)
        assertEquals(ResourceType.Device, light.owner.type)
        assertTrue(light.powerInfo.on)
        assertEquals(LightMode.Normal, light.mode)

        assertNull(light.dimmingInfo)
        assertNull(light.colorTemperatureInfo)
        assertNull(light.colorInfo)
        assertNull(light.v1Id)
        assertNull(light.dynamics)
        assertNull(light.alertInfo)
        assertNull(light.gradient)
        assertNull(light.effects)
        assertNull(light.timedEffects)
    }
}
