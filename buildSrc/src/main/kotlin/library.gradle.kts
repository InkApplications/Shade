plugins {
    kotlin("multiplatform")
}

kotlin {
    applyDefaultHierarchyTemplate()
    jvm()

    js {
        nodejs()
        browser {
            testTask {
                enabled = false
            }
        }
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
}
