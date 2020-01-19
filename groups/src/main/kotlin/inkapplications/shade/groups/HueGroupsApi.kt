package inkapplications.shade.groups

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import inkapplications.shade.constructs.*
import inkapplications.shade.lights.AlertState
import inkapplications.shade.lights.LightEffect
import inkapplications.shade.lights.LightState
import inkapplications.shade.serialization.converter.FirstInCollection
import retrofit2.http.*

/**
 * A Special group ID containing all lamps known by the bridge.
 */
const val GROUP_ALL = "0"

/**
 * API URL for modifying the state of a Group.
 */
private const val HUE_GROUPS_STATE_URL = "api/{token}/groups/{group}/action"

/**
 * Creates a Command object used for scheduling a future modification.
 *
 * @param token The Auth token to use when making the request.
 * @param group The Group of lights to modify the state of.
 * @param modification State settings to apply to the group.
 */
fun createGroupModificationCommand(
    token: String,
    group: String,
    modification: GroupStateModification
) = Command(
    address = HUE_GROUPS_STATE_URL
        .replace("{token}", token)
        .replace("{group}", group),
    method = "PUT",
    body = modification
)

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
    suspend fun getAll(@Path("token") token: String): Map<String, Group>

    /**
     * Creates a new group containing the lights specified and optional name.
     *
     * A new group is created in the bridge with the next available id.
     *
     * @param group The editable attributes of the group
     */
    @POST("api/{token}/groups")
    @FirstInCollection
    suspend fun createGroup(@Path("token") token: String, @Body group: MutableGroupAttributes): IdToken

    /**
     * Get a single group's attributes.
     *
     * @param groupId The unique ID of the group to fetch.
     */
    @GET("api/{token}/groups/{group}")
    suspend fun getGroup(@Path("token") token: String, @Path("group") groupId: String): Group

    /**
     * Allows the user to modify the name, light and class membership of a group.
     *
     * @param groupId The unique ID of the group to update.
     * @param attributes The editable attributes to set. Optional data will be unmodified.
     */
    @PUT("api/{token}/groups/{group}")
    suspend fun updateGroup(
        @Path("token") token: String,
        @Path("group") groupId: String,
        @Body attributes: MutableGroupAttributes
    ): HueResponse<HueProperties>

    /**
     * Modifies the state of all lights in a group.
     *
     * @param groupId The unique ID of the group to update.
     * @param state The state to assign to all lights in the group.
     */
    @PUT(HUE_GROUPS_STATE_URL)
    suspend fun setState(
        @Path("token") token: String,
        @Path("group") groupId: String,
        @Body state: GroupStateModification
    ): HueResponse<HueProperties>

    /**
     * Deletes the specified group from the bridge.
     *
     * @param groupId The unique ID of the group to delete.
     */
    @DELETE("api/{token}/groups/{group}")
    suspend fun deleteGroup(
        @Path("token") token: String,
        @Path("group") groupId: String
    ): HueResponse<String>
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
     * Room Group.
     *
     * A group of lights that are physically located in the same place
     * in the house. Rooms behave similar as light groups, except:
     * (1) A room can be empty and contain 0 lights,
     * (2) a light is only allowed in one room and
     * (3) a room isn’t automatically deleted when all lights in that
     * room are deleted.
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
        val sensors: List<String>?,
        val recycle: Boolean,
        @Json(name = "class") val roomType: RoomType,
        val state: GroupState,
        @Json(name = "action") val lastAction: LightState?
    ): Group()

    /**
     * Multisource luminaire group.
     *
     * A lighting installation of default groupings of hue lights.
     * The bridge will pre-install these groups for ease of use.
     * This type cannot be created manually.  Also, a light can only
     * be in a maximum of one luminaire group. See multisource
     * luminaires for more info.
     *
     * There is very little documentation about what this group type
     * is or what its properties are. If you know more, let me know.
     *
     * @param name A unique, editable name given to the group.
     * @param lights The IDs of the lights that are in the group.
     * @param modelId Uniquely identifies the hardware model of the luminaire.
     */
    @JsonClass(generateAdapter = true)
    data class Luminaire(
        override val name: String,
        override val lights: List<String>?,
        @Json(name = "modelid") val modelId: String
    ): Group()

    /**
     * Lightsource Group.
     *
     * Note: This type has a copy/paste error in Hue's documentation.
     * As a result, there's no way to know what it does. Do you know?
     *
     * There is very little documentation about what this group type
     * is or what its properties are. If you know more, let me know.
     *
     * @param name A unique, editable name given to the group.
     * @param lights The IDs of the lights that are in the group.
     * @param uuid Unique Id in AA:BB:CC:DD format for Luminaire
     *        groups or AA:BB:CC:DD-XX format for Lightsource groups,
     *        where XX is the lightsource position.
     */
    @JsonClass(generateAdapter = true)
    data class Lightsource(
        override val name: String,
        override val lights: List<String>?,
        @Json(name = "uniqueid") val uuid: String
    ): Group()

    /**
     * Light group.
     *
     * A group of lights that can be controlled together. This the
     * default group type that the bridge generates for user created
     * groups. Default type when no type is given on creation.
     *
     * There is very little documentation about what this group type
     * is or what its properties are. If you know more, let me know.
     *
     * @param name A unique, editable name given to the group.
     * @param lights The IDs of the lights that are in the group.
     */
    @JsonClass(generateAdapter = true)
    data class LightGroup(
        override val name: String,
        override val lights: List<String>?
    ): Group()

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
     * LightGroup.
     *
     * There is very little documentation about what this group type
     * is or what its properties are. If you know more, let me know.
     *
     * @param name A unique, editable name given to the group.
     * @param lights The IDs of the lights that are in the group.
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
        val recycle: Boolean,
        val stream: StreamInfo,
        val locations: Map<String, List<Float>>
    ): Group()

    /**
     * Zones describe a group of lights that can be controlled together.
     *
     * Zones can be empty and contain 0 lights. A light is allowed to
     * be in multiple zones.
     *
     * No idea how this differs from any other group.
     * There is very little documentation about what this group type
     * is or what its properties are. If you know more, let me know.
     *
     * @param name A unique, editable name given to the group.
     * @param lights The IDs of the lights that are in the group.
     */
    @JsonClass(generateAdapter = true)
    data class Zone(
        override val name: String,
        override val lights: List<String>?
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

/**
 * Attributes of a Light group that are modifiable.
 */
sealed class MutableGroupAttributes {
    /**
     * A unique, editable name given to the group. (optional)
     */
    abstract val name: String?

    /**
     * The IDs of the lights that are in the group. (optional)
     */
    abstract val lights: Set<String>?

    /**
     * Attributes of a Luminaire group that are modifiable.
     *
     * TODO: Need more information about this object's attributes.
     *
     * @param name A unique, editable name given to the group. (optional)
     * @param lights The IDs of the lights that are in the group. (optional)
     */
    @JsonClass(generateAdapter = true)
    data class Luminaire(
        override val name: String? = null,
        override val lights: Set<String>? = null
    ): MutableGroupAttributes()

    /**
     * Attributes of a Lightsource group that are modifiable.
     *
     * TODO: Need more information about this object's attributes.
     *
     * @param name A unique, editable name given to the group. (optional)
     * @param lights The IDs of the lights that are in the group. (optional)
     */
    @JsonClass(generateAdapter = true)
    data class Lightsource(
        override val name: String? = null,
        override val lights: Set<String>? = null
    ): MutableGroupAttributes()

    /**
     * Attributes of a Light group that are modifiable.
     *
     * TODO: Need more information about this object's attributes.
     *
     * @param name A unique, editable name given to the group. (optional)
     * @param lights The IDs of the lights that are in the group. (optional)
     */
    @JsonClass(generateAdapter = true)
    data class LightGroup(
        override val name: String? = null,
        override val lights: Set<String>? = null
    ): MutableGroupAttributes()

    /**
     * Attributes of a Room group that are modifiable.
     *
     * @param name A unique, editable name given to the group. (optional)
     * @param lights The IDs of the lights that are in the group. (optional)
     * @param sensors The IDs of the sensors that are in the group. (optional)
     * @param roomType Category of Room, default is OTHER
     */
    @JsonClass(generateAdapter = true)
    data class Room(
        override val name: String? = null,
        override val lights: Set<String>? = null,
        val sensors: Set<String>? = null,
        @Json(name = "class") val roomType: RoomType = RoomType.OTHER
    ): MutableGroupAttributes()

    /**
     * Attributes of an entertainment group that are modifiable.
     *
     * TODO: Need more information about this object's attributes.
     *
     * @param name A unique, editable name given to the group. (optional)
     * @param lights The IDs of the lights that are in the group. (optional)
     */
    @JsonClass(generateAdapter = true)
    data class Entertainment(
        override val name: String? = null,
        override val lights: Set<String>? = null
    ): MutableGroupAttributes()

    /**
     * Attributes of an zone group that are modifiable.
     *
     * TODO: Need more information about this object's attributes.
     *
     * @param name A unique, editable name given to the group. (optional)
     * @param lights The IDs of the lights that are in the group. (optional)
     */
    @JsonClass(generateAdapter = true)
    data class Zone(
        override val name: String? = null,
        override val lights: Set<String>? = null
    ): MutableGroupAttributes()
}

/**
 * Options when modifying the state of a group of lights.
 *
 * This is *almost* identical to a `LightStateModification`
 * but also gives the option to set a scene.
 *
 * @property on On/Off state of the light. On=true, Off=false
 * @property brightness Brightness of the light.
 * @property hue Hue of the light. This is a wrapping value between 0
 *           and 65535. Note, that hue/sat values are hardware
 *           dependent which means that programming two devices with
 *           the same value does not garantuee that they will be the
 *           same color. Programming 0 and 65535 would mean that the
 *           light will resemble the color red, 21845 for green and
 *           43690 for blue.
 * @property saturation Saturation of the light. 254 is the most
 *           saturated (colored) and 0 is the least saturated (white).
 * @property effect The dynamic effect of the light.
 * @property transitionTime The duration of the transition from the
 *           light’s current state to the new state. This is given as
 *           a multiple of 100ms and defaults to 4 (400ms).
 *           For example, setting `transitiontime:10` will make the
 *           transition last 1 second.
 * @property cieColorCoordinates The x and y coordinates of a color
 *           in CIE color space.
 *           The first entry is the x coordinate and the second entry
 *           is the y coordinate. Both x and y are between 0 and 1.
 *           Using CIE xy, the colors can be the same on all lamps if
 *           the coordinates are within every lamps gamuts (example:
 *           “xy”:[0.409,0.5179] is the same color on all lamps). If
 *           not, the lamp will calculate it’s closest color and use
 *           that. The CIE xy color is absolute, independent from the
 *           hardware.
 * @property colorTemperature The Color temperature of the light.
 *           2012 connected lights are capable of 153 (6500K) to 500 (2000K).
 * @property alert The alert effect is a temporary change to the bulb’s state.
 *           Note that this contains the last alert sent to the light and
 *           not its current state. i.e. After the breathe cycle has
 *           finished the bridge does not reset the alert to “none“.
 * @property brightnessIncrement Increments or decrements the value of the
 *           brightness.  this is ignored if the bri attribute is provided.
 *           Any ongoing bri transition is stopped. Setting a value of 0
 *           also stops any ongoing transition. The bridge will return the
 *           bri value after the increment is performed.
 * @property saturationIncrement Increments or decrements the value of
 *           the saturation. This is ignored if the sat attribute is
 *           provided. Any ongoing sat transition is stopped.
 *           Setting a value of 0 also stops any ongoing transition.
 *           The bridge will return the sat value after the increment
 *           is performed.
 * @property hueIncrement Increments or decrements the value of the hue.
 *           This is ignored if the hue attribute is provided. Any
 *           ongoing color transition is stopped. Setting a value of 0
 *           also stops any ongoing transition. The bridge will return
 *           the hue value after the increment is performed.Note if the
 *           resulting values are < 0 or > 65535 the result is wrapped.
 *           For example: `hueIncrement` of 1 on a hue value of 65535
 *           results in a hue of 0 and `hueIncrement` of -1 on a hue of
 *           0 results in a hue of 65534.
 * @property colorTemperatureIncrement Increments or decrements the
 *           value of the color temperature. This is ignored if the
 *           temperature attribute is provided. Any ongoing color
 *           transition is stopped. Setting a value of 0 also stops any
 *           ongoing transition. The bridge will return the temperature
 *           value after the increment is performed.
 * @property cieCoordinateTranslation Translates the coordinates of the
 *           CIE color. This is ignored if the CIE coordinates attribute
 *           is provided.
 *           Any ongoing color transition is stopped. Setting a value
 *           of 0 also stops any ongoing transition. Will stop at it’s
 *           gamut boundaries. The bridge will return the xy value after
 *           the increment is performed. Max value [0.5, 0.5].
 * @property scene The scene identifier if the scene you wish to recall.
 */
@JsonClass(generateAdapter = true)
data class GroupStateModification(
    val on: Boolean? = null,
    @Json(name="bri") val brightness: Brightness? = null,
    val hue: Int? = null,
    @Json(name="sat") val saturation: Int? = null,
    val effect: LightEffect? = null,
    @Json(name="transitiontime") val transitionTime: Int? = null,
    @Json(name="xy") val cieColorCoordinates: Coordinates? = null,
    @Json(name="ct") val colorTemperature: ColorTemperature? = null,
    val alert: AlertState? = null,
    @Json(name="bri_inc") val brightnessIncrement: Brightness? = null,
    @Json(name="sat_inc") val saturationIncrement: Int? = null,
    @Json(name="hue_inc") val hueIncrement: Int? = null,
    @Json(name="ct_inc") val colorTemperatureIncrement: Int? = null,
    @Json(name="xy_inc") val cieCoordinateTranslation: Coordinates? = null,
    val scene: String? = null
)
