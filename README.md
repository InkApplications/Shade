# Shade

![Shade Logo](docs/svg/logo-full.svg)

_Cross Platform CLI and Multiplatform Kotlin SDK for controlling Hue devices._

## Cross-Platform Command Line

Shade's CLI application for Windows, MacOS and Linux provides commands for
controlling your lighing directly from the terminal.

```shell
$ shade update-light $lightId --brightness=10%
```

[Get Started](https://shade.lighting)

## Kotlin Multiplatform SDK

Shade's Kotlin SDK provides API's for controlling your lighting on Java,
Android and Javascript platforms.

```kotlin
shade.lights.updateLight(
    id = lightId,
    parameters = LightUpdateParameters(
        brightness = 10.percent,
    ),
)
```

[Get Started](https://shade.lighting)

## Free + Open Source
Shade is free under the [MIT License], the project is Open Source, actively
maintained, and always looking for contributions.

There are many Hue devices and things to do with them. Testing all that can
be difficult. Please [report] any issues you find.

[MIT License]:LICENSE
[report]:https://github.com/InkApplications/Shade/issues/new/choose

## Trademarks

"Hue" and "Philips" are trademarks of Signify Holding and are not affiliated
with this project.
