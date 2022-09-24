package com.dongchao.home.banner

import com.dongchao.common.utils.eLog
import com.dongchao.core.lib.base.*
import com.dongchao.core.network.bean.NetworkResponse
import com.dongchao.core.network.extensions.requestGet
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class BannerState(
    override val isLoading: Boolean = false,
    override val data: List<Banner> = emptyList(),
) :
    BaseUiState<List<Banner>>()

@HiltViewModel
class BannerViewModel @Inject constructor() : BaseViewModel<BannerState, DefaultUiEffect>() {

    override fun createInitialState() = BannerState()

    init {
        "HomeBannerViewModel init ".eLog()
        getHomeBanner()
    }


    fun getHomeBanner() = launch {
        "HomeBannerViewModel getHomeBanner ".eLog()

//        setState {
//            copy(isLoading = true)
//        }

        // https://www.wanandroid.com/banner/json
        var responseBanners = iHttpProcessor.requestGet<List<Banner>>(
            "banner/json",
        )

        if (responseBanners is NetworkResponse.Success) {
            setState {
                "HomeBannerViewModel getHomeBanner Success".eLog()
                copy(data = responseBanners.data)
            }
        }

//        setState {
//            copy(isLoading = false, data = responseBanners)
//        }
    }

}