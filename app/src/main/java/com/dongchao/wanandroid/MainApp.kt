package com.dongchao.wanandroid

import android.app.Application
import com.dongchao.core.network.NetworkTask
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        NetworkTask.init()
    }
}

