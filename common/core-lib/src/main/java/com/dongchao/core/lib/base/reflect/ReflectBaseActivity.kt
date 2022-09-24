package com.dongchao.core.lib.base.reflect

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.dongchao.common.utils.eLog
import com.dongchao.core.lib.R
import com.dongchao.core.lib.base.BasicActivity
import com.dongchao.core.lib.base.delegates.BaseActivityDelegate
import com.dongchao.core.lib.base.interfaces.BaseActivityCallback
import com.dongchao.core.lib.extensions.getMyColor


/**
 *    @author : dong.chao
 *    @time   : 2022/8/27
 *    @desc   : 使用反射的基础 Activity
 */
open class ReflectBaseActivity<VB : ViewBinding, VM : ViewModel> : BasicActivity(), BaseActivityCallback {

    /** viewBinding  */
    protected lateinit var mViewBinding: VB

    /** viewModel */
    protected lateinit var mViewModel: VM

    private val baseActivityDelegate by lazy { BaseActivityDelegate.create(this, this) }

//    /** 基础 TitleLayout */
//    protected var mBasicTitleLayoutBinding: BasicTitleLayoutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseActivityDelegate.initTitleLayout()
        mViewBinding = baseActivityDelegate.initViewBinding()
        mViewModel = baseActivityDelegate.initViewModel()
        mViewBinding.hashCode().eLog("ReflectBaseActivity")
        mViewModel.hashCode().eLog("ReflectBaseActivity")
    }

    override fun getTitleBgColor() = getMyColor(R.color.white)

    override fun onBarBack() = false

    override fun getTitleText(): String = title.toString()

    override fun getTitleRightView(): View? = null

    override fun onTitleRightClick() {
    }

}