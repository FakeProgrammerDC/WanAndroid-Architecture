package com.dongchao.my

import com.dongchao.core.lib.base.BaseUiState
import com.dongchao.core.lib.base.BaseViewModel
import com.dongchao.core.lib.base.DefaultUiEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


data class MyState(
    override val isLoading: Boolean = false,
    override val data: List<Any> = emptyList(),
) :
    BaseUiState<List<Any>>()

/**
 *    @author : dong.chao
 *    @time   : 2022/9/24
 *    @desc   : ViewModel 模版类用于复制
 */
@HiltViewModel
class MyViewModel @Inject constructor() : BaseViewModel<MyState, DefaultUiEffect>() {

    override fun createInitialState(): MyState {
        return MyState()
    }
}