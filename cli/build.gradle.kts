plugins {
    application
    kotlin("jvm")
}

application {
    applicationName = "shade"
    mainClassName = "inkapplications.shade.cli.MainKt"
}

dependencies {
    implementation(libs.coroutines.core)
    implementation("com.github.ajalt.clikt:clikt:3.4.0")
    implementation(projects.core)
}
