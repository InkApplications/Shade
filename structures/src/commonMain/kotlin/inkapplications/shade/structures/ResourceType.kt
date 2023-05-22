package inkapplications.shade.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Discriminator key used within the hue api to determine object types
 */
@Serializable
@JvmInline
value class ResourceType(val key: String) {
    override fun toString(): String = key

    companion object {
        val Device = ResourceType("device")
        val BridgeHome = ResourceType("bridge_home")
        val Room = ResourceType("room")
        val Zone = ResourceType("zone")
        val Light = ResourceType("light")
        val Button = ResourceType("button")
        val Temperature = ResourceType("temperature")
        val LightLevel = ResourceType("light_level")
        val Motion = ResourceType("motion")
        val Entertainment = ResourceType("entertainment")
        val GroupedLight = ResourceType("grouped_light")
        val DevicePower = ResourceType("device_power")
        val ZigbeeBridgeConnectivity = ResourceType("zigbee_bridge_connectivity")
        val ZigbeeConnectivity = ResourceType("zigbee_connectivity")
        val ZgpConnectivity = ResourceType("zgp_connectivity")
        val Bridge = ResourceType("bridge")
        val Homekit = ResourceType("homekit")
        val Scene = ResourceType("scene")
        val EntertainmentConfiguration = ResourceType("entertainment_configuration")
        val PublicImage = ResourceType("public_image")
        val AuthV1 = ResourceType("auth_v1")
        val BehaviorScript = ResourceType("behavior_script")
        val BehaviorInstance = ResourceType("behavior_instance")
        val Geofence = ResourceType("geofence")
        val GeofenceClient = ResourceType("geofence_client")
        val Geolocation = ResourceType("geolocation")

        @Deprecated("This is an unbounded set of values. The values provided here are not exhaustive and will be removed in a future release.")
        fun values(): Array<ResourceType> = arrayOf(
            Device,
            BridgeHome,
            Room,
            Zone,
            Light,
            Button,
            Temperature,
            LightLevel,
            Motion,
            Entertainment,
            GroupedLight,
            DevicePower,
            ZigbeeBridgeConnectivity,
            ZigbeeConnectivity,
            ZgpConnectivity,
            Bridge,
            Homekit,
            Scene,
            EntertainmentConfiguration,
            PublicImage,
            AuthV1,
            BehaviorScript,
            BehaviorInstance,
            Geofence,
            GeofenceClient,
            Geolocation,
        )

        @Deprecated(
            message = "Deprecated in favor of constructor",
            replaceWith = ReplaceWith("ResourceType(key)"),
        )
        fun valueOf(key: String) = values().single { it.key == key }
    }
}
