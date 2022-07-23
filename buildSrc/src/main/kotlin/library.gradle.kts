plugins {
    kotlin("multiplatform")
    id("maven-publish")
}

kotlin {
    jvm()
    js {
        nodejs()
        browser()
    }
}

project.extensions.configure(PublishingExtension::class.java) {
    publications {
        withType<MavenPublication> {
            pom {
                name.set("Shade - ${project.name}")
                description.set("Multiplatform Kotlin SDK for Hue lighting controls (unofficial)")
                url.set("https://shade.lighting")
                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://choosealicense.com/licenses/mit/")
                    }
                }
                developers {
                    developer {
                        id.set("reneevandervelde")
                        name.set("Renee Vandervelde")
                        email.set("Renee@InkApplications.com")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/InkApplications/shade.git")
                    developerConnection.set("scm:git:ssh://git@github.com:InkApplications/shade.git")
                    url.set("https://github.com/InkApplications/shade")
                }
            }
        }
    }
}
