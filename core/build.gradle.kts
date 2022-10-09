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
                api(projects.structures)
                api(projects.discover)
                api(projects.lights)
                api(projects.rooms)

                api(inkLibraries.kimchi.logger)
            }
        }
    }
}
