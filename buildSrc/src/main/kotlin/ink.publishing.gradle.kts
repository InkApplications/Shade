plugins {
    id("maven-publish")
    id("signing")
    id("org.jetbrains.dokka")
    id("org.jetbrains.kotlinx.binary-compatibility-validator")
}

val dokkaHtml by tasks.getting(org.jetbrains.dokka.gradle.DokkaTask::class)

val javadocJar: TaskProvider<Jar> by tasks.registering(Jar::class) {
    dependsOn(dokkaHtml)
    archiveClassifier.set("javadoc")
    from(dokkaHtml.outputDirectory)
}

apiValidation {
    nonPublicMarkers.add("inkapplications.spondee.format.SimpleNumberFormats")
}

publishing {
    repositories {
        val mavenUser = findProperty("mavenUser")?.toString()
        val mavenPassword = findProperty("mavenPassword")?.toString()
        if (mavenUser != null && mavenPassword != null) {
            maven {
                name = "MavenCentral"
                setUrl("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
                credentials {
                    username = mavenUser
                    password = mavenPassword
                }
            }
        }
    }
    publications {
        withType<MavenPublication> {
            artifact(javadocJar)
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

signing {
    val signingKey = findProperty("signingKey")?.toString()
    val signingKeyId = findProperty("signingKeyId")?.toString()
    val signingPassword = findProperty("signingPassword")?.toString()
    val shouldSign = signingKeyId != null && signingKey != null && signingPassword != null

    if (shouldSign) {
        useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
        sign(publishing.publications)
    }
}

val signingTasks: TaskCollection<Sign> = tasks.withType<Sign>()
tasks.withType<PublishToMavenRepository>().configureEach {
    mustRunAfter(signingTasks)
}
