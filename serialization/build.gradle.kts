plugins {
    id("library")
    kotlin("plugin.serialization")
    id("com.inkapplications.publishing")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.serialization.json)
                api(libs.spondee)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(libs.test.core)
                implementation(libs.test.junit)
                implementation(libs.test.annotations)
            }
        }
    }
}
