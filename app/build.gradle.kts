plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = Versions.applicationId
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packagingOptions{
        exclude("META-INF/gradle/incremental.annotation.processors")
    }
}

dependencies {

    implementation(Lib.Jetpack.hiltAndroid)
    kapt(Lib.Jetpack.kaptHiltAndroidCompiler)

    implementation(project(mapOf("path" to ":common:core-lib")))
    implementation(project(mapOf("path" to ":common:core-network")))
    androidTestImplementation(Lib.Test.junit)
    androidTestImplementation(Lib.Test.espressoCore)
}