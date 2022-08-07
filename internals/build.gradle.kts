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
                implementation(inkLibraries.subatomic)
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
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(ktorLibraries.client.js)
            }
        }
    }
}
