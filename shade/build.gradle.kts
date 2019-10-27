plugins {
    kotlin("jvm")
}

dependencies {
    compile(project(":lights"))
    compile(project(":groups"))
    compile(project(":schedules"))
    compile(project(":auth"))

    compile(kotlin("stdlib"))
    compile(coroutines())
}
