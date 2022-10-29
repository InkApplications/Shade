rootProject.name = "shade"
enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    versionCatalogs {
        create("kotlinLibraries") {
            from(files("gradle/versions/kotlin.toml"))
        }
        create("inkLibraries") {
            from(files("gradle/versions/ink.toml"))
        }
        create("ktorLibraries") {
            from(files("gradle/versions/ktor.toml"))
        }
    }
}

include("auth")
include("cli")
include("discover")
include("grouped-lights")
include("internals")
include("lights")
include("serialization")
include("core")
include("rooms")
include("structures")
include("zones")
