# Shade 🕶
Shade is an SDK for the Philips Hue API written for Kotlin.

## Usage

### Installation

If you haven’t already, add JitPack to your gradle repositories in 
your `build.gradle` file:

    repositories {
        maven {
            url "https://jitpack.io"
        }
    }
    
Next, add Shade as a dependency to your `build.gradle` file:

    compile "com.github.Inkapplications.Shade:shade:+" // Replace with exact version

### Initialize Shade

Connect Shade to your Hue Bridge by providing a config:

 - `baseUrl`: The URL of the Hue Hub you want to connect to
 - `appID`: A Unique ID for your application.

```kotlin
val config = ShadeConfig(baseUrl = "http://192.168.0.2/", appId = "shade#shade")
val shade = Shade(config)
```

### Authenticate with Hue

To authenticate with your hue system call the `awaitToken()` method:

```kotlin
suspend fun connect() {
    println("Waiting for token. Press Button on Hue Bridge")
    shade.auth.awaitToken()
    println("Authenticated Successfully!")
}
```

### Control Lights

```kotlin
suspend fun turnOffAll() {
    shade.lights.getLights().forEach { (id, light) ->
        println("Turning off Light ID: $id, Name: ${light.name}")
        shade.lights.setLightState(id, LightStateModification(on = false))
    }
}
```

### Groups

Groups can be accessed through Shade's `.groups` service:

```kotlin
shade.groups.getGroups()
    .also { println("Found ${it.size} Light Groups") }
    .forEach { (id, group) -> println("    Group ID: $id, Name: ${group.name}") }

shade.groups.setState("10", GroupStateModification(
    on = true,
    colorTemperature = 200,
    brightness = 100
))
```
