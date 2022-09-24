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
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }

    packagingOptions {
        exclude("META-INF/gradle/incremental.annotation.processors")
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

    implementation(project(mapOf("path" to ":appmodule:home")))
    implementation(project(mapOf("path" to ":appmodule:navigation")))
    implementation(project(mapOf("path" to ":appmodule:my")))

    implementation(project(mapOf("path" to ":common:core-lib")))

    androidTestImplementation(Lib.Test.junit)
    androidTestImplementation(Lib.Test.espressoCore)

}