package com.dongchao.wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dongchao.core.lib.eClassTagLog
import com.dongchao.core.network.IHttpProcessor
import com.dongchao.core.network.annotation.BindOkHttp
import com.dongchao.core.network.annotation.BindRetrofit
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(@BindOkHttp var iHttpProcessor: IHttpProcessor) :
    ViewModel() {

//    @BindRetrofit
//    @Inject
//    lateinit var iHttpProcessor: IHttpProcessor

    private val testLiveData: MutableLiveData<Int> = MutableLiveData()

    suspend fun testNetwork() {
        iHttpProcessor.hashCode().eClassTagLog<LoginViewModel>()
        iHttpProcessor.get("banner/json")
    }
}