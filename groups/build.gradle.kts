plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    compile(project(":hue-constructs"))
    implementation(project(":hue-serialization"))
    compile(project(":auth"))
    compile(project(":lights"))

    compile(kotlin("stdlib"))
    compile(coroutines())

    implementation(retrofit())
    implementation(retrofit("converter-moshi"))
    implementation(moshi())
    implementation(moshi("moshi-adapters"))
    implementation(moshi("moshi-kotlin-codegen"))
    compile(okHttp())
}
