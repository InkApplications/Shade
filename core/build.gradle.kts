plugins {
    id("library")
    id("ink.publishing")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.internals)
                api(projects.auth)
                api(projects.button)
                api(projects.discover)
                api(projects.devices)
                api(projects.events)
                api(projects.groupedLights)
                api(projects.homekit)
                api(projects.lights)
                api(projects.lightlevel)
                api(projects.resources)
                api(projects.rooms)
                api(projects.scenes)
                api(projects.structures)
                api(projects.zones)

                api(libs.kimchi.logger)
            }
        }
    }
}
