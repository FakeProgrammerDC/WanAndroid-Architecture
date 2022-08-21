package com.dongchao.wanandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.dongchao.core.network.annotation.BindRetrofit
import com.dongchao.core.lib.eClassTagLog
import com.dongchao.core.lib.iLog
import com.dongchao.core.network.IHttpProcessor
import com.dongchao.core.network.RetrofitProcessor
import com.dongchao.core.network.annotation.BindOkHttp
import com.dongchao.core.network.bean.NetworkResponse
import com.dongchao.core.network.exception.NetworkException
import com.dongchao.wanandroid.viewmodel.LoginViewModel
import com.dongchao.wanandroid.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    private val loginViewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }

    private val mainScope by lazy { MainScope() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun testNetwork(view: View) {
        viewModel.testNetwork()
//        mainScope.launch {
//            var response = iHttpProcessor.get("banner/json")
//
//            if (response.data is NetworkException) {
//                var r = response.data as NetworkException
//                r.message?.eClassTagLog<MainActivity>()
//            }
//
////            iHttpProcessor2.get("banner/json")
////            viewModel.testNetwork()
////            loginViewModel.testNetwork()
//        }
        //iHttpProcessor.request<NetworkException>()
    }

//    private inline fun <reified T : Any> Any.request() {
//        GlobalScope.launch {
//            val response = iHttpProcessor.get("banner/json")
//            if (response.data is T) {
//                "response.data is T".iLog()
//                val data = response.data as T
//                liveData.postValue(data)
//            }
//        }
//        "Any.request end".iLog()
//    }
}