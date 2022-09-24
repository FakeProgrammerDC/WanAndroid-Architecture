plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
    }
}

dependencies {

    implementation(Lib.Network.retrofit)
    implementation(Lib.Network.retrofitGsonConverter)
    
    implementation(Lib.Network.okhttp)
    implementation(Lib.Network.okhttpLogging)

    implementation(Lib.Network.stetho)
    implementation(Lib.Network.stethoOkhttp)

    implementation(Lib.Jetpack.hiltAndroid)
    kapt(Lib.Jetpack.kaptHiltCompiler)
    kapt(Lib.Jetpack.kaptHiltAndroidCompiler)

    implementation(project(mapOf("path" to ":common:core-utils")))
}