package com.dongchao.core.network.task

import com.blankj.utilcode.util.Utils
import com.dongchao.common.utils.task.Task
import com.facebook.stetho.Stetho

object NetworkTask : Task {
    override fun init() {
        //调试网络,查看数据库
        Stetho.initializeWithDefaults(Utils.getApp())
    }
}