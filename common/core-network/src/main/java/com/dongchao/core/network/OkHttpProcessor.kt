package com.dongchao.core.network

import com.dongchao.core.lib.iClassTagLog
import com.dongchao.core.network.bean.NetworkResponse
import com.dongchao.core.network.bean.User
import com.dongchao.core.network.di.BASE_URL
import com.google.gson.Gson
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.*
import retrofit2.Invocation
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


class OkHttpProcessor @Inject constructor(var okHttpClient: OkHttpClient) :
    IHttpProcessor {

//    override suspend fun post(url: String, params: Map<String, String>): NetworkResponse<Any> {
//        val requestBody = appendBody(params)
//        val request = Request.Builder().url("$BASE_URL$url").post(requestBody).build()
//        try {
//            //return okHttpClient.newCall(request).await()
//            //str.iClassTagLog<OkHttpProcessor>()
//        } catch (e: Exception) {
//            //e.message?.iClassTagLog<OkHttpProcessor>()
//        }
//        return NetworkResponse(User("w"))
//    }
//
//    override suspend fun get(url: String, params: Map<String, String>): NetworkResponse<Any> {
//        val request: Request = Request.Builder()
//            .url("$BASE_URL$url")
//            .build()
//        try {
//            var str = okHttpClient.newCall(request).await()
//            str.iClassTagLog<OkHttpProcessor>()
//        } catch (e: Exception) {
//            e.message?.iClassTagLog<OkHttpProcessor>()
//        }
//        return NetworkResponse(User("w"))
//    }

//    override suspend fun <T : Any> get2(
//        url: String,
//        params: Map<String, String>,
//        clazz: Class<T>
//    ): NetworkResponse<T>? {
//
//    }

    private fun appendBody(params: Map<String, Any>?): RequestBody {
        val body = FormBody.Builder()

        if (params == null || params.isEmpty()) {
            return body.build()
        }

        params.forEach { item ->
            body.add(item.key, item.value.toString())
        }

        return body.build()
    }

    override suspend fun <T : Any> post(
        url: String,
        params: Map<String, String>
    ): NetworkResponse<T> {
        TODO("Not yet implemented")
    }

    override suspend fun <T : Any> get(
        url: String,
        params: Map<String, String>
    ): NetworkResponse<List<T>> {
        TODO("Not yet implemented")
    }
}

suspend fun <T : Any> Call.await(): T {
    return suspendCancellableCoroutine { continuation ->
        continuation.invokeOnCancellation {
            cancel()
        }

        enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                continuation.resumeWithException(e)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val body = response.body
                    if (body == null) {
                        val invocation = call.request().tag(Invocation::class.java)!!
                        val method = invocation.method()
                        val e = KotlinNullPointerException(
                            "Response from " +
                                    method.declaringClass.name +
                                    '.' +
                                    method.name +
                                    " was null but response body type was declared as non-null"
                        )
                        continuation.resumeWithException(e)
                    } else {
//                        var gson = Gson()
//                        var objResult = gson.fromJson<T>(body.toString(), T::class)
//                        continuation.resume(body)
                    }
                } else {
                    continuation.resumeWithException(RuntimeException("error"))
                }
            }
        })
    }
}