plugins {
    application
    kotlin("jvm")
}

application {
    applicationName = "shade"
    mainClass.set("inkapplications.shade.cli.MainKt")
}

dependencies {
    implementation(libs.coroutines.core)
    implementation("com.github.ajalt.clikt:clikt:4.4.0")
    implementation(projects.core)
}
