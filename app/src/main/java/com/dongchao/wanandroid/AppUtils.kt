package com.dongchao.wanandroid

import android.util.Log

fun String.vLog(tag: String = "App") {
    Log.v(tag, this)
}