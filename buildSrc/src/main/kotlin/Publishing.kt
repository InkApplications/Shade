import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin
import org.gradle.kotlin.dsl.*

private val Project.publishUrl get() = findProperty("publishUrl")?.toString()
private val Project.publishUsername get() = findProperty("publishUsername")?.toString()
private val Project.publishPassword get() = findProperty("publishPassword")?.toString()

fun Project.publishJava() {
    apply<MavenPublishPlugin>()
    configure<PublishingExtension> {
        publications {
            repositories {
                if (publishUrl == null) return@repositories
                maven(publishUrl!!) {
                    credentials {
                        username = publishUsername!!
                        username = publishPassword!!
                    }
                }
            }
            create<MavenPublication>("maven") {
                from(components["java"])
            }
        }
    }
}
