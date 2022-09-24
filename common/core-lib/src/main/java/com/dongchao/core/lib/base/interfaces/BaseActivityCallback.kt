package com.dongchao.core.lib.base.interfaces

import android.view.View
import com.dongchao.core.lib.R
import com.dongchao.core.lib.extensions.getMyColor

interface BaseActivityCallback {

    /** 获取标题默认颜色 */
    fun getTitleBgColor():Int

    /** 拦截返回 */
    fun onBarBack(): Boolean

    /** 获取标题 */
    fun getTitleText(): String

    /** 标题栏右侧布局 */
    fun getTitleRightView(): View?

    /** 标题栏右侧布局点击事件 */
    fun onTitleRightClick()
}