package com.dongchao.my.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.*
import com.dongchao.common.utils.eLog
import com.dongchao.core.lib.base.BaseActivity
import com.dongchao.core.lib.base.Reduce
import com.dongchao.core.lib.base.delegates.launchStarted
import com.dongchao.core.lib.base.reduce
import com.dongchao.core.lib.router.AppRouter
import com.dongchao.my.R
import com.dongchao.my.databinding.ActivityLoginBinding

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

//@Route(path = AppRouter.My.loginPath)
@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(
    R.layout.activity_login,
    ActivityLoginBinding::bind
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(viewBinding) {
            btnLogin.setOnClickListener {
//                viewModel.sendEvent(
//                    LoginContract.Event.LoginEvent(
//                        etPhone.text.toString(),
//                        etPassword.text.toString()
//                    )
//                )
            }
        }
    }


    override fun initObserver() {
        launchStarted {
            viewModel.uiState.collect { state ->
//                when (state.loadStatus) {
//                    is LoadError -> {
//
//                    }
//                    is LoadSuccess -> {
//                        state.userLogin?.nickname.eLog("LoginActivity")
//                        state.userLogin?.nickname?.showToast(this)
//                    }
//                    is LoginContract.LoadStatus.LoadStart -> {
//                        "开始加载".showToast(this)
//                    }
//                    else -> {}
//                }
            }

        }
    }
}



