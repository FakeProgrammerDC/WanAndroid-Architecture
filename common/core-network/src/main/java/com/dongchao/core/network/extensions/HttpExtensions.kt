package com.dongchao.core.network.extensions

import com.dongchao.common.utils.converts.GsonSingleton
import com.dongchao.core.network.IHttpProcessor
import com.dongchao.core.network.bean.NetworkResponse
import com.dongchao.core.network.exception.AppEmptyResponseException
import com.dongchao.core.network.exception.AppNetworkException
import com.dongchao.core.network.exception.getError
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException
import java.lang.reflect.Type
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend inline fun <reified T> IHttpProcessor.requestGet(
    url: String,
    params: Map<String, String> = mapOf()
): NetworkResponse<T> {

    var networkResponse = get<T>(
        url,
        object : TypeToken<NetworkResponse.Success<T>>() {}.type,
        params
    )

    return if (networkResponse is NetworkResponse.Success && networkResponse.errorCode != 0) {
        NetworkResponse.BusinessError(networkResponse.errorCode, networkResponse.errorMsg)
    } else {
        networkResponse
    }
}

suspend inline fun <reified T> IHttpProcessor.requestPost(
    url: String,
    params: Map<String, String> = mapOf()
): NetworkResponse<T> {

    var networkResponse = get<T>(
        url,
        object : TypeToken<NetworkResponse.Success<T>>() {}.type,
        params
    )

    return if (networkResponse is NetworkResponse.Success && networkResponse.errorCode != 0) {
        NetworkResponse.BusinessError(networkResponse.errorCode, networkResponse.errorMsg)
    } else {
        networkResponse
    }
}

fun <T> convertData(json: String, type: Type): NetworkResponse.Success<T> {
    var gson = GsonSingleton.GSON
    var objResult = gson.fromJson<T>(json, type)
    return (objResult ?: NetworkResponse.BusinessError(
        -1003,
        "fromJson == null"
    )) as NetworkResponse.Success<T>
}

suspend fun <T> getNetworkResponse(
    type: Type,
    block: suspend () -> ResponseBody
): NetworkResponse<T> {
    var responseBody: ResponseBody
    try {
        responseBody = block()
    } catch (e: AppNetworkException) {
        return NetworkResponse.BusinessError(e.code, e.msg)
    } catch (e: Exception) {
        return getError(e).run {
            NetworkResponse.Error(code, msg, e)
        }
    }
    return convertData(responseBody.string(), type)
}

suspend fun <T : Any> Call.await(): T {
    return suspendCancellableCoroutine { continuation ->
        continuation.invokeOnCancellation {
            cancel()
        }
        enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val body = response.body
                    if (body == null) {
                        continuation.resumeWithException(
                            AppEmptyResponseException(
                                -1000,
                                "response.isSuccessful response.body == null"
                            )
                        )
                    } else {
                        continuation.resume(body as T)
                    }
                } else {
                    continuation.resumeWithException(AppNetworkException(-1001, response.message))
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                continuation.resumeWithException(e)
            }
        })

    }
}