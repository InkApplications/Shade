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
                api(inkLibraries.spondee)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(kotlinLibraries.test.core)
                implementation(kotlinLibraries.test.junit)
                implementation(kotlinLibraries.test.annotations)
            }
        }
    }
}
