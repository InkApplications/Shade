import org.gradle.api.tasks.testing.logging.TestExceptionFormat

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.4.20"))
        classpath(atomicFU("gradle-plugin"))
    }
}

subprojects {
    group = "com.inkapplications.shade"
    version = if (version != "unspecified") version else "1.0-SNAPSHOT"
    
    repositories {
        jcenter()
    }
    tasks.withType(Test::class) {
        testLogging.exceptionFormat = TestExceptionFormat.FULL
    }
}
