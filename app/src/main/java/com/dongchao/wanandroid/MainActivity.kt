package com.dongchao.wanandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.dongchao.core.network.annotation.BindRetrofit
import com.dongchao.core.lib.eClassTagLog
import com.dongchao.core.network.IHttpProcessor
import com.dongchao.core.network.RetrofitProcessor
import com.dongchao.core.network.annotation.BindOkHttp
import com.dongchao.wanandroid.viewmodel.LoginViewModel
import com.dongchao.wanandroid.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity  : AppCompatActivity() {

    @BindRetrofit
    @Inject
    lateinit var iHttpProcessor: IHttpProcessor

    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    private val loginViewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }

    private val mainScope by lazy { MainScope() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun testNetwork(view: View) {
        iHttpProcessor.hashCode().eClassTagLog<MainActivity>()
        mainScope.launch {
            iHttpProcessor.get("banner/json")
//            viewModel.testNetwork()
//            loginViewModel.testNetwork()
        }
        //"测试".eClassTagLog<MainActivity>()
    }
}