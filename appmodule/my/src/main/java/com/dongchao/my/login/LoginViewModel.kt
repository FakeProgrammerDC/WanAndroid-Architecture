package com.dongchao.my.login

import com.dongchao.core.lib.base.*
import com.dongchao.core.network.IHttpProcessor
import com.dongchao.core.network.annotation.BindRetrofit
import com.dongchao.core.network.bean.NetworkResponse
import com.dongchao.core.network.extensions.requestPost

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() :
    BaseViewModel<LoginContract.State, LoginContract.Effect>() {

    override fun createInitialState(): LoginContract.State {
        return LoginContract.State(
            loadStatus = LoginContract.LoadStatus.LoadInit,
            verCodeStatus = LoginContract.VerCodeStatus.LoadVerCodeInit
        )
    }

    private fun sendVerCode(phone: String) {

        setState {
            copy(loadStatus = LoginContract.LoadStatus.LoadStart)
        }

        setState {
            copy(loadStatus = LoginContract.LoadStatus.LoadSuccess)
        }
    }

    private fun login(phone: String, pwd: String) = launch {

        setState {
            copy(loadStatus = LoginContract.LoadStatus.LoadStart)
        }

        var responseUserLogin = iHttpProcessor.requestPost<UserLogin>(
            "user/login",
            mapOf(
                "username" to phone,
                "password" to pwd,
            )
        )

        if (responseUserLogin is NetworkResponse.Success) {
            setState {
                copy(
                    loadStatus = LoginContract.LoadStatus.LoadSuccess,
                    userLogin = responseUserLogin.data
                )
            }
        }
    }
}


/**
 *    @author : dong.chao
 *    @time   : 2022/9/2
 *    @desc   : 定义具体业务需要的State、Event、Effect类
 */
class LoginContract {
    /**
     * pageTitle: 页面标题
     * loadStatus: 加载的状态
     */
    data class State constructor(
        val pageTitle: String = "登录页面",
        val loadStatus: LoadStatus,
        val verCodeStatus: VerCodeStatus,
        val userLogin: UserLogin? = null,
    ) : UiState

    sealed class Effect : UiEffect {
        // 弹出数据加载错误Toast
        data class ShowErrorToastEffect(val text: String) : Effect()
    }

    sealed class LoadStatus {

        object LoadInit : LoadStatus()

        object LoadStart : LoadStatus()

        object LoadSuccess : LoadStatus()

        data class LoadError(val exception: Throwable) : LoadStatus()

        data class LoadFailed(val errCode: Int) : LoadStatus()
    }

    sealed class VerCodeStatus {
        // 验证码完成状态
        object LoadVerCodeInit : VerCodeStatus()
        data class LoadVerCodeCompletion(val VerCode: Int) : VerCodeStatus()
        data class LoadVerCode(val VerCode: Int) : VerCodeStatus()
    }
}