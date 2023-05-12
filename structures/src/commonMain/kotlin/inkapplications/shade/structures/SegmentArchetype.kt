package inkapplications.shade.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Categorizes the type/purpose of the room/zone segment.
 */
@JvmInline
@Serializable
value class SegmentArchetype(val key: String) {
    override fun toString(): String = key

    companion object {
        val LivingRoom = SegmentArchetype("living_room")
        val Kitchen = SegmentArchetype("kitchen")
        val Dining = SegmentArchetype("dining")
        val Bedroom = SegmentArchetype("bedroom")
        val KidsBedroom = SegmentArchetype("kids_bedroom")
        val Bathroom = SegmentArchetype("bathroom")
        val Nursery = SegmentArchetype("nursery")
        val Recreation = SegmentArchetype("recreation")
        val Office = SegmentArchetype("office")
        val Gym = SegmentArchetype("gym")
        val Hallway = SegmentArchetype("hallway")
        val Toilet = SegmentArchetype("toilet")
        val FrontDoor = SegmentArchetype("front_door")
        val Garage = SegmentArchetype("garage")
        val Terrace = SegmentArchetype("terrace")
        val Garden = SegmentArchetype("garden")
        val Driveway = SegmentArchetype("driveway")
        val Carport = SegmentArchetype("carport")
        val Home = SegmentArchetype("home")
        val Downstairs = SegmentArchetype("downstairs")
        val Upstairs = SegmentArchetype("upstairs")
        val TopFloor = SegmentArchetype("top_floor")
        val Attic = SegmentArchetype("attic")
        val GuestRoom = SegmentArchetype("guest_room")
        val Staircase = SegmentArchetype("staircase")
        val Lounge = SegmentArchetype("lounge")
        val ManCave = SegmentArchetype("man_cave")
        val Computer = SegmentArchetype("computer")
        val Studio = SegmentArchetype("studio")
        val Music = SegmentArchetype("music")
        val Tv = SegmentArchetype("tv")
        val Reading = SegmentArchetype("reading")
        val Closet = SegmentArchetype("closet")
        val Storage = SegmentArchetype("storage")
        val LaundryRoom = SegmentArchetype("laundry_room")
        val Balcony = SegmentArchetype("balcony")
        val Porch = SegmentArchetype("porch")
        val Barbecue = SegmentArchetype("barbecue")
        val Pool = SegmentArchetype("pool")
        val Other = SegmentArchetype("other")

        @Deprecated("This is an unbounded set of values. The values provided here are not exhaustive and will be removed in a future release.")
        fun values(): Array<SegmentArchetype> = arrayOf(
            LivingRoom,
            Kitchen,
            Dining,
            Bedroom,
            KidsBedroom,
            Bathroom,
            Nursery,
            Recreation,
            Office,
            Gym,
            Hallway,
            Toilet,
            FrontDoor,
            Garage,
            Terrace,
            Garden,
            Driveway,
            Carport,
            Home,
            Downstairs,
            Upstairs,
            TopFloor,
            Attic,
            GuestRoom,
            Staircase,
            Lounge,
            ManCave,
            Computer,
            Studio,
            Music,
            Tv,
            Reading,
            Closet,
            Storage,
            LaundryRoom,
            Balcony,
            Porch,
            Barbecue,
            Pool,
            Other,
        )

        @Deprecated(
            message = "Deprecated in favor of constructor.",
            replaceWith = ReplaceWith("SegmentArchetype(key)"),
        )
        fun valueOf(key: String) = values().single { it.key == key }
    }
}
