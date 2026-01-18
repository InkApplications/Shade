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
    implementation("com.github.ajalt.clikt:clikt:5.0.3")
    implementation("org.slf4j:slf4j-nop:2.0.16")
    implementation(projects.core)
}
