package com.dongchao.core.lib.base

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.LayoutRes

/**
 *    @author : dong.chao
 *    @time   : 2022/9/4
 *    @desc   : 直接添加到根布局 decorView 的快捷辅助工具
 */
@Suppress("MemberVisibilityCanBePrivate")
open class BaseQuickDecorWindow(activity: Activity, @LayoutRes layoutResId: Int, convert: Convert? = null) {
    var rootView: View
    var outsideTouchCancelable = true

    init {
        val decorView = activity.window.decorView as FrameLayout
        rootView = LayoutInflater.from(activity).inflate(layoutResId, decorView, false)
        with(rootView) {
            visibility = View.GONE
            setOnClickListener { if (outsideTouchCancelable) hide() }
            decorView.addView(this)
            convert?.convert(this)
        }
    }

    open fun show() {
        rootView.visibility = View.VISIBLE
    }

    open fun hide() {
        rootView.visibility = View.INVISIBLE
    }

    fun isShowing(): Boolean = rootView.visibility == View.VISIBLE

    interface Convert {
        fun convert(view: View)
    }
}