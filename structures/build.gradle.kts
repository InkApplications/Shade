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
                implementation(projects.serialization)
                api(libs.coroutines.core)
                api(libs.datetime)
                api(libs.spondee)
                api("com.github.ajalt.colormath:colormath:3.2.0")
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
