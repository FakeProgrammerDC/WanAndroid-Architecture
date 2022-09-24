package com.dongchao.home.banner

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dongchao.common.utils.eLog
import com.dongchao.common.utils.iClassTagLog
import com.dongchao.core.lib.base.BaseFragment
import com.dongchao.core.lib.base.delegates.getFragmentViewModels
import com.dongchao.core.lib.base.delegates.launchStarted
import com.dongchao.core.lib.base.delegates.viewModels
import com.dongchao.home.HomeViewModel
import com.dongchao.home.R
import com.dongchao.home.databinding.FragemntViewBannerBinding

import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BannerFragment :
    BaseFragment<FragemntViewBannerBinding, BannerViewModel>(
        R.layout.fragemnt_view_banner,
        FragemntViewBannerBinding::bind
    ) {

    private val bannerAdapter: BannerImageAdapter<Banner> by lazy {
        getHomeBannerAdapter()
    }

    //private val mainViewModel: MainViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by viewModels(HomeViewModel::class.java.kotlin,
        { this.requireParentFragment() })


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        "homeViewModel = ${homeViewModel.hashCode()}".iClassTagLog<BannerFragment>()

        //val homeViewModel = parentFragment?.getFragmentViewModels(HomeViewModel::class.java)

        with(viewBinding) {
            homeBanner.addBannerLifecycleObserver(requireActivity())
                .setAdapter(bannerAdapter).indicator =
                CircleIndicator(requireActivity())
        }

        launchStarted {
            viewModel.uiState.collect { state ->
                if (state.data.isNotEmpty()) {
                    bannerAdapter.setDatas(state.data)
                }
            }
        }
    }

    private fun getHomeBannerAdapter() = object : BannerImageAdapter<Banner>(emptyList()) {
        override fun onBindView(
            holder: BannerImageHolder?,
            data: Banner?,
            position: Int,
            size: Int
        ) {
            holder?.imageView?.let {
                Glide.with(holder.itemView)
                    .load(data?.imagePath)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                    .into(it)
            }
            holder?.imageView?.setOnClickListener {
//                val homeViewModel = parentFragment?.getFragmentViewModels(HomeViewModel::class.java)
//                "HomeFragment = ${parentFragment.hashCode()}".eLog("homeViewModel")
//                "homeViewModel = ${homeViewModel.hashCode()}".eLog("homeViewModel")

//                requireActivity().startActivity(
//                    Intent(
//                        requireActivity(),
//                        //WebViewActivity::class.java
//                    )
//                )
            }
        }
    }
}