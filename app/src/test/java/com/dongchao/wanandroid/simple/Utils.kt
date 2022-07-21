package com.dongchao.wanandroid.simple

import android.util.Log

fun getName() = "dong chao"

fun String.vLog(tag: String, msg: String) {
    Log.v(tag, msg)
}