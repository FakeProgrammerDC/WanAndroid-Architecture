package com.dongchao.core.lib.base

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.blankj.utilcode.util.ActivityUtils
import com.dongchao.common.utils.iLog
import com.dongchao.core.lib.base.delegates.viewModel
import com.dongchao.core.lib.base.delegates.viewModels
import java.lang.reflect.ParameterizedType

/**
 *    @author : dong.chao
 *    @time   : 2022/9/3
 *    @desc   : 基础 LinearLayout
 */
abstract class BaseConstraintLayout<VB : ViewBinding, VM : ViewModel> constructor(
    context: Context,
    attrs: AttributeSet
) :
    ConstraintLayout(context, attrs) {

    abstract fun getLayoutId(): Int

    abstract fun getViewBindingFactory(): (View) -> VB

    protected val componentActivity: ComponentActivity

    init {
        getContext().javaClass.iLog("BaseConstraintLayout")
        // 因为使用 hilt 的原因所以 Context 是 GeneratedComponentManager
        componentActivity = ActivityUtils.getTopActivity() as ComponentActivity
        inflate(context, getLayoutId(), this)
    }

    /** viewBinding  */
    val viewBinding by viewBinding(getViewBindingFactory())

    /** viewModel */
    val viewModel by componentActivity.viewModel(getViewModelClass().kotlin)

    private fun getViewModelClass() =
        (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>

}

