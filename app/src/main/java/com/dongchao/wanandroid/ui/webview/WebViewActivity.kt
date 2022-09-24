package com.dongchao.wanandroid.ui.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import by.kirich1409.viewbindingdelegate.activityViewBinding
import by.kirich1409.viewbindingdelegate.internal.findRootView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dongchao.core.lib.base.webview.BaseWebViewActivity
import com.dongchao.core.lib.router.AppRouter
import com.dongchao.wanandroid.R
import com.dongchao.wanandroid.databinding.ActivityWebviewBinding

//@Route(path = AppRouter.webViewPath)
class WebViewActivity : BaseWebViewActivity() {

    private val viewBinding by viewBinding(ActivityWebviewBinding::bind)

    override fun getAgentWebParent(): ViewGroup? {
        return viewBinding.root
    }

    override fun getUrl(): String? {
        return "https://www.baidu.com/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
    }
}