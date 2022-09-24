package com.dongchao.core.lib.extensions

import android.app.Activity
import android.os.Build

import androidx.annotation.*

fun Activity.getMyColor(@ColorRes color: Int): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        resources.getColor(color, theme)
    } else {
        resources.getColor(color)
    }
}


