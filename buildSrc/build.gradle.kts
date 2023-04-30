plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation(kotlinLibraries.gradle)
    implementation(inkLibraries.publishing)
    implementation(kotlinLibraries.serialization.plugin)
    implementation(kotlinLibraries.dokka)
    implementation(kotlinLibraries.kotlinx.binary.compatibility)
}
