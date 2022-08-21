package com.dongchao.core.network

import com.dongchao.core.lib.eClassTagLog
import com.dongchao.core.lib.iLog
import com.dongchao.core.network.bean.NetworkResponse
import com.google.gson.Gson
import kotlinx.serialization.Serializable
import okhttp3.FormBody
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import javax.inject.Inject


class RetrofitProcessor @Inject constructor(var retrofitApi: RetrofitApi) :
    IHttpProcessor {

//    override suspend fun post(url: String, params: Map<String, String>): NetworkResponse<T> =
//        retrofitApi.requestPost(url, appendBody(params))
//
//    override suspend fun get(url: String, params: Map<String, String>): NetworkResponse<T> =
//        retrofitApi.requestGet(url, params)

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

    interface RetrofitApi {
        @POST("{url}")
        suspend fun requestPost(
            @Path("url") url: String,
            @Body requestBody: RequestBody
        ): NetworkResponse

        @GET("{url}")
        suspend fun requestGet(
            @Path("url") url: String,
            @QueryMap map: Map<String, String>
        ): NetworkResponse
    }

    override suspend fun post(url: String, params: Map<String, String>) {
        "post retrofitApi.hashCode() = ${retrofitApi.hashCode()}".eClassTagLog<RetrofitProcessor>()
        //retrofitApi.requestPost()
    }

    override suspend fun get(url: String, params: Map<String, String>) {
        "get retrofitApi.hashCode() = ${retrofitApi.hashCode()}".eClassTagLog<RetrofitProcessor>()
        try {
            retrofitApi.requestGet(url, params).eClassTagLog<RetrofitProcessor>()
        } catch (e: Exception) {
            e.message?.eClassTagLog<RetrofitProcessor>()
        }
    }

}