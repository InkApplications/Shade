plugins {
    id("library")
    kotlin("plugin.serialization")
    id("com.inkapplications.publishing")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlinLibraries.serialization.json)
                implementation(projects.internals)
                implementation(projects.serialization)
                api(projects.structures)
                api(projects.events)

                api(kotlinLibraries.coroutines.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlinLibraries.test.core)
                implementation(kotlinLibraries.test.annotations)
            }
        }
    }
}
