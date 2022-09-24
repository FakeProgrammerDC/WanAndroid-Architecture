package com.dongchao.home

import com.dongchao.common.utils.eLog
import com.dongchao.core.lib.base.*

import com.dongchao.core.network.extensions.requestGet
import com.dongchao.home.list.Article

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import kotlin.system.measureTimeMillis


data class HomeViewEffect(val errorToastEffect: String) : UiEffect


@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel<DefaultUiState, HomeViewEffect>() {


    override fun createInitialState() = DefaultUiState(isLoading = false)

//    fun bannerCompletion() {
//        setState {
//            "bannerCompletion ${toString()}".iClassTagLog<HomeViewModel>()
//            copy(bannerCompletion = true)
//        }
//    }
//
//    fun listCompletion() {
//        setState {
//            "listCompletion ${toString()}".iClassTagLog<HomeViewModel>()
//            copy(listCompletion = true)
//        }
//    }


    fun fibonacci(): Flow<Int> = flow {
        // 验证码倒计时
        (0..10).toList().forEach { element ->
            emit(element)
            delay(1000L)
        }
    }

    fun testNet() {
        //setState { copy(isLoading = true) }
        launch {

            repeat(5){
                sendEffect(HomeViewEffect("it = $it"))
            }

            val time = measureTimeMillis {
                val list = mutableListOf<Deferred<*>>()
                repeat(1) {
                    // context = Dispatchers.Unconfined
                    val job = async(context = Dispatchers.Unconfined, start = CoroutineStart.LAZY) {
                        "请求 job".eLog()
                        var response = iHttpProcessor.requestGet<Article>(
                            "article/list/0/json",
                        )
                    }
                    val job2 =
                        async(context = Dispatchers.Unconfined, start = CoroutineStart.LAZY) {
                            "请求 job2".eLog()
//                            var responseBanners = iHttpProcessor.requestGet<List<Banner>>(
//                                "banner/json",
//                            )
                        }

                    list.add(job)
                    list.add(job2)
                }

                list.forEach {
                    it.start()
                }
                list.forEach {
                    it.await()
                }

//                var response = iHttpProcessor.requestGet<Article>(
//                    "article/list/0/json",
//                )
//
//                var response2 = iHttpProcessor.requestGet<Article>(
//                    "article/list/0/json",
//                )

            }

            "耗时 $time".eLog("time")

        }
    }
}