package com.dongchao.core.network

import com.dongchao.core.network.bean.NetworkResponse
import com.dongchao.core.network.bean.User

interface IHttpProcessor {

    //  post 请求
    suspend fun <T : Any> post(
        url: String,
        params: Map<String, String> = mapOf()
    ): NetworkResponse<T>

    //  get 请求
    suspend fun <T : Any> get(
        url: String,
        params: Map<String, String> = mapOf()
    ): NetworkResponse<List<T>>

}