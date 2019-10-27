plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    compile(project(":hue-constructs"))
    implementation(project(":hue-serialization"))
    compile(project(":auth"))

    compile(kotlin("stdlib"))
    compile(coroutines())

    implementation(retrofit())
    implementation(retrofit("converter-moshi"))
    implementation(moshi())
    implementation(moshi("moshi-adapters"))
    kapt(moshi("moshi-kotlin-codegen"))
    compile(okHttp())
    compile(threeTen())
}
