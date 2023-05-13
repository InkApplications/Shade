package inkapplications.shade.devices.parameters

import kotlinx.serialization.Serializable

/**
 * Configurable data for a device.
 */
@Serializable
data class UpdateDeviceParameters internal constructor(
    /**
     * User metadata for the device.
     */
    val metadata: DeviceMetadataParameters? = null,
    internal val identify: IdentifyParameters? = null,
) {
    constructor(
        metadata: DeviceMetadataParameters? = null,
    ): this(
        metadata = metadata,
        identify = null,
    )
}
