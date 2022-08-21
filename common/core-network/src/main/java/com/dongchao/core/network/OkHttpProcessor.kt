package com.dongchao.core.network

import com.dongchao.core.lib.iClassTagLog
import com.dongchao.core.network.di.BASE_URL
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.*
import retrofit2.Invocation
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


class OkHttpProcessor @Inject constructor(var okHttpClient: OkHttpClient) :
    IHttpProcessor {

    override suspend fun post(url: String, params: Map<String, String>) {
        val requestBody = appendBody(params)
        val request = Request.Builder().url("$BASE_URL$url").post(requestBody).build()
        try {
            var str = okHttpClient.newCall(request).await()
            str.iClassTagLog<OkHttpProcessor>()
        } catch (e: Exception) {
            e.message?.iClassTagLog<OkHttpProcessor>()
        }
    }

    override suspend fun get(url: String, params: Map<String, String>) {
        val request: Request = Request.Builder()
            .url("$BASE_URL$url")
            .build()
        try {
            var str = okHttpClient.newCall(request).await()
            str.iClassTagLog<OkHttpProcessor>()
        } catch (e: Exception) {
            e.message?.iClassTagLog<OkHttpProcessor>()
        }
    }

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
}

suspend fun Call.await(): String {
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
                        continuation.resume(body.toString())
                    }
                } else {
                    continuation.resumeWithException(RuntimeException("error"))
                }
            }
        })
    }
}