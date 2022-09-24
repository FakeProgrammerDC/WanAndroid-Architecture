package com.dongchao.wanandroid.copy

import com.dongchao.core.lib.base.BaseFragment
import com.dongchao.wanandroid.R

import com.dongchao.wanandroid.databinding.FragmentCopyBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 *    @author : dong.chao
 *    @time   : 2022/9/24
 *    @desc   : Fragment 模版类用于复制
 */
@AndroidEntryPoint
class CopyFragment : BaseFragment<FragmentCopyBinding, CopyViewModel>(
    R.layout.fragment_copy,
    FragmentCopyBinding::bind
) {

    // 获取 Activity ViewModel
    // private val mainViewModel: MainViewModel by activityViewModels()

    // 获取父 Fragment ViewModel
    // private val homeViewModel: HomeViewModel by viewModels(HomeViewModel::class.java.kotlin,{ this.requireParentFragment() })

    companion object {
        fun newInstance() = CopyFragment()
    }
}