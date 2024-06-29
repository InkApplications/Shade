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
                api(libs.coroutines.core)
                implementation(projects.serialization)
                api(projects.structures)

                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.contentnegotiation)
                implementation(libs.ktor.serialization.json)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.test.core)
                implementation(libs.test.annotations)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(libs.ktor.client.okhttp)
            }
        }

        val nativeMain by getting {
            dependencies {
                implementation(libs.ktor.client.cio)
            }
        }

        val windowsMain by getting {
            dependencies {
                implementation(libs.ktor.client.winhttp)
            }
        }

        val iosMain by getting {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(libs.ktor.client.js)
            }
        }
    }
}
