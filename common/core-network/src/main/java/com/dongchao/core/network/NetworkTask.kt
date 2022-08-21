package com.dongchao.core.network

import com.blankj.utilcode.util.Utils
import com.dongchao.core.lib.Task
import com.facebook.stetho.Stetho

object NetworkTask : Task {
    override fun init() {
        //调试网络,查看数据库
        Stetho.initializeWithDefaults(Utils.getApp())
    }
}