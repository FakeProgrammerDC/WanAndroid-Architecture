package com.dongchao.my

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.dongchao.core.lib.base.BaseFragment
import com.dongchao.my.databinding.FragmentMyBinding
import com.dongchao.my.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyFragment : BaseFragment<FragmentMyBinding, MyViewModel>(
    R.layout.fragment_my,
    FragmentMyBinding::bind
) {

    // 获取 Activity ViewModel
    // private val mainViewModel: MainViewModel by activityViewModels()

    // 获取父 Fragment ViewModel
    // private val homeViewModel: HomeViewModel by viewModels(HomeViewModel::class.java.kotlin,{ this.requireParentFragment() })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.apply {
            myTv.setOnClickListener {
                startActivity(Intent(requireActivity(), LoginActivity::class.java))
            }
        }
    }

    companion object {
        fun newInstance() = MyFragment()
    }
}