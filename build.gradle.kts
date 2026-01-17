import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    id("org.jetbrains.dokka")
}

repositories {
    mavenCentral()
}

dependencies {
    dokka(project(":auth"))
    dokka(project(":core"))
    dokka(project(":devices"))
    dokka(project(":discover"))
    dokka(project(":events"))
    dokka(project(":grouped-lights"))
    dokka(project(":homekit"))
    dokka(project(":internals"))
    dokka(project(":lights"))
    dokka(project(":resources"))
    dokka(project(":rooms"))
    dokka(project(":scenes"))
    dokka(project(":serialization"))
    dokka(project(":structures"))
    dokka(project(":zones"))
}

dokka {
    moduleName.set("Shade")
}

subprojects {
    repositories {
        mavenCentral()
    }
    tasks.withType(Test::class) {
        testLogging.exceptionFormat = TestExceptionFormat.FULL
    }
}

tasks.named("dokkaGeneratePublicationHtml") {
    doLast {
        copy {
            from(layout.buildDirectory.dir("dokka/html"))
            into(rootDir.resolve("docs/reference/${project.version}"))
        }
    }
}
