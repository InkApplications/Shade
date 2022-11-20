package inkapplications.shade.lights.events

import inkapplications.shade.lights.structures.*
import inkapplications.shade.structures.PowerInfo
import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference
import inkapplications.shade.structures.UndocumentedApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data sent during a light update on the events API.
 */
@Serializable
@UndocumentedApi
data class LightEvent(
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
    val powerInfo: PowerInfo? = null,

    /**
     * Information about the light's dimming, if supported.
     */
    @SerialName("dimming")
    val dimmingInfo: DimmingInfoEvent? = null,

    /**
     * Information about the color temperature and capabilities of the light.
     */
    @SerialName("color_temperature")
    val colorTemperatureInfo: ColorTemperatureInfoEvent? = null,

    /**
     * Information about the bulb's color and color capabilities.
     */
    @SerialName("color")
    val colorInfo: ColorInfoEvent? = null,
)
