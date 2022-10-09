package inkapplications.shade.rooms.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Categorizes the type/purpose of the room.
 */
@JvmInline
@Serializable
value class RoomArchetype private constructor(val key: String) {
    override fun toString(): String = key

    companion object {
        val LivingRoom = RoomArchetype("living_room")
        val Kitchen = RoomArchetype("kitchen")
        val Dining = RoomArchetype("dining")
        val Bedroom = RoomArchetype("bedroom")
        val KidsBedroom = RoomArchetype("kids_bedroom")
        val Bathroom = RoomArchetype("bathroom")
        val Nursery = RoomArchetype("nursery")
        val Recreation = RoomArchetype("recreation")
        val Office = RoomArchetype("office")
        val Gym = RoomArchetype("gym")
        val Hallway = RoomArchetype("hallway")
        val Toilet = RoomArchetype("toilet")
        val FrontDoor = RoomArchetype("front_door")
        val Garage = RoomArchetype("garage")
        val Terrace = RoomArchetype("terrace")
        val Garden = RoomArchetype("garden")
        val Driveway = RoomArchetype("driveway")
        val Carport = RoomArchetype("carport")
        val Home = RoomArchetype("home")
        val Downstairs = RoomArchetype("downstairs")
        val Upstairs = RoomArchetype("upstairs")
        val TopFloor = RoomArchetype("top_floor")
        val Attic = RoomArchetype("attic")
        val GuestRoom = RoomArchetype("guest_room")
        val Staircase = RoomArchetype("staircase")
        val Lounge = RoomArchetype("lounge")
        val ManCave = RoomArchetype("man_cave")
        val Computer = RoomArchetype("computer")
        val Studio = RoomArchetype("studio")
        val Music = RoomArchetype("music")
        val Tv = RoomArchetype("tv")
        val Reading = RoomArchetype("reading")
        val Closet = RoomArchetype("closet")
        val Storage = RoomArchetype("storage")
        val LaundryRoom = RoomArchetype("laundry_room")
        val Balcony = RoomArchetype("balcony")
        val Porch = RoomArchetype("porch")
        val Barbecue = RoomArchetype("barbecue")
        val Pool = RoomArchetype("pool")
        val Other = RoomArchetype("other")

        fun values(): Array<RoomArchetype> = arrayOf(
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
        fun valueOf(key: String) = values().single { it.key == key }
    }
}
