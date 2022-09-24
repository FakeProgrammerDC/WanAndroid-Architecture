package com.dongchao.core.lib.base.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.dongchao.core.lib.base.interfaces.BaseActivityCallback
import com.dongchao.core.lib.databinding.BasicTitleLayoutBinding
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType

abstract class BaseActivityDelegate {

    companion object {
        @JvmStatic
        open fun create(
            activity: AppCompatActivity,
            callBack: BaseActivityCallback
        ): BaseActivityDelegate {
            return ReflectBaseActivityDelegateImpl(activity, callBack)
        }
    }

    abstract fun initTitleLayout(): ViewBinding

    abstract fun <VB : ViewBinding> initViewBinding(): VB

    abstract fun <VM : ViewModel> initViewModel(): VM
}

internal class ReflectBaseActivityDelegateImpl constructor(
    private val activity: AppCompatActivity,
    private val callBack: BaseActivityCallback
) : BaseActivityDelegate() {

    /** 基础 TitleLayout */
    private var basicTitleLayoutBinding: BasicTitleLayoutBinding? = null
    private val type: ParameterizedType = activity.javaClass.genericSuperclass as ParameterizedType

    override fun initTitleLayout(): ViewBinding {
        activity.supportActionBar?.hide()

        val viewBind = BasicTitleLayoutBinding.inflate(activity.layoutInflater, null, false)
        return viewBind.apply {
            basicTitleLayoutBinding = this
            backTv.setOnClickListener { if (callBack.onBarBack()) activity.onBackPressed() }
            titleTv.text = callBack.getTitleText()
            callBack.getTitleRightView()?.let { titleRightView ->
                rightLayout.addView(titleRightView)
                titleRightView.setOnClickListener { callBack.onTitleRightClick() }
            }
            titleTopLayout.setBackgroundColor(callBack.getTitleBgColor())
        }
    }

    private fun getViewBindingClass(): Class<ViewBinding> =
        type.actualTypeArguments[0] as Class<ViewBinding>

    private fun getViewModelClass(): Class<ViewModel> =
        type.actualTypeArguments[1] as Class<ViewModel>

    override fun <VB : ViewBinding> initViewBinding(): VB {
        var viewBinding: VB
        val inflate: Method = getViewBindingClass()
            .getDeclaredMethod(
                "inflate", LayoutInflater::class.java,
                ViewGroup::class.java,
                Boolean::class.java
            )
        if (basicTitleLayoutBinding == null) {
            viewBinding = inflate.invoke(null, activity.layoutInflater, null, false) as VB
            activity.setContentView(viewBinding.root)
        } else {
            viewBinding =
                inflate.invoke(
                    null,
                    activity.layoutInflater,
                    basicTitleLayoutBinding!!.root,
                    true
                ) as VB
            activity.setContentView(basicTitleLayoutBinding!!.root)
        }
        return viewBinding
    }

    override fun <VM : ViewModel> initViewModel(): VM {
        return ViewModelProvider(activity)[getViewModelClass()] as VM
    }

}