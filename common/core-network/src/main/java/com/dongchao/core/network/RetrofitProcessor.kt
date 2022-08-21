package com.dongchao.core.network

import com.dongchao.core.lib.eClassTagLog
import com.dongchao.core.network.bean.Banner
import com.dongchao.core.network.bean.NetworkResponse
import com.dongchao.core.network.bean.User
import com.dongchao.core.network.exception.NetworkException
import okhttp3.FormBody
import okhttp3.RequestBody
import retrofit2.http.*
import java.lang.reflect.Type
import javax.inject.Inject


class RetrofitProcessor @Inject constructor(var retrofitApi: RetrofitApi) :
    IHttpProcessor {

    override suspend fun <T : Any> post(
        url: String,
        params: Map<String, String>
    ): NetworkResponse<T> {
        "post retrofitApi.hashCode() = ${retrofitApi.hashCode()}".eClassTagLog<RetrofitProcessor>()
        return retrofitApi.requestPost(url, appendBody(params))
    }

    override suspend fun <T : Any> get(
        url: String,
        params: Map<String, String>
    ): NetworkResponse<List<T>> {
        "get retrofitApi.hashCode() = ${retrofitApi.hashCode()}".eClassTagLog<RetrofitProcessor>()
        return try {
            retrofitApi.requestGet(url, params)
        } catch (e: Exception) {
            e.message?.let { message ->
                NetworkResponse(
                    -1,
                    message,
                    NetworkException(1, message)
                )
            } as NetworkResponse<List<T>>
        }

//        // 反射获取
//        val response = test<List<Banner>>(url, params)
//        return response as NetworkResponse<T>
    }

//    private suspend inline fun <reified T : Any> test(
//        url: String,
//        params: Map<String, String>
//    ): NetworkResponse<T> {
//        "get retrofitApi.hashCode() = ${retrofitApi.hashCode()}".eClassTagLog<RetrofitProcessor>()
//        return try {
//            retrofitApi.requestGet(url, params)
//        } catch (e: Exception) {
//            e.message?.let { message ->
//                NetworkResponse(
//                    -1,
//                    message,
//                    NetworkException(1, message)
//                )
//            } as NetworkResponse<List>
//        }
//    }

    interface RetrofitApi {
        @POST("{url}")
        suspend fun <T : Any> requestPost(
            @Path("url") url: String,
            @Body requestBody: RequestBody
        ): NetworkResponse<T>

        @GET("{url}")
        suspend fun <T : Any> requestGet(
            @Path("url") url: String,
            @QueryMap map: Map<String, String>
        ): NetworkResponse<List<T>>
    }

    private fun appendBody(params: Map<String, Any>?): RequestBody {
        "retrofitApi.hashCode() = ${retrofitApi.hashCode()}".eClassTagLog<RetrofitProcessor>()
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