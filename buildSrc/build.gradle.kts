plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation(libs.gradle)
    implementation(libs.publishing)
    implementation(libs.serialization.plugin)
    implementation(libs.dokka)
    implementation(libs.kotlinx.binary.compatibility)
}
