package com.dongchao.wanandroid

import android.content.Context
import android.util.Log
import android.widget.Toast
import java.math.MathContext

fun String.showToast(context: Context) {
    Toast.makeText(
        context, this, Toast.LENGTH_LONG
    ).show()
}

