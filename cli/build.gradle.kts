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
    implementation("org.slf4j:slf4j-nop:2.0.17")
    implementation(projects.core)
}
