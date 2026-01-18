plugins {
    id("library")
    kotlin("plugin.serialization")
    id("ink.publishing")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.serialization.json)
            api(libs.coroutines.core)
            implementation(projects.serialization)
            api(projects.structures)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.contentnegotiation)
            implementation(libs.ktor.serialization.json)
        }

        commonTest.dependencies {
            implementation(libs.test.core)
            implementation(libs.test.annotations)
        }

        jvmMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }

        linuxMain.dependencies {
            implementation(libs.ktor.client.curl)
        }

        mingwMain.dependencies {
            implementation(libs.ktor.client.winhttp)
        }

        appleMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }

        jsMain.dependencies {
            implementation(libs.ktor.client.js)
        }
    }
}
