enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    versionCatalogs {
        create("inkLibraries") {
            from(files("../gradle/versions/ink.toml"))
        }
        create("kotlinLibraries") {
            from(files("../gradle/versions/kotlin.toml"))
        }
    }
}
