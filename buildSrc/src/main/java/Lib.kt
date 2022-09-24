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
    const val androidxActivityKtx = "1.5.1"
    const val androidxMaterial = "1.4.0"

    // jetpack
    const val hilt = "2.42"
    const val lifecycle = "2.4.1"

    // network
    const val okHttp = "4.10.0"
    const val retrofit = "2.9.0"
    const val stetho = "1.5.0"

    // refresh
    const val refresh = "2.0.5"

    // glide
    const val glide = "4.13.2"

    // agentWeb
    const val agentWeb = "v5.0.6"

    // aRouter
    const val aRouter = "1.5.2"

    // test
    const val androidxTest = "1.4.0"
    const val androidxTestExt = "1.1.3"
    const val androidxEspresso = "3.4.0"

}

/**
 *    @author : dong.chao
 *    @time   : 2022/9/4
 *    @desc   : 注意: 如果只是改了字符串内容可能无法识别到变化,不会重新下载库，必须 clean
 */
object Lib {

    object Jar {
        // gson 格式化
        const val gson = "com.google.code.gson:gson:2.9.0"

        // utilCodex 常用 util: https://github.com/Blankj/AndroidUtilCode/blob/master/lib/utilcode/README-CN.md
        const val utilCodex = "com.blankj:utilcodex:1.31.0"

        // viewBinding 内存泄漏处理: https://github.com/androidbroadcast/ViewBindingPropertyDelegate
        const val viewBindingPropertyDelegate =
            "com.github.kirich1409:viewbindingpropertydelegate-noreflection:1.5.6"

        // skeleton 骨架: https://github.com/ethanhua/Skeleton
        const val skeleton = "com.ethanhua:skeleton:1.1.2"
        const val shimmerLayout = "io.supercharge:shimmerlayout:2.1.0"

        // startup 启动框架: https://github.com/idisfkj/android-startup
        const val androidStartup = "io.github.idisfkj:android-startup:1.1.0"
    }

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
        const val activityKtx = "androidx.activity:activity-ktx:${Versions.androidxActivityKtx}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.androidxCore}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.androidxAppCompat}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.androidxConstraintLayout}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.androidxActivityKtx}"
        const val material = "com.google.android.material:material:${Versions.androidxMaterial}"
    }

    object Jetpack {
        // 代码生成路径 app/build/generated/source/kapt/debug
        const val hiltAndroid =
            "com.google.dagger:hilt-android:${Versions.hilt}"
        const val kaptHiltAndroidCompiler =
            "com.google.dagger:hilt-android-compiler:${Versions.hilt}"

        // Hilt 和 Jetpack 集成
        const val kaptHiltCompiler =
            "androidx.hilt:hilt-compiler:${Versions.androidxHilt}"

        const val viewModelKtx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val liveDataKtx =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val runtimeKtx =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"

        // ViewModel 的状态保存
        const val viewModelSavedState =
            "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"

        // ViewModel hilt 扩展
        const val hiltLifecycleViewModel =
            "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha01"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitGsonConverter =
            "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

        // okhttp: https://github.com/square/okhttp
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"

        // 测试 HTTP
        const val mockWebServerTest = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp}"
        const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

        // stetho 调试网络请求:
        const val stetho = "com.facebook.stetho:stetho:${Versions.stetho}"
        const val stethoOkhttp = "com.facebook.stetho:stetho-okhttp3:${Versions.stetho}"
    }

    object Refresh {
        // 核心必须依赖: https://github.com/scwang90/SmartRefreshLayout
        const val layoutKernel = "com.scwang.smart:refresh-layout-kernel:${Versions.refresh}"
        const val headerClassics = "com.scwang.smart:refresh-header-classics:${Versions.refresh}"
        const val footerClassics = "com.scwang.smart:refresh-footer-classics:${Versions.refresh}"
    }

    object UI {
        // 轮播图： https://github.com/youth5201314/banner
        const val banner = "io.github.youth5201314:banner:2.2.2"

        // RecyclerView 适配器：https://github.com/CymChad/BaseRecyclerViewAdapterHelper/blob/master/readme/0-BaseRecyclerViewAdapterHelper.md
        // const val baseRecyclerViewAdapterHelper =
        //    "io.github.cymchad:BaseRecyclerViewAdapterHelper:4.0.0-beta02"
        const val baseRecyclerViewAdapterHelper =
            "com.github.CymChad:BaseRecyclerViewAdapterHelper:3.0.8"
    }

    // matrix https://github.com/Tencent/matrix
    object Apm {

        // blockcanary https://github.com/markzhai/AndroidPerformanceMonitor
        // debugCompile 'com.github.markzhai:blockcanary-android:1.5.0'
        // releaseCompile 'com.github.markzhai:blockcanary-no-op:1.5.0'

        // BlockCanaryEx https://github.com/seiginonakama/BlockCanaryEx/blob/master/README_ZH.md
        // debugCompile 'com.letv.sarrsdesktop:BlockCanaryExJRT:0.9.9.4'
        // releaseCompile 'com.letv.sarrsdesktop:BlockCanaryExJRTNoOp:0.9.9.4'
        // classpath 'com.letv.sarrsdesktop:BlockCanaryExPlugin:0.9.9.5'

        // leakcanary https://square.github.io/leakcanary/getting_started/
        // debugImplementation 'com.squareup.leakcanary:leakcanary-android:2.9.1'

        // anrwatchdog https://github.com/SalomonBrys/ANR-WatchDog
        // implementation 'com.github.anrwatchdog:anrwatchdog:1.4.0'
    }

    object WebView {
        // agentWeb 加载 H5 库: https://github.com/Justson/AgentWeb/wiki/AgentWeb-%E5%9F%BA%E7%A1%80%E4%BD%BF%E7%94%A8
        const val agentWebCore =
            "com.github.Justson.AgentWeb:agentweb-core:${Versions.agentWeb}-androidx"

        // (可选)
        const val agentWebFilechooser =
            "com.github.Justson.AgentWeb:agentweb-filechooser:${Versions.agentWeb}-androidx"
        const val agentWebDownloader = "com.github.Justson:Downloader:${Versions.agentWeb}-androidx"

        // jsBridge android 和 js 的桥接: https://github.com/lzyzsd/JsBridge
        const val jsbridge = "com.github.lzyzsd:jsbridge:1.0.4"
    }

    object ImageLoad {
        // Glide 图片加载： https://github.com/bumptech/glide
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glideCompilerKapt = "com.github.bumptech.glide:compiler:${Versions.glide}"
    }

    object Router {
        // ARouter 阿里路由: https://github.com/alibaba/ARouter/blob/master/README_CN.md
//        const val aRouter = "com.alibaba:arouter-api:${Versions.aRouter}"
//        const val aRouterKapt = "com.alibaba:arouter-compiler:${Versions.aRouter}"
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
    // const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val kotlinSerializationPlugin =
        "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
    const val hiltPlugin =
        "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
}

