package com.dongchao.wanandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dongchao.core.lib.eClassTagLog
import com.dongchao.core.lib.iClassTagLog
import com.dongchao.core.network.IHttpProcessor
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var http: IHttpProcessor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        "测试".eClassTagLog<MainActivity>()
    }

    fun testNetwork(view: View) {

    }
}