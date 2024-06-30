plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm()

    js {
        nodejs()
        browser()
    }

    iosArm64()
    iosX64()

    watchosArm32()
    watchosArm64()
    watchosX64()

    tvosArm64()
    tvosX64()

    macosX64()
    macosArm64()

    linuxX64()

    mingwX64()

    sourceSets{
        val commonMain by sourceSets.getting

        val linuxX64Main by sourceSets.getting
        val macosArm64Main by sourceSets.getting
        val macosX64Main by sourceSets.getting
        val mingwX64Main by sourceSets.getting

        val nativeMain by sourceSets.creating {
            dependsOn(commonMain)
            linuxX64Main.dependsOn(this)
            macosArm64Main.dependsOn(this)
            macosX64Main.dependsOn(this)
        }

        val windowsMain by sourceSets.creating {
            dependsOn(commonMain)
            mingwX64Main.dependsOn(this)
        }

        val iosArm64Main by sourceSets.getting
        val iosX64Main by sourceSets.getting
        val watchosArm64Main by sourceSets.getting
        val watchosX64Main by sourceSets.getting
        val tvosArm64Main by sourceSets.getting
        val tvosX64Main by sourceSets.getting

        val iosMain by sourceSets.creating {
            dependsOn(commonMain)
            iosArm64Main.dependsOn(this)
            iosX64Main.dependsOn(this)
            watchosArm64Main.dependsOn(this)
            watchosX64Main.dependsOn(this)
            tvosArm64Main.dependsOn(this)
            tvosX64Main.dependsOn(this)
        }
    }
}
