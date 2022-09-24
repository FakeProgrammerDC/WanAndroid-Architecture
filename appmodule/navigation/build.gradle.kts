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

    buildFeatures {
        viewBinding = true
    }


}

//kapt {
//    arguments {
//        arg("AROUTER_MODULE_NAME", project.name)
//    }
//}

dependencies {
    implementation(Lib.Jetpack.hiltAndroid)
    kapt(Lib.Jetpack.kaptHiltCompiler)
    kapt(Lib.Jetpack.kaptHiltAndroidCompiler)
//    kapt(Lib.Router.aRouterKapt)
    implementation(project(mapOf("path" to ":common:core-lib")))
}