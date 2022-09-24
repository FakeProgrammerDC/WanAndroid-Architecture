package com.dongchao.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.dongchao.common.utils.checkMainThread
import com.dongchao.common.utils.eLog
import com.dongchao.common.utils.iClassTagLog
import com.dongchao.core.lib.base.BaseFragment
import com.dongchao.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

/**
 *    @author : dong.chao
 *    @time   : 2022/9/3
 *    @desc   : 首页
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    R.layout.fragment_home,
    FragmentHomeBinding::bind
) {

    //    private val mainViewModel: MainViewModel by activityViewModels()
    companion object {
        fun newInstance() = HomeFragment()
    }

    @Volatile
    private var isNext: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        "homeViewModel = ${viewModel.hashCode()}".iClassTagLog<HomeFragment>()

        with(viewBinding) {
            titleTv.text = "wdwadawdada"
            titleTv.setOnClickListener {
                viewModel.testNet()
                isNext = true
                //homeListViewModel.getHomeArticle(0)
//                lifecycleScope.launch {
//                    viewModel.fibonacci().onCompletion {
//                        "验证码 完成".eLog()
//                     }.collect {
//                         "验证码 $it".eLog()
//                     }
//                }
            }

            //getParentFragmentManager.findFragmentByTag("")

            //给列表添加头 ,刷新针对所有view

            lifecycleScope.launchWhenStarted {
//                viewBinding.homeBannerLayoutView.viewModel.uiState.collect { state ->
//                    if (state.data.isNotEmpty()) {
//                        "收到 homeBannerLayoutView.viewModel.uiState".iClassTagLog<HomeFragment>()
//                        //viewModel.bannerCompletion()
//                    }
//                }
                //viewBinding.homeBannerFragment

                viewModel.effect.collect {
                    checkMainThread()
                    titleTv.text = " 通道过来数据 ${it.errorToastEffect}"
                    isNext = false
                    while (!isNext) {
                        delay(100)
                    }
                }
            }

//            lifecycleScope.launchWhenStarted {
//                viewBinding.homeListLayoutView.viewModel.uiState.collect { state ->
//                    if (state.data.isNotEmpty()) {
//                        "收到 homeListLayoutView.viewModel.uiState".iClassTagLog<HomeFragment>()
//                        //viewModel.listCompletion()
//                    }
//                }
//            }

            lifecycleScope.launchWhenStarted {
                viewModel.uiState.collect { state ->
                    "viewModel.uiState.collect".iClassTagLog<HomeFragment>()
                    if (state.isLoading) {
                        "state.isLoading = true".iClassTagLog<HomeFragment>()
                        showAppLoading()
                    } else {
                        "state.isLoading = false".iClassTagLog<HomeFragment>()
                        closeAppLoading()
                    }

                    //state.data.datas.size

//                    if (state.bannerCompletion && state.listCompletion) {
//                        "加载完成".iClassTagLog<HomeFragment>()
//                        closeAppLoading()
//                    }
                }
            }

        }
    }
}

