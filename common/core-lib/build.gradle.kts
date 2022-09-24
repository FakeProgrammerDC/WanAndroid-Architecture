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
        // 代码生成路径 app/build/generated/data_binding_base_class_source_out/debug/out/
        viewBinding = true
    }
}


dependencies {

    api(Lib.Androidx.material)
    api(Lib.Androidx.coreKtx)
    api(Lib.Androidx.appcompat)
    api(Lib.Androidx.constraintLayout)
    api(Lib.Androidx.activityKtx)
    api(Lib.Androidx.fragmentKtx)

    api(Lib.Jetpack.viewModelKtx)
    api(Lib.Jetpack.liveDataKtx)
    api(Lib.Jetpack.runtimeKtx)

    api(Lib.Jar.viewBindingPropertyDelegate)

    api(Lib.WebView.agentWebCore)
    api(Lib.WebView.agentWebFilechooser)
    api(Lib.WebView.jsbridge)

//    api(Lib.Router.aRouter){
//        exclude("com.android.support","support-compat")
//        exclude("com.android.support","loader")
//        exclude("com.android.support","versionedparcelable")
//    }

//    api(Lib.Jar.androidStartup)
//
//    api(Lib.Jar.skeleton)
//    api(Lib.Jar.shimmerLayout)

//    api(Lib.Refresh.layoutKernel)
//    api(Lib.Refresh.headerClassics)
//    api(Lib.Refresh.footerClassics)

    api(Lib.UI.baseRecyclerViewAdapterHelper)
    api(Lib.UI.banner)

    api(Lib.ImageLoad.glide)
    kapt(Lib.ImageLoad.glideCompilerKapt)

    implementation(Lib.Jetpack.hiltAndroid)
    kapt(Lib.Jetpack.kaptHiltCompiler)
    kapt(Lib.Jetpack.kaptHiltAndroidCompiler)

    api(project(mapOf("path" to ":common:core-network")))
    api(project(mapOf("path" to ":common:core-utils")))

    androidTestImplementation(Lib.Test.junit)
    androidTestImplementation(Lib.Test.espressoCore)
}
