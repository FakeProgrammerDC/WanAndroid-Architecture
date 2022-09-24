package com.dongchao.core.lib.base.webview

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.ViewGroup
import android.webkit.DownloadListener
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.annotation.ColorInt
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.just.agentweb.*

abstract class BaseWebViewActivity : AppCompatActivity() {

    protected var mAgentWeb: AgentWeb? = null
    private val mAgentWebUIController: AgentWebUIControllerImplBase? = null
    private var mErrorLayoutEntity: ErrorLayoutEntity? = null
    private var mMiddleWareWebChrome: MiddlewareWebChromeBase? = null
    private var mMiddleWareWebClient: MiddlewareWebClientBase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        if (mAgentWeb == null) {
            buildAgentWeb()
        }
        addJavaObject()
    }

    protected open fun buildAgentWeb() {
        val mErrorLayoutEntity = getErrorLayoutEntity()
        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(getAgentWebParent()!!, ViewGroup.LayoutParams(-1, -1))
            .useDefaultIndicator(getIndicatorColor(), getIndicatorHeight())
            .setWebChromeClient(getWebChromeClient())
            .setWebViewClient(getWebViewClient())
            .setWebView(getWebView())
            .setPermissionInterceptor(getPermissionInterceptor())
            .setWebLayout(getWebLayout())
            .setAgentWebUIController(getAgentWebUIController())
            .interceptUnkownUrl()
            .setOpenOtherPageWays(getOpenOtherAppWay())
            .useMiddlewareWebChrome(getMiddleWareWebChrome()!!)
            .useMiddlewareWebClient(getMiddleWareWebClient()!!)
            .setAgentWebWebSettings(getAgentWebSettings())
            .setMainFrameErrorView(mErrorLayoutEntity.layoutRes, mErrorLayoutEntity.reloadId)
            .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
            .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DISALLOW) //打开其他页面时，弹窗质询用户前往其他应用 AgentWeb 3.0.0 加入。
            .interceptUnkownUrl()
            .createAgentWeb()
            .ready()
            .go(getUrl())
        //页面自适应
        mAgentWeb?.agentWebSettings?.webSettings?.useWideViewPort = true
        mAgentWeb?.agentWebSettings?.webSettings?.loadWithOverviewMode = true
    }


    @NonNull
    protected open fun getErrorLayoutEntity(): ErrorLayoutEntity {
        if (mErrorLayoutEntity == null) {
            mErrorLayoutEntity = ErrorLayoutEntity()
        }
        return mErrorLayoutEntity!!
    }

    protected open fun getAgentWeb(): AgentWeb? {
        return mAgentWeb
    }


    protected class ErrorLayoutEntity {
        val layoutRes = R.layout.agentweb_error_page
        val reloadId = 0
        fun setLayoutRes(layoutRes: Int) {
            var layoutRes = layoutRes
            if (layoutRes <= 0) {
                layoutRes = -1
            }
        }

        fun setReloadId(reloadId: Int) {
            var reloadId = reloadId
            if (reloadId <= 0) {
                reloadId = -1
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (mAgentWeb != null && mAgentWeb!!.handleKeyEvent(keyCode, event)) {
            true
        } else super.onKeyDown(keyCode, event)
    }

    override fun onPause() {
        if (mAgentWeb != null) {
            mAgentWeb!!.webLifeCycle.onPause()
        }
        super.onPause()
    }

    override fun onResume() {
        if (mAgentWeb != null) {
            mAgentWeb!!.webLifeCycle.onResume()
        }
        super.onResume()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }


    override fun onDestroy() {
        if (mAgentWeb != null) {
            mAgentWeb!!.webLifeCycle.onDestroy()
        }
        super.onDestroy()
    }

    @Nullable
    protected open fun getDownloadListener(): DownloadListener? {
        return null
    }


    protected open fun setTitle(view: WebView?, title: String?) {}

    @Nullable
    protected open fun getUrl(): String? {
        return null
    }

    @Nullable
    open fun getAgentWebSettings(): AbsAgentWebSettings {
        return object : AbsAgentWebSettings() {
            override fun bindAgentWebSupport(agentWeb: AgentWeb?) {
            }

            override fun toSetting(webView: WebView?): IAgentWebSettings<*> {
                var iAgentWebSettings = super.toSetting(webView)
                if (AgentWebUtils.checkNetwork(this@BaseWebViewActivity)) {
                    //根据cache-control获取数据。
                    iAgentWebSettings.webSettings.cacheMode = WebSettings.LOAD_NO_CACHE
                } else {
                    //没网，则从本地获取，即离线加载
                    iAgentWebSettings.webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                }
                return super.toSetting(webView)
            }
        }

    }

    @NonNull
    protected abstract fun getAgentWebParent(): ViewGroup?

    @Nullable
    protected open fun getWebChromeClient(): WebChromeClient? {
        return null
    }

    @ColorInt
    protected open fun getIndicatorColor(): Int {
        return -1
    }

    protected open fun getIndicatorHeight(): Int {
        return -1
    }

    @Nullable
    protected open fun getWebViewClient(): WebViewClient? {
        return null
    }


    @Nullable
    protected open fun getWebView(): WebView? {
        return null
    }

    @Nullable
    protected open fun getWebLayout(): IWebLayout<*, *>? {
        return null
    }

    @Nullable
    protected open fun getPermissionInterceptor(): PermissionInterceptor? {
        return null
    }

    @Nullable
    open fun getAgentWebUIController(): AgentWebUIControllerImplBase? {
        return null
    }

    @Nullable
    open fun getOpenOtherAppWay(): DefaultWebClient.OpenOtherPageWays? {
        return null
    }

    @NonNull
    protected open fun getMiddleWareWebChrome(): MiddlewareWebChromeBase? {
        return object : MiddlewareWebChromeBase() {}.also { mMiddleWareWebChrome = it }
    }

    @NonNull
    protected open fun getMiddleWareWebClient(): MiddlewareWebClientBase? {
        return object : MiddlewareWebClientBase() {}.also { mMiddleWareWebClient = it }
    }

    protected open fun addJavaObject() {
        mAgentWeb?.jsInterfaceHolder?.addJavaObject(
            "androidHolder",
            MyJsInterfaceHolder(this, mAgentWeb)
        )
    }

}