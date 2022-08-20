package com.dongchao.core.lib.log

import android.util.Log
import com.dongchao.core.lib.isDebug
import com.dongchao.core.lib.iLog

object AppLog {

    val logImp: LogImp by lazy { debugLog }

    private val debugLog: LogImp = object : LogImp {
        override fun v(tag: String, msg: String) {
            if (isDebug)
                logImp?.v(tag, msg)
        }

        override fun i(tag: String, msg: String) {
            if (isDebug)
                logImp?.i(tag, msg)
        }

        override fun w(tag: String, msg: String) {
            if (isDebug)
                logImp?.w(tag, msg)
        }

        override fun d(tag: String, msg: String) {
            if (isDebug)
                logImp?.d(tag, msg)
        }

        override fun e(tag: String, msg: String) {
            if (isDebug)
                logImp?.e(tag, msg)
        }

        override fun printErrStackTrace(tag: String, msg: String, tr: Throwable) {
            if (isDebug)
                logImp?.v(tag, "$msg  ${Log.getStackTraceString(tr)}")
        }
    }

    fun v(tag: String, msg: String) = debugLog?.v(tag, msg)
    fun i(tag: String, msg: String) = debugLog?.i(tag, msg)
    fun w(tag: String, msg: String) = debugLog?.w(tag, msg)
    fun d(tag: String, msg: String) = debugLog?.d(tag, msg)
    fun e(tag: String, msg: String) = debugLog?.e(tag, msg)
    fun printErrStackTrace(tag: String, msg: String, tr: Throwable) =
        debugLog?.printErrStackTrace(tag, msg, tr)

    interface LogImp {
        fun v(tag: String, msg: String)
        fun i(tag: String, msg: String)
        fun w(tag: String, msg: String)
        fun d(tag: String, msg: String)
        fun e(tag: String, msg: String)
        fun printErrStackTrace(tag: String, msg: String, tr: Throwable)
    }
}