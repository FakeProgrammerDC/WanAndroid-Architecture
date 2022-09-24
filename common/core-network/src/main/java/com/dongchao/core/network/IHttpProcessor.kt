package com.dongchao.core.network

import com.dongchao.core.network.bean.NetworkResponse
import com.google.gson.Gson
import java.lang.reflect.Type

interface IHttpProcessor {

    //  post 请求
    suspend fun <T> post(
        url: String,
        type: Type,
        params: Map<String, String> = mapOf()
    ): NetworkResponse<T>

    //  get 请求
    suspend fun <T> get(
        url: String,
        type: Type,
        params: Map<String, String> = mapOf()
    ): NetworkResponse<T>



}