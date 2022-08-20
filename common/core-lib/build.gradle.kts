plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    kotlin("kapt")
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

    api(Lib.Androidx.coreKtx)
    api(Lib.Androidx.appcompat)
    api(Lib.material)
    api(Lib.Kotlin.kotlinSerial)

    api(Lib.Jetpack.viewModelKtx)
    api(Lib.Jetpack.liveDataKtx)
    api(Lib.Jetpack.runtimeKtx)

    api(Lib.utilCodex)

    androidTestImplementation(Lib.Test.junit)
    androidTestImplementation(Lib.Test.espressoCore)
}