plugins {
    id("library")
    kotlin("plugin.serialization")
    id("ink.publishing")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.serialization.json)
                implementation(projects.internals)
                implementation(projects.serialization)
                api(projects.structures)
                api(projects.events)

                api(libs.coroutines.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.test.core)
                implementation(libs.test.annotations)
            }
        }
    }
}
