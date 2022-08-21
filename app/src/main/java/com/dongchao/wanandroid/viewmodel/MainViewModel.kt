package com.dongchao.wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dongchao.core.network.annotation.BindRetrofit
import com.dongchao.core.lib.eClassTagLog
import com.dongchao.core.lib.eLog
import com.dongchao.core.lib.iLog
import com.dongchao.core.network.IHttpProcessor
import com.dongchao.core.network.RetrofitProcessor
import com.dongchao.core.network.annotation.BindOkHttp
import com.dongchao.core.network.bean.User
import com.dongchao.core.network.exception.NetworkException
import com.dongchao.wanandroid.bean.Banner
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @BindRetrofit var iHttpProcessor: IHttpProcessor,
    @BindOkHttp var iHttpProcessor2: IHttpProcessor
) :
    ViewModel() {

    private val testLiveData: MutableLiveData<Int> = MutableLiveData()

    fun testNetwork() {
        iHttpProcessor.hashCode().eClassTagLog<MainViewModel>()

//        iHttpProcessor.run {
//
//        }

        launch {


            if (User("2") is Any) {
                "User(\"2\") is Any".iLog()
            }

            if (listOf(User("2")) is Any) {
                "listOf(User(\"2\")) is Any".iLog()
            }

            var response = iHttpProcessor.get<Banner>("banner/json")
            response.data[0].title.eClassTagLog<MainViewModel>()
            //"${response?.data?.message} launch".eClassTagLog<MainViewModel>()

//            var response2 =
//                iHttpProcessor.get2("banner/json", mapOf(), NetworkException::class.java)
//            response2?.data?.message?.eClassTagLog<MainViewModel>()
        }
    }

//    private suspend fun test(): NetworkException {
//        var response = iHttpProcessor.get("banner/json")
//        return response.data as NetworkException
//    }
}


fun ViewModel.launch(request: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launch(Dispatchers.IO) {
        try {
            request()
        } catch (e: Exception) {
            e.message.eLog()
        }
    }
}