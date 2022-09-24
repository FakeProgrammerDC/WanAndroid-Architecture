package com.dongchao.navigation

import android.os.Bundle
import android.view.View
import com.dongchao.core.lib.base.BaseFragment
import com.dongchao.core.lib.router.ARouterImpl
import com.dongchao.navigation.databinding.FragmentNavigationBinding
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class NavigationFragment : BaseFragment<FragmentNavigationBinding, NavigationViewModel>(
    R.layout.fragment_navigation,
    FragmentNavigationBinding::bind
) {

    // 获取 Activity ViewModel
    // private val mainViewModel: MainViewModel by activityViewModels()

    // 获取父 Fragment ViewModel
    // private val homeViewModel: HomeViewModel by viewModels(HomeViewModel::class.java.kotlin,{ this.requireParentFragment() })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.apply {
            navigationTv.setOnClickListener {
                ARouterImpl.toActivityWebView()
            }
        }
    }

    companion object {
        fun newInstance() = NavigationFragment()
    }
}