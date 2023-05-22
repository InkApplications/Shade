package inkapplications.shade.devices.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Product types for a device.
 */
@Serializable
@JvmInline
value class ProductArchetype(val key: String) {
    override fun toString(): String = key

    companion object {
        val BridgeV2 = ProductArchetype("bridge_v2")
        val UnknownArchetype = ProductArchetype("unknown_archetype")
        val ClassicBulb = ProductArchetype("classic_bulb")
        val SultanBulb = ProductArchetype("sultan_bulb")
        val FloodBulb = ProductArchetype("flood_bulb")
        val SpotBulb = ProductArchetype("spot_bulb")
        val CandleBulb = ProductArchetype("candle_bulb")
        val LusterBulb = ProductArchetype("luster_bulb")
        val PendantRound = ProductArchetype("pendant_round")
        val PendantLong = ProductArchetype("pendant_long")
        val CeilingRound = ProductArchetype("ceiling_round")
        val CeilingSquare = ProductArchetype("ceiling_square")
        val FloorShade = ProductArchetype("floor_shade")
        val FloorLantern = ProductArchetype("floor_lantern")
        val TableShade = ProductArchetype("table_shade")
        val RecessedCeiling = ProductArchetype("recessed_ceiling")
        val RecessedFloor = ProductArchetype("recessed_floor")
        val SingleSpot = ProductArchetype("single_spot")
        val DoubleSpot = ProductArchetype("double_spot")
        val TableWash = ProductArchetype("table_wash")
        val WallLantern = ProductArchetype("wall_lantern")
        val WallShade = ProductArchetype("wall_shade")
        val FlexibleLamp = ProductArchetype("flexible_lamp")
        val GroundSpot = ProductArchetype("ground_spot")
        val WallSpot = ProductArchetype("wall_spot")
        val Plug = ProductArchetype("plug")
        val HueGo = ProductArchetype("hue_go")
        val HueLightstrip = ProductArchetype("hue_lightstrip")
        val HueIris = ProductArchetype("hue_iris")
        val HueBloom = ProductArchetype("hue_bloom")
        val Bollard = ProductArchetype("bollard")
        val WallWasher = ProductArchetype("wall_washer")
        val HuePlay = ProductArchetype("hue_play")
        val VintageBulb = ProductArchetype("vintage_bulb")
        val VintageCandleBulb = ProductArchetype("vintage_candle_bulb")
        val EllipseBulb = ProductArchetype("ellipse_bulb")
        val TriangleBulb = ProductArchetype("triangle_bulb")
        val SmallGlobeBulb = ProductArchetype("small_globe_bulb")
        val LargeGlobeBulb = ProductArchetype("large_globe_bulb")
        val EdisonBulb = ProductArchetype("edison_bulb")
        val ChristmasTree = ProductArchetype("christmas_tree")
        val StringLight = ProductArchetype("string_light")
        val HueCentris = ProductArchetype("hue_centris")
        val HueLightstripTv = ProductArchetype("hue_lightstrip_tv")
        val HueLightstripPc = ProductArchetype("hue_lightstrip_pc")
        val HueTube = ProductArchetype("hue_tube")
        val HueSigne = ProductArchetype("hue_signe")
        val PendantSpot = ProductArchetype("pendant_spot")
        val CeilingHorizontal = ProductArchetype("ceiling_horizontal")
        val CeilingTube = ProductArchetype("ceiling_tube")
    }
}
