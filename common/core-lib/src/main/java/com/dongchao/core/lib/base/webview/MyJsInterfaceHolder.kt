package com.dongchao.core.lib.base.webview

import android.content.Context
import android.webkit.JavascriptInterface
import com.just.agentweb.AgentWeb

class MyJsInterfaceHolder(val context: Context, val agentWeb: AgentWeb? = null) {

    @JavascriptInterface
    fun getUserToken() {
//        if (!UserManager.checkIsGotoLogin()) {
//            mAgentWeb?.let {
//                it.jsAccessEntrace?.quickCallJs(
//                    "setUserToken",
//                    UserManager.getToken()
//                )
//            }
//        }
    }
}