package com.dongchao.core.lib

import com.dongchao.core.lib.log.AppLog

/** log **/
inline fun <reified T> Any.iClassTagLog() {
    AppLog.logImp.i(T::class.java.name, "$this")
}

inline fun <reified T> Any.eClassTagLog() {
    AppLog.logImp.e(T::class.java.name, "$this")
}

fun <T> T.iLog(tag: String = "") {
    var currentTag = tag
    if (currentTag == "") {
        currentTag = appTag
    }
    AppLog.logImp.i(currentTag, "$this")
}

fun <T> T.eLog(tag: String = "") {
    var currentTag = tag
    if (currentTag == "") {
        currentTag = appTag
    }
    AppLog.logImp.e(currentTag, "$this")
}
