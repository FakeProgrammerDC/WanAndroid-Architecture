object Versions {

    // Config
    const val compileSdk = 32
    const val applicationId = "com.dongchao.wanandroid"
    const val minSdk = 23
    const val targetSdk = 32
    const val versionCode = 1
    const val versionName = "0.0.1" // X.Y.Z; X = Major, Y = minor, Z = Patch level

    // gradle plugin
    const val gradlePlugin = "7.2.1"

    // kotlin
    const val kotlin = "1.7.10"
    const val kotlinxCoroutines = "1.6.3"

    // androidx
    const val androidxAppCompat = "1.4.2"
    const val androidxConstraintLayout = "2.0.4"
    const val androidxCore = "1.8.0"
    const val androidxHilt = "1.0.0"
    //    implementation 'androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha01'

    // jetpack
    const val hilt = "2.42"
    const val viewModelAndLiveData = "2.4.1"

    // network
    const val okHttp = "4.10.0"
    const val retrofit = "2.9.0"
    const val stetho = "1.5.0"

    // test
    const val androidxTest = "1.4.0"
    const val androidxTestExt = "1.1.3"
    const val androidxEspresso = "3.4.0"

//    const val = "${}"
//    const val = "${}"
//    const val = "${}"
//    const val = "${}"
}

object Lib {

    const val gson = "com.google.code.gson:gson:2.9.0"
    const val material = "com.google.android.material:material:1.4.0"
    const val utilCodex = "com.blankj:utilcodex:1.31.0"

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinxCoroutines}"
        const val coroutinesTest =
            "org.jetbrains.kotlin:kotlinx-coroutines-test:${Versions.kotlinxCoroutines}"
        const val kotlinSerial =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3"
    }

    object Androidx {
        const val coreKtx = "androidx.core:core-ktx:${Versions.androidxCore}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.androidxAppCompat}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.androidxConstraintLayout}"

        // Hilt 和 Jetpack 集成
        const val kaptHiltCompiler =
            "androidx.hilt:hilt-compiler:${Versions.androidxHilt}"
    }

    object Jetpack {
        const val hiltAndroid =
            "com.google.dagger:hilt-android:${Versions.hilt}"
        const val kaptHiltAndroidCompiler =
            "com.google.dagger:hilt-android-compiler:${Versions.hilt}"

        const val viewModelKtx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelAndLiveData}"
        const val liveDataKtx =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.viewModelAndLiveData}"
        const val runtimeKtx =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.viewModelAndLiveData}"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitGsonConverter =
            "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

        //调试网络请求
        const val stetho = "com.facebook.stetho:stetho:${Versions.stetho}"
        const val stethoOkhttp = "com.facebook.stetho:stetho-okhttp3:${Versions.stetho}"
    }

    object Test {
        const val core = "androidx.test:core:${Versions.androidxTest}"
        const val runner = "androidx.test:runner:${Versions.androidxTest}"
        const val rules = "androidx.test:rules:${Versions.androidxTest}"
        const val junit = "androidx.test.ext:junit-ktx:${Versions.androidxTestExt}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.androidxEspresso}"
    }
}

object Plugins {
    const val gradlePlugin = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val kotlinSerializationPlugin =
        "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
    const val hiltPlugin =
        "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
}