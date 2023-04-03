plugins {
    id("library")
    id("com.inkapplications.publishing")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.internals)
                api(projects.auth)
                api(projects.discover)
                api(projects.events)
                api(projects.groupedLights)
                api(projects.lights)
                api(projects.rooms)
                api(projects.structures)
                api(projects.zones)

                api(inkLibraries.kimchi.logger)
            }
        }
    }
}
