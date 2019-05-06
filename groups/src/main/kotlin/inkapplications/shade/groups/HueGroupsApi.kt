package inkapplications.shade.groups

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import inkapplications.shade.lights.LightState
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * API Access for Hue's Groups endpoints.
 */
internal interface HueGroupsApi {
    /**
     * Gets a list of all groups that have been added to the bridge.
     *
     * A group is a list of lights that can be created, modified and
     * deleted by a user.
     */
    @GET("api/{token}/groups")
    fun getAll(@Path("token") token: String): Deferred<Map<String, Group>>
}

/**
 * A Group of hue devices.
 */
sealed class Group {
    /**
     * A unique, editable name given to the group.
     */
    abstract val name: String

    /**
     * The IDs of the lights that are in the group.
     */
    abstract val lights: List<String>?

    /**
     * The IDs of Hue sensors in the group.
     */
    abstract val sensors: List<String>?

    /**
     * Devices grouped by Room
     *
     * @param name A unique, editable name given to the group.
     * @param lights The IDs of the lights that are in the group.
     * @param sensors The IDs of Hue sensors in the group.
     * @param recycle No documentation for this was provided.
     *        If you know what it does, let us know.
     * @param roomType Category of Room.
     * @param state current state descriptors about the entire room's devices
     * @param lastAction The light state of one of the lamps in the group.
     */
    @JsonClass(generateAdapter = true)
    data class Room(
        override val name: String,
        override val lights: List<String>?,
        override val sensors: List<String>?,
        val recycle: Boolean,
        @Json(name = "class") val roomType: RoomType,
        val state: GroupState,
        @Json(name = "action") val lastAction: LightState?
    ): Group()

    /**
     * Luminaire Device
     *
     * There is very little documentation about what this group type
     * is or what its properties are. If you know more, let me know.
     *
     * @param name A unique, editable name given to the group.
     * @param lights The IDs of the lights that are in the group.
     * @param sensors The IDs of Hue sensors in the group.
     * @param modelId Uniquely identifies the hardware model of the luminaire.
     */
    @JsonClass(generateAdapter = true)
    data class Luminaire(
        override val name: String,
        override val lights: List<String>?,
        override val sensors: List<String>?,
        @Json(name = "modelid") val modelId: String
    ): Group()

    /**
     * LightSource
     *
     * There is very little documentation about what this group type
     * is or what its properties are. If you know more, let me know.
     *
     * @param name A unique, editable name given to the group.
     * @param lights The IDs of the lights that are in the group.
     * @param sensors The IDs of Hue sensors in the group.
     * @param uuid Unique Id in AA:BB:CC:DD format for Luminaire
     *        groups or AA:BB:CC:DD-XX format for Lightsource groups,
     *        where XX is the lightsource position.
     */
    @JsonClass(generateAdapter = true)
    data class Lightsource(
        override val name: String,
        override val lights: List<String>?,
        override val sensors: List<String>?,
        @Json(name = "uniqueid") val uuid: String
    ): Group()

    /**
     * Default group type.
     *
     * There is very little documentation about what this group type
     * is or what its properties are. If you know more, let me know.
     *
     * @param name A unique, editable name given to the group.
     * @param lights The IDs of the lights that are in the group.
     * @param sensors The IDs of Hue sensors in the group.
     */
    @JsonClass(generateAdapter = true)
    data class LightGroup(
        override val name: String,
        override val lights: List<String>?,
        override val sensors: List<String>?
    ): Group()

    /**
     * Entertainment Group
     *
     * There is very little documentation about what this group type
     * is or what its properties are. If you know more, let me know.
     *
     * @param name A unique, editable name given to the group.
     * @param lights The IDs of the lights that are in the group.
     * @param sensors The IDs of Hue sensors in the group.
     * @param recycle No documentation for this was provided.
     *        If you know what it does, let us know.
     * @param stream Info about streaming state for the group.
     * @param locations The relative position of the lights in an
     *        entertainment setup. E.g. for TV the position is relative
     *        to the TV. Can be used to configure streaming sessions.
     */
    @JsonClass(generateAdapter = true)
    data class Entertainment(
        override val name: String,
        override val lights: List<String>?,
        override val sensors: List<String>?,
        val recycle: Boolean,
        val stream: StreamInfo,
        val locations: Map<String, List<Float>>
    ): Group()

    /**
     * Zones describe a group of lights that can be controlled together.
     *
     * No idea how this differs from any other group.
     * There is very little documentation about what this group type
     * is or what its properties are. If you know more, let me know.
     *
     * @param name A unique, editable name given to the group.
     * @param lights The IDs of the lights that are in the group.
     * @param sensors The IDs of Hue sensors in the group.
     */
    @JsonClass(generateAdapter = true)
    data class Zone(
        override val name: String,
        override val lights: List<String>?,
        override val sensors: List<String>?
    ): Group()
}

/**
 * Info about streaming state for a group.
 *
 * There is very little documentation about what this info
 * is or what its properties are. If you know more, let me know.
 */
@JsonClass(generateAdapter = true)
data class StreamInfo(
    @Json(name = "proxymode") val proxyMode: String,
    @Json(name = "proxynode") val proxyNode: String,
    @Json(name = "active") val active: Boolean,
    @Json(name = "owner") val owner: String?
)

/**
 * Category of Room types.
 */
enum class RoomType {
    @Json(name = "Living room") LIVING_ROOM,
    @Json(name = "Kitchen") KITCHEN,
    @Json(name = "Dining") DINING,
    @Json(name = "Bedroom") BEDROOM,
    @Json(name = "Kids bedroom") KIDS_BEDROOM,
    @Json(name = "Bathroom") BATHROOM,
    @Json(name = "Nursery") NURSERY,
    @Json(name = "Recreation") RECREATION,
    @Json(name = "Office") OFFICE,
    @Json(name = "Gym") GYM,
    @Json(name = "Hallway") HALLWAY,
    @Json(name = "Toilet") TOILET,
    @Json(name = "Front door") FRONT_DOOR,
    @Json(name = "Garage") GARAGE,
    @Json(name = "Terrace") TERRACE,
    @Json(name = "Garden") GARDEN,
    @Json(name = "Driveway") DRIVEWAY,
    @Json(name = "Carport") CARPORT,
    @Json(name = "Home") HOME,
    @Json(name = "Downstairs") DOWNSTAIRS,
    @Json(name = "Upstairs") UPSTAIRS,
    @Json(name = "Top floor") TOP_FLOOR,
    @Json(name = "Attic") ATTIC,
    @Json(name = "Guest room") GUEST_ROOM,
    @Json(name = "Staircase") STAIRCASE,
    @Json(name = "Lounge") LOUNGE,
    @Json(name = "Man cave") MAN_CAVE,
    @Json(name = "Computer") COMPUTER,
    @Json(name = "Studio") STUDIO,
    @Json(name = "Music") MUSIC,
    @Json(name = "TV") TV,
    @Json(name = "Reading") READING,
    @Json(name = "Closet") CLOSET,
    @Json(name = "Storage") STORAGE,
    @Json(name = "Laundry room") LAUNDRY_ROOM,
    @Json(name = "Balcony") BALCONY,
    @Json(name = "Porch") PORCH,
    @Json(name = "Barbecue") BARBECUE,
    @Json(name = "Pool") POOL,
    @Json(name = "Other") OTHER
}

/**
 * Current state descriptors about the entire room's devices.
 *
 * @param allOn Whether all lights in the group are currently on
 * @param anyOn Whether any lights in the group are currently on
 */
@JsonClass(generateAdapter = true)
data class GroupState(
    @Json(name = "all_on") val allOn: Boolean,
    @Json(name = "any_on") val anyOn: Boolean
)

enum class GroupType {
    /**
     * Multisource luminaire group.
     *
     * A lighting installation of default groupings of hue lights.
     * The bridge will pre-install these groups for ease of use.
     * This type cannot be created manually.  Also, a light can only
     * be in a maximum of one luminaire group. See multisource
     * luminaires for more info.
     */
    @Json(name = "Luminaire") LUMINAIRE,

    /**
     * Lightsource Group.
     *
     * Note: This type has a copy/paste error in Hue's documentation.
     * As a result, there's no way to know what it does. Do you know?
     */
    @Json(name = "Lightsource") LIGHTSOURCE,

    /**
     * Light group.
     *
     * A group of lights that can be controlled together. This the
     * default group type that the bridge generates for user created
     * groups. Default type when no type is given on creation.
     */
    @Json(name = "LightGroup") LIGHT_GROUP,

    /**
     * Room Group.
     *
     * A group of lights that are physically located in the same place
     * in the house. Rooms behave similar as light groups, except:
     * (1) A room can be empty and contain 0 lights,
     * (2) a light is only allowed in one room and
     * (3) a room isn’t automatically deleted when all lights in that
     * room are deleted.
     */
    @Json(name = "Room") ROOM,

    /**
     * Represents an entertainment setup
     *
     * Entertainment group describe a group of lights that are used in
     * an entertainment setup. Locations describe the relative position
     * of the lights in an entertainment setup. E.g. for TV the
     * position is relative to the TV. Can be used to configure
     * streaming sessions.
     *
     * Entertainment group behave in a similar way as light groups,
     * with the exception: it can be empty and contain 0 lights. The
     * group is also not automatically recycled when lights are
     * deleted. The group of lights can be controlled together as in
     * LightGroupa.
     */
    @Json(name = "Entertainment") ENTERTAINMENT,

    /**
     * Zones describe a group of lights that can be controlled together.
     *
     * Zones can be empty and contain 0 lights. A light is allowed to
     * be in multiple zones.
     */
    @Json(name = "Zone") ZONE
}
