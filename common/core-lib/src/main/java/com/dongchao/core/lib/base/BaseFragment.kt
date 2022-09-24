package com.dongchao.core.lib.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dongchao.core.lib.base.delegates.viewModels
import com.dongchao.core.lib.widget.AppLoading
import java.lang.reflect.ParameterizedType

/**
 *    @author : dong.chao
 *    @time   : 2022/9/3
 *    @desc   : Fragment 基础类
 */
abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> constructor(
    layoutId: Int,
    viewBindingFactory: (View) -> VB
) : Fragment(layoutId) {

    open fun fragmentViewDestroyed(vb: VB) {}

    /** viewBinding  */
    val viewBinding by viewBinding(viewBindingFactory,
        onViewDestroyed = { vb: VB ->
            // reset view
            fragmentViewDestroyed(vb)
        })

    /** viewModel */
    val viewModel by viewModels(getViewModelClass().kotlin)

    private fun getViewModelClass() =
        (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
     * ---------------------------------------------------------------------------------------------
     * ---------------------------------------------------------------------------------------------
     * 加载框AppLoading
     * ---------------------------------------------------------------------------------------------
     * ---------------------------------------------------------------------------------------------
     */
    private var appLoading: AppLoading? = null

    fun showAppLoading(text: String? = "正在加载") {
        if (appLoading == null) appLoading = AppLoading(requireActivity())
        appLoading!!.show(text)
    }

    fun closeAppLoading() {
        appLoading?.hide()
    }

    fun showLockedAppLoading(text: String) {
        appLoading?.isLocked = false
        showAppLoading(text)
        appLoading?.isLocked = true
    }

    fun closeLockedApploading() {
        appLoading?.isLocked = false
        closeAppLoading()
    }
}