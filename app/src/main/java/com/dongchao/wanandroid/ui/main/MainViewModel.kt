package com.dongchao.wanandroid.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dongchao.common.utils.eClassTagLog
import com.dongchao.common.utils.eLog
import com.dongchao.common.utils.iLog
import com.dongchao.core.lib.base.BaseUiState
import com.dongchao.core.lib.base.BaseViewModel
import com.dongchao.core.lib.base.DefaultUiEffect
import com.dongchao.core.lib.base.DefaultUiState
import com.dongchao.core.network.IHttpProcessor
import com.dongchao.core.network.annotation.BindRetrofit
import com.dongchao.core.network.exception.AppNetworkException
import com.dongchao.core.network.extensions.requestGet
import com.dongchao.core.network.extensions.requestPost

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel<DefaultUiState, DefaultUiEffect>() {
    override fun createInitialState() = DefaultUiState()

    fun testNet(){
        iHttpProcessor.hashCode().eLog("MainViewModel")
    }
}

