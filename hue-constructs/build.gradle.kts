plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    compile(kotlin("stdlib"))

    implementation(moshi())
    kapt(moshi("moshi-kotlin-codegen"))
    compile("com.github.ajalt:colormath:1.2.0")
    compile(threeTen())

    testImplementation(jUnit())
}
