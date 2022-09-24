package com.dongchao.core.lib.task

//import android.app.Application
//import android.content.Context
//import com.alibaba.android.arouter.launcher.ARouter
//import com.dongchao.common.utils.constants.debug
//import com.rousetime.android_startup.AndroidStartup

//class ARouterTask : AndroidStartup<Any>() {
//
//    override fun callCreateOnMainThread() = false
//
//    override fun create(context: Context): Any? {
//        if (debug) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
//            ARouter.openLog()     // 打印日志
//            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
//        }
//        ARouter.init(context as? Application) // 尽可能早，推荐在Application中初始化
//        return null
//    }
//
//    override fun waitOnMainThread() = false
//}