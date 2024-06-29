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
                implementation(libs.ktor.client.core)
                implementation(projects.serialization)
                api(projects.structures)
                api(libs.kimchi.logger)

                implementation(libs.ktor.client.contentnegotiation)
                implementation(libs.ktor.serialization.json)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(libs.ktor.client.okhttp)
                implementation(libs.okhttp.tls)
                implementation(libs.okhttp.sse)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(libs.ktor.client.js)
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

        val commonTest by getting {
            dependencies {
                implementation(libs.test.core)
                implementation(libs.test.annotations)
            }
        }
    }
}
