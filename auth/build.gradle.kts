plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    implementation(project(":hue-serialization"))
    compile(project(":hue-constructs"))

    compile(kotlin("stdlib"))
    compile(coroutines())

    implementation(retrofit())
    implementation(retrofit("converter-moshi"))
    implementation(moshi())
    kapt(moshi("moshi-kotlin-codegen"))

    compile(okHttp())
}
