package com.dongchao.core.network

import com.dongchao.common.utils.eClassTagLog
import com.dongchao.core.network.bean.NetworkResponse
import com.dongchao.core.network.di.BASE_URL
import com.dongchao.core.network.extensions.await
import com.dongchao.core.network.extensions.getNetworkResponse
import okhttp3.*
import java.lang.reflect.Type
import javax.inject.Inject


class OkHttpProcessor @Inject constructor(val okHttpClient: OkHttpClient) :
    IHttpProcessor {

    override suspend fun <T> post(
        url: String,
        type: Type,
        params: Map<String, String>
    ): NetworkResponse<T> {
        "post okHttpClient.hashCode() = ${okHttpClient.hashCode()}".eClassTagLog<OkHttpProcessor>()
        return getNetworkResponse(type) {
            okHttpClient.newCall(
                Request.Builder().url("$BASE_URL$url").post(appendBody(params)).build()
            ).await()
        }
    }

    override suspend fun <T> get(
        url: String,
        type: Type,
        params: Map<String, String>
    ): NetworkResponse<T> {
        "get okHttpClient.hashCode() = ${okHttpClient.hashCode()}".eClassTagLog<OkHttpProcessor>()
        return getNetworkResponse(type) {
            okHttpClient.newCall(
                Request.Builder()
                    .url("$BASE_URL$url")
                    .build()
            ).await()
        }
    }

    private fun appendBody(params: Map<String, String>): RequestBody {
        val body = FormBody.Builder()
        if (params.isEmpty()) {
            return body.build()
        }
        params.forEach { item ->
            body.add(item.key, item.value)
        }
        return body.build()
    }
}
