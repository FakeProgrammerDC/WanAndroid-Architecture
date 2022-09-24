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
    }
}


dependencies {

    api(Lib.Jar.gson)
    api(Lib.Kotlin.kotlinSerial)
    api(Lib.Jar.utilCodex)

    api(Lib.Kotlin.coroutinesAndroid)

    androidTestImplementation(Lib.Test.junit)
    androidTestImplementation(Lib.Test.espressoCore)
}
