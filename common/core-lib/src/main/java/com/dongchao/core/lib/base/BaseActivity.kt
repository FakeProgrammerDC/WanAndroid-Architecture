package com.dongchao.core.lib.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.viewbinding.ViewBinding
import by.kirich1409.viewbindingdelegate.ViewBindingProperty
import by.kirich1409.viewbindingdelegate.internal.emptyVbCallback
import by.kirich1409.viewbindingdelegate.internal.findRootView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dongchao.core.lib.R
import com.dongchao.core.lib.base.delegates.viewModel
import com.dongchao.core.lib.base.interfaces.BaseActivityCallback
import com.dongchao.core.lib.extensions.getMyColor
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

/**
 *    @author : dong.chao
 *    @time   : 2022/8/28
 *    @desc   : 基础BaseActivity
 */
abstract class BaseActivity<VB : ViewBinding, VM : ViewModel> constructor(
    private val layoutId: Int,
    viewBindingFactory: (View) -> VB
) : AppCompatActivity(), BaseActivityCallback {

    abstract fun initObserver()

    /** viewBinding  */
    val viewBinding by viewBinding(viewBindingFactory)

    /** viewModel */
    val viewModel by viewModel(getViewModelClass().kotlin)

    private fun getViewModelClass() =
        (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>

//    /** 基础 TitleLayout */
//    protected var mBasicTitleLayoutBinding: BasicTitleLayoutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        initObserver()

        supportActionBar?.hide()

    }

    override fun getTitleBgColor() = getMyColor(R.color.white)

    override fun onBarBack() = false

    override fun getTitleText(): String = title.toString()

    override fun getTitleRightView(): View? = null

    override fun onTitleRightClick() {
    }

}

//@JvmName("viewBindingActivity")
//public inline fun <A : ComponentActivity, T : ViewBinding> ComponentActivity.viewBinding2(
//    crossinline vbFactory: (View) -> T,
//    crossinline viewProvider: (A) -> View = ::findRootView
//): ViewBindingProperty<A, T> {
//    return viewBinding(emptyVbCallback(), vbFactory, viewProvider)
//}