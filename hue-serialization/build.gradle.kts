plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    compile(project(":hue-constructs"))

    compile(kotlin("stdlib"))
    compile(coroutines())

    compile(moshi())
    kapt(moshi("moshi-kotlin-codegen"))
    compile(retrofit())
    compile(retrofit("converter-moshi"))

    implementation(threeTen())

    testImplementation(jUnit())
}
