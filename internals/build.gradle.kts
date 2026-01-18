plugins {
    id("library")
    kotlin("plugin.serialization")
    id("ink.publishing")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.serialization.json)
            implementation(libs.ktor.client.core)
            implementation(projects.serialization)
            api(projects.structures)
            api(libs.kimchi.logger)

            implementation(libs.ktor.client.contentnegotiation)
            implementation(libs.ktor.serialization.json)
        }

        commonTest.dependencies {
            implementation(libs.test.core)
            implementation(libs.test.annotations)
        }

        jvmMain.dependencies {
            implementation(libs.ktor.client.okhttp)
            implementation(libs.okhttp.tls)
            implementation(libs.okhttp.sse)
        }

        jsMain.dependencies {
            implementation(libs.ktor.client.js)
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
    }
}
