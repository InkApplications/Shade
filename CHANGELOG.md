Change Log
==========

3.0.0
-----

Kotlin 2.0 Upgrade

### Changed:

 - The library has been upgraded to use Kotlin 2.0. While there are no direct
   API changes to the library's API, this may require upgrades to your
   project's dependencies for compatibility.

### Removed:

 - JS No longer supports the Legacy targets, as these are not compatible with K2.
 - Due to an external dependency, WatchOS-Arm32 support was removed.

2.1.0
-----

### Added:

 - Devices module for getting/updating device metadata.
 - Resources module for listing bridge resources
 - Scenes module for getting/updating scenes definitions for rooms.

### Deprecated:

 - Deprecated value class list accessors, due to unbounded nature of collections.
 - Deprecated value class `valueOf` methods in favor of using constructor directly.

2.0.0
-----

This major release of the SDK contains breaking changes required to support
Kotlin Multiplatform as well as the [v2 endpoints] of the Hue API. Please
refer to the [docs] for new setup instructions.

The new SDK contains more thorough documentation as well as a published API
reference.

### Supported Modules:

 - Bridge Discovery
 - Light Control
 - Grouped Lights
 - Rooms
 - Zones

### Supported Platforms:
 - jvm
 - js (IR)
     - nodejs
 -   - browser
 - iosArm64
 - iosX64
 - watchosArm64
 - watchosX64
 - tvosArm64
 - tvosX64
 - macosX64
 - macosArm64
 - linuxX64
 - mingwX64

[v2 endpoints]: https://developers.meethue.com/develop/hue-api-v2/api-reference/
[docs]: https://shade.lighting/

1.2.0
-----

 - Update ColorMath dependency to 2.0.0
 - Update to Kotlin 1.4.20
 - Update OkHttp to 4.9.0
 - Update ThreeTenBP to 1.5.0

1.1.3
-----

### Fixed:

 - Handle null `lastInstall` fields on hue lights' update state.

1.1.2
-----

### Fixed:

 - Migrate Discover functionality from old nupnp to new endpoint (discover.meethue.com)

1.1.1
-----

### Fixed:

 - Unhandled JsonDataException when a room is empty.

### Other Changes:
 - Update ThreeTenBP to 1.4.4
 - Update OkHttp to 4.8.0
 - Update Moshi to 1.9.3
 - Update Coroutines to 1.3.8

1.1.0
-----

### Other Changes:
 - API Requests for modifying light state are rate limited by 100ms.
 - API Requests for modifying group state are rate limited by 1s.

1.0.0
-----

Initial Release SDK and CLI with support for:
 - Light Control
 - Group Control
 - Scenes
 - Schedules
