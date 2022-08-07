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
                api(kotlinLibraries.coroutines.core)
                implementation(projects.serialization)
                api(projects.structures)

                implementation(ktorLibraries.client.core)
                implementation(ktorLibraries.client.contentnegotiation)
                implementation(ktorLibraries.serialization.json)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlinLibraries.test.core)
                implementation(kotlinLibraries.test.annotations)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(ktorLibraries.client.okhttp)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(ktorLibraries.client.js)
            }
        }
    }
}
