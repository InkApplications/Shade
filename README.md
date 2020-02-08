
![Shade Logo](docs/svg/logo-full.svg)

_A modern CLI and SDK for the Philips Hue API written in Kotlin._

## CLI

Shade's CLI tools can be used to connect, discover and control lights
on your Hue Bridge.

```shell
$ shade-cli connect
Connecting to bridge at 192.168.1.100
Waiting to connect. Press the button on your Hue Device now.
Success 🎉

$ shade lights:list
1:
  name: Renee's Office
  uuid: 00:19:28:37:00:a1:b9:f2-0b
  type: Extended color light
  firmware: 5.127.1.26581
2:
  name: Kitchen
  uuid: 00:91:82:73:00:b9:c3:7a-0b
  type: Extended color light
  firmware: 5.127.1.26581

$ shade lights:control 1 --on --brightness 50
```

## Kotlin SDK

Shede's Kotlin SDK uses Kotlin Coroutines and a modern API to allow
your JVM or Android app to discover and control lights.

```kotlin
fun main() {
    runBlocking {
        println("Searching for Hue Bridges")
        val shade = Shade()
        val devices = shade.discovery.getDevices()
        shade.setBaseUrl(devices.first().url)

        println("Press the connect button on the hue bridge")
        shade.auth.awaitToken()

        println("Turning all the lights on!")
        shade.groups.setState(
            GROUP_ALL,
            GroupStateModification(
                on = on
            )
        )
    }
}
```

## Documentation

For more examples and documentation please see [the website](https://shade.lighting)

## Contributions and Issues

Shade is free, Open Source, actively maintained, and always looking for contributions.

There are many Hue devices and things to do with them.
Testing all that can be difficult.
If you have an issue, please [tell us](https://github.com/InkApplications/Shade/issues/new)!
