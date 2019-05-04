package inkapplications.shade.constructs

import com.squareup.moshi.JsonClass

/**
 * Attributes for a device.
 *
 * I guess they made this whole thing, but never came up with anything
 * more than name as an attribute.
 * This is used in things like scans and renaming.
 *
 * @param name The user-readable name of the device.
 */
@JsonClass(generateAdapter = true)
data class DeviceAttributes(val name: String)
