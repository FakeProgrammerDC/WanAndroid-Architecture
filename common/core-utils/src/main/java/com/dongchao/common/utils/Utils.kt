package com.dongchao.common.utils

import android.os.Looper
import com.dongchao.common.utils.log.AppLog
import com.dongchao.common.utils.constants.APP_TAG

/** log **/
inline fun <reified T> Any.iClassTagLog() {
    AppLog.logImp.i(T::class.java.simpleName, "[${Thread.currentThread().name}] $this")
}

inline fun <reified T> Any.eClassTagLog() {
    AppLog.logImp.e(T::class.java.simpleName, "[${Thread.currentThread().name}] $this")
}

fun <T> T.iLog(tag: String = "") {
    var currentTag = tag
    if (currentTag == "") {
        currentTag = APP_TAG
    }
    AppLog.logImp.i(currentTag, "[${Thread.currentThread().name}] $this")
}

fun <T> T.eLog(tag: String = "") {
    var currentTag = tag
    if (currentTag == "") {
        currentTag = APP_TAG
    }
    AppLog.logImp.e(currentTag, "[${Thread.currentThread().name}] $this")
}

fun Throwable.printErrStackTrace(tag: String = "", msg: String = "") {
    var currentTag = tag
    if (currentTag == "") {
        currentTag = APP_TAG
    }
    AppLog.logImp.printErrStackTrace(currentTag, "[${Thread.currentThread().name}] $msg", this)
}


public fun <T> emptyMutableList(): MutableList<T> = ArrayList()


public fun checkMainThread() {
    check(Looper.getMainLooper() === Looper.myLooper()) {
        "必须在主线程调用"
    }
}
