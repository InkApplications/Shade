package inkapplications.shade.entertainment.structures

import inkapplications.shade.structures.Position
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A channel in an entertainment configuration.
 *
 * Each channel groups segments of one or different lights.
 */
@Serializable
data class EntertainmentChannel(
    /**
     * Channel identifier assigned by the bridge upon creation.
     *
     * This is the number to be used by the HueStream API when addressing the channel.
     */
    @SerialName("channel_id")
    val channelId: EntertainmentChannelId,

    /**
     * XYZ position of this channel.
     *
     * It is the average position of its members.
     */
    val position: Position,

    /**
     * List of segment references that are members of this channel.
     */
    val members: List<SegmentReference>,
)
