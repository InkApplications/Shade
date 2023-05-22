package inkapplications.shade.devices.parameters

import kotlinx.serialization.Serializable

/**
 * Internal object used to trigger an identify event.
 */
@Serializable
internal class IdentifyParameters private constructor(
    val action: String,
) {
    constructor(): this("identify")
}
