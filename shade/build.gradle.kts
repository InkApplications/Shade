plugins {
    kotlin("jvm")
    id("kotlinx-atomicfu")
}

dependencies {
    compile(project(":discover"))
    compile(project(":lights"))
    compile(project(":groups"))
    compile(project(":scenes"))
    compile(project(":schedules"))
    compile(project(":auth"))

    compile(kotlin("stdlib"))
    compile(coroutines())
    compile(atomicFU())
}
