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
                implementation(ktorLibraries.client.core)
                implementation(projects.serialization)
                api(projects.structures)
                api(inkLibraries.kimchi.logger)

                implementation(ktorLibraries.client.contentnegotiation)
                implementation(ktorLibraries.serialization.json)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(ktorLibraries.client.okhttp)
                implementation(ktorLibraries.okhttp.tls)
                implementation(ktorLibraries.okhttp.sse)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(ktorLibraries.client.js)
            }
        }

        val nativeMain by getting {
            dependencies {
                implementation(ktorLibraries.client.curl)
            }
        }

        val iosMain by getting {
            dependencies {
                implementation(ktorLibraries.client.darwin)
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
