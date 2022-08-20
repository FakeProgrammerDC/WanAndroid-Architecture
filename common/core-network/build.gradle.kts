plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

}

dependencies {

    implementation(Lib.Network.retrofit)
    implementation(Lib.Network.retrofitGsonConverter)
    implementation(Lib.Network.okhttpLogging)
    implementation(Lib.Network.stetho)
    implementation(Lib.Network.stethoOkhttp)
    implementation(Lib.Jetpack.hiltAndroid)
    kapt(Lib.Jetpack.kaptHiltAndroidCompiler)
    implementation(project(mapOf("path" to ":common:core-lib")))
}