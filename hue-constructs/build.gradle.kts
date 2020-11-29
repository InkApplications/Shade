plugins {
    kotlin("jvm")
    kotlin("kapt")
}

publishJava()

dependencies {
    compile(kotlin("stdlib"))

    implementation(moshi())
    kapt(moshi("moshi-kotlin-codegen"))
    compile("com.github.ajalt.colormath:colormath:2.0.0")
    compile(threeTen())

    testImplementation(jUnit())
}
