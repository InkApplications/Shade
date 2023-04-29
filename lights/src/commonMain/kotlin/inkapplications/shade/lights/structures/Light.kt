package inkapplications.shade.lights.structures

import inkapplications.shade.structures.PowerInfo
import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * State and capabilities of a light resource.
 */
@Serializable
data class Light(
    /**
     * Unique identifier representing a specific resource instance
     */
    val id: ResourceId,

    /**
     * Owner of the service
     *
     * In case the owner service is deleted, the service also gets deleted.
     */
    val owner: ResourceReference,

    /**
     * On/Off state of the light
     */
    @SerialName("on")
    val powerInfo: PowerInfo,

    /**
     * Current mode that the light is in
     */
    val mode: LightMode,

    /**
     * Information about the light's dimming, if supported.
     */
    @SerialName("dimming")
    val dimmingInfo: DimmingInfo? = null,

    /**
     * Information about the color temperature and capabilities of the light.
     */
    @SerialName("color_temperature")
    val colorTemperatureInfo: ColorTemperatureInfo? = null,

    /**
     * Information about the bulb's color and color capabilities.
     */
    @SerialName("color")
    val colorInfo: ColorInfo? = null,

    /**
     * Clip v1 resource identifier.
     */
    @Deprecated("V1 Resource")
    @SerialName("id_v1")
    val v1Id: String? = null,

    /**
     * Light dynamics information if supported
     */
    val dynamics: LightDynamics? = null,

    /**
     * Information on alerting effects for this light
     */
    @SerialName("alert")
    val alertInfo: AlertInfo? = null,

    /**
     * Gradient information if supported.
     */
    val gradient: Gradient? = null,

    /**
     * Basic feature containing effect properties.
     */
    val effects: LightingEffectInfo? = null,

    /**
     * Basic feature containing timed effect properties.
     */
    @SerialName("timed_effects")
    val timedEffects: TimedLightingEffectInfo? = null,

    /**
     * Signaling properties of the light.
     */
    val signaling: LightSignaling? = null,

    /**
     * Feature containing properties to configure powerup behaviour of
     * a lightsource.
     */
    val powerup: LightPowerup? = null,
)
