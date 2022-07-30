plugins {
    id("library")
    id("com.inkapplications.publishing")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.internals)
                api(projects.structures)
                api(projects.lights)
            }
        }
    }
}
