package com.dongchao.core.lib.base

import android.content.Context
import android.util.AttributeSet
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dongchao.common.utils.eLog
import com.dongchao.common.utils.iClassTagLog
import com.dongchao.common.utils.iLog
import com.dongchao.core.network.IHttpProcessor
import com.dongchao.core.network.annotation.BindOkHttp
import com.dongchao.core.network.annotation.BindRetrofit
import com.dongchao.core.network.bean.NetworkResponse
import com.dongchao.core.network.exception.AppNetworkException
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

//sealed class Async<out T> {
//    object Loading : Async<Nothing>()
//    data class Success<out T>(val data: T) : Async<T>()
//}

interface UiState

abstract class BaseUiState<T> : UiState {
    abstract val isLoading: Boolean
    abstract val data: T?
}

data class DefaultUiState(
    override val isLoading: Boolean = false,
    override val data: Any = Any(),
) :
    BaseUiState<Any>()

interface UiEffect

sealed class DefaultUiEffect : UiEffect

//sealed class BaseUiEffect : UiEffect {
//    // 弹出数据加载错误Toast
//    // data class ShowErrorToastEffect(val text: String) : BaseUiEffect()
//}

/**
 *    @author : dong.chao
 *    @time   : 2022/9/3
 *    @desc   : ViewModel 基类
 */
abstract class BaseViewModel<State : UiState, Effect : UiEffect> : ViewModel() {

    @Inject
    @BindRetrofit
    protected lateinit var iHttpProcessor: IHttpProcessor

    /**
     * 初始状态
     * stateFlow 区别于 LiveData 必须有初始值
     */
    private val initialState: State by lazy { createInitialState() }

    private var currentState = initialState

    abstract fun createInitialState(): State

    /**
     * uiState 聚合页面的全部 UI 状态
     */
    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)

    /**
     * asStateFlow 变成不可变
     */
    val uiState = _uiState.asStateFlow()

    /**
     * effect 用作事件带来的副作用，通常是一次性事件且一对一的订阅关系
     * 例如：弹Toast、导航Fragment等
     * _effect 内热，外冷
     */
    private val _effect: Channel<Effect> = Channel()

    val effect = _effect.receiveAsFlow()

    fun setState(reduce: Reduce<State>) {
        val newReduce = currentState.reduce()
        newReduce.toString().iLog("HomeViewModel")
        _uiState.value = newReduce
    }

    suspend fun sendEffect(effect: Effect) {
        viewModelScope.launch(Dispatchers.IO) {
            //delay(100)
            "sendSharedFlow".iLog("HomeViewModel")
            _effect.send(effect)
        }
    }
}

/**
 *  给 UI 状态变化函数取别名
 */
typealias Reduce<State> = State.() -> State

/**
 *  给 UI 状态变化函数取别名
 */
typealias EffectType<Effect> = Effect.() -> Effect

/**
 *  创建 UI 状态的语法糖
 */
fun <State> reduce(reduce: State.() -> State): Reduce<State> = reduce

public fun ViewModel.launch(request: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launch(Dispatchers.IO) {
        try {
            request()
        } catch (cancellationException: CancellationException) {
            "协程被取消 ${cancellationException.message}".eLog()
            throw cancellationException
        }
    }
}

// 什么都不写使用默认, Error = 网络异常 弹出dialog, BusinessError 业务异常 弹出toast
fun <T> ViewModel.handleNetResponse(
    response: NetworkResponse<T>,
    setStateError: () -> Unit = {},
    setStateBusinessError: () -> Unit = {
    },
    setStateSuccess: (T) -> Unit
): NetworkResponse<T> {
    when (response) {
        is NetworkResponse.Success -> {
            response.data.eLog("testNet")
            setStateSuccess(response.data)
        }
        is NetworkResponse.BusinessError -> {
            "BusinessError ${response.errorMsg}".eLog("testNet")
            setStateBusinessError()
        }
        is NetworkResponse.Error -> {
            "Error ${response.errorMsg}".eLog("testNet")
            setStateError()
        }
    }
    return response
}