package inkapplications.shade.entertainment.structures

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Holds all parameters concerning the segmentation capabilities of a device.
 *
 * Segmentation allows a device to be divided into multiple independently
 * controllable sections for entertainment purposes.
 */
@Serializable
data class Segments(
    /**
     * Defines if the segmentation of the device is configurable or not.
     */
    val configurable: Boolean,

    /**
     * Maximum number of segments the device supports.
     */
    @SerialName("max_segments")
    val maxSegments: Int,

    /**
     * Contains the segments configuration of the device for entertainment purposes.
     *
     * A device can be segmented in a single way.
     */
    @SerialName("segments")
    val segmentRanges: List<@Serializable(with = SegmentRangeSerializer::class) IntRange>,
)
