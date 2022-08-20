package com.dongchao.core.network

import com.dongchao.core.network.bean.NetworkResponse

interface IHttpProcessor {

    //  post 请求
    suspend fun post(url: String, params: Map<String, String>): NetworkResponse<*>

    //  get 请求
    suspend fun get(url: String, params: Map<String, String>): NetworkResponse<*>
}