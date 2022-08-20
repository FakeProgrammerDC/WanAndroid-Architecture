package com.dongchao.wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dongchao.core.network.IHttpProcessor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val iHttpProcessor: IHttpProcessor) : ViewModel() {

    private val testLiveData: MutableLiveData<Int> = MutableLiveData()

    suspend fun testNetwork() {
        iHttpProcessor.hashCode()
    }
}