package inkapplications.shade.lights

import inkapplications.shade.lights.structures.*
import inkapplications.shade.lights.structures.LightSignal.Companion.OnOff
import inkapplications.shade.structures.ResourceType
import inkapplications.spondee.measure.toMireds
import inkapplications.spondee.scalar.toWholePercentage
import inkapplications.spondee.structure.convert
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
                "signaling": {
                    "status": {
                        "signal": "on_off",
                        "estimated_end": "2021-10-18T17:04:55Z"
                    }
                },
                "powerup": {
                    "preset": "custom",
                    "configured": true,
                    "on": {
                        "mode": "on",
                        "on": {
                            "on": true
                        }
                    },
                    "dimming": {
                        "mode": "dimming",
                        "dimming": {
                            "brightness": 69
                        }
                    },
                    "color": {
                        "mode": "color",
                        "color_temperature": {
                            "mirek": 420
                        },
                        "color": {
                            "xy": {
                                "x": .12,
                                "y": .34
                            }
                        }
                    }
                }
                "type": "light"
            }
        """.trimIndent()

        val light = json.decodeFromString<Light>(data)

        assertEquals("test-id", light.id.value)
        assertEquals("owner-id", light.owner.id.value)
        assertEquals(ResourceType.Device, light.owner.type)
        assertTrue(light.powerInfo.on)
        assertEquals(LightMode.Normal, light.mode)

        assertEquals(50.0, light.dimmingInfo?.brightness?.toWholePercentage()?.value?.toDouble() ?: 0.0, 1e-16)
        assertEquals(0.20000000298023224, light.dimmingInfo?.minimum?.toWholePercentage()?.value?.toDouble() ?: 0.0, 1e-16)
        assertEquals(161, light.colorTemperatureInfo?.temperature?.toMireds()?.convert { roundToInt() })
        assertEquals(153, light.colorTemperatureInfo?.range?.coolest?.toMireds()?.convert { roundToInt() })
        assertEquals(454, light.colorTemperatureInfo?.range?.warmest?.toMireds()?.convert { roundToInt() })
        assertEquals(true, light.colorTemperatureInfo?.valid)
        assertEquals(0.3171f, light.colorInfo?.color?.toXYZ()?.toCIExyY()?.x ?: 0f, 1e-16f)
        assertEquals(0.3327f, light.colorInfo?.color?.toXYZ()?.toCIExyY()?.y ?: 0f, 1e-16f)
        assertEquals(GamutType.C, light.colorInfo?.gamutType)
        assertEquals(0.6915f, light.colorInfo?.gamut?.red?.x ?: 0f, 1e-16f)
        assertEquals(0.3083f, light.colorInfo?.gamut?.red?.y ?: 0f, 1e-16f)
        assertEquals(0.17f, light.colorInfo?.gamut?.green?.x ?: 0f, 1e-16f)
        assertEquals(0.7f, light.colorInfo?.gamut?.green?.y ?: 0f, 1e-16f)
        assertEquals(0.1532f, light.colorInfo?.gamut?.blue?.x ?: 0f, 1e-16f)
        assertEquals(0.0475f, light.colorInfo?.gamut?.blue?.y ?: 0f, 1e-16f)
        assertEquals("/lights/2", light.v1Id)
        assertEquals(12.3, light.dynamics?.speed?.toWholePercentage()?.value?.toDouble() ?: 0.0, 1e-16)
        assertEquals(false, light.dynamics?.speedValid)
        assertEquals(DynamicsStatus.None, light.dynamics?.status)
        assertEquals(listOf(DynamicsStatus.None), light.dynamics?.statusValues)
        assertEquals(listOf(AlertEffectType.Breathe), light.alertInfo?.actionValues)
        assertEquals(5, light.gradient?.pointsCapable)
        assertEquals(0.12f, light.gradient?.points?.single()?.colorValue?.color?.toXYZ()?.toCIExyY()?.x ?: 0f, 1e-16f)
        assertEquals(0.34f, light.gradient?.points?.single()?.colorValue?.color?.toXYZ()?.toCIExyY()?.y ?: 0f, 1e-16f)
        assertEquals(listOf(LightEffect.None, LightEffect.Candle), light.effects?.values)
        assertEquals(LightEffect.Candle, light.effects?.status)
        assertEquals(listOf(TimedLightEffect.None), light.timedEffects?.values)
        assertEquals(TimedLightEffect.None, light.timedEffects?.status)
        assertEquals(OnOff, light.signaling?.status?.signal)
        assertEquals(1634576695000, light.signaling?.status?.estimatedEnd?.toEpochMilliseconds())
        assertEquals(true, light.powerup?.configured)
        assertEquals(true, (light.powerup?.powerState as? PowerupPowerState.StaticPower?)?.powerValue?.on)
        val powerup = light.powerup as LightPowerup.Custom?
        assertEquals(69, (powerup?.dimmingState as? PowerupDimmingState.StaticBrightness?)?.dimmingValue?.brightness?.toWholePercentage()?.value?.toInt())
        val colorState = (powerup?.colorState as? PowerupColorState.Color?)
        assertEquals(420, colorState?.temperatureValue?.temperature?.toMireds()?.value?.toInt())
        assertEquals(.12f, colorState?.colorValue?.color?.toXYZ()?.toCIExyY()?.x ?: 0f, 1e-16f)
        assertEquals(.34f, colorState?.colorValue?.color?.toXYZ()?.toCIExyY()?.y ?: 0f, 1e-16f)
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
        assertNull(light.powerup)
        assertNull(light.signaling)
    }
}
