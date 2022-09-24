package com.dongchao.wanandroid.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.dongchao.common.utils.iClassTagLog
import com.dongchao.core.lib.base.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// 内联扩展属性
// 优化一下缓存 liveData
inline val <T> Channel<T>.coroutineLiveData: LiveData<T>
    get() = liveData {
        for (entry in this@coroutineLiveData) {
            emit(entry)
            "emit result = $entry".iClassTagLog<TestMainViewModel>()
        }
    }


@HiltViewModel
class TestMainViewModel @Inject constructor() : BaseViewModel<DefaultUiState,DefaultUiEffect>() {

    override fun createInitialState() = DefaultUiState()

    sealed class Effect {
        data class ShowToastEffect(val text: String) : Effect()
    }

    // 依旧会有粘性
    // onPause 也就是界面不可见的时候依旧会发送出数据
    private val departCityTextChannel = Channel<String>(1)
    val departCityTextChannelLiveData = departCityTextChannel.coroutineLiveData

    private val _departCityTextLiveData = MutableLiveData<String>()
    val departCityTextLiveData: LiveData<String> = _departCityTextLiveData

    val testFlow = MutableSharedFlow<String>()

    private val _effect = Channel<Effect>(0)
    val effectTest = _effect.receiveAsFlow()

    private val _signEvent = MutableSharedFlow<String>(2)
    val signEvent: MutableSharedFlow<String> = _signEvent

    fun sendSharedFlow(string: String) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(100)
            "sendSharedFlow".iClassTagLog<TestMainViewModel>()
            _signEvent.emit(string)
        }
    }

    private fun setEffect(builder: () -> Effect) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(100)
            "viewModelScope.launch".iClassTagLog<TestMainViewModel>()
            val newEffect = builder()
            _effect.send(newEffect)
        }
    }

    fun showToast(text: String) {
        setEffect {
            "_effect.send newEffect.text = $text".iClassTagLog<TestMainViewModel>()
            Effect.ShowToastEffect(text)
        }
    }

    fun testN() {
        //testNetwork()
    }

    // 外部的 UI 通过调用该方法来更新数据
    fun updateCityUI(string: String) = viewModelScope.launch(Dispatchers.IO) {
        Thread.currentThread().name.iClassTagLog<TestMainViewModel>()
        val result = fetchData() // 拉取数据
        //delay(500)
        //departCityTextChannel.send(result)
        "LiveData.postValue result = $result $string".iClassTagLog<TestMainViewModel>()
        _departCityTextLiveData.postValue("$result $string")
    }

    private fun fetchData(): String {
        return "json 数据"
    }
}