package com.dongchao.core.network

import com.dongchao.common.utils.eClassTagLog
import com.dongchao.core.network.bean.NetworkResponse
import com.dongchao.core.network.extensions.convertData
import com.dongchao.core.network.extensions.getNetworkResponse

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*
import java.lang.reflect.Type
import javax.inject.Inject


class RetrofitProcessor @Inject constructor(var retrofitApi: RetrofitApi) :
    IHttpProcessor {

    override suspend fun <T> post(
        url: String,
        type: Type,
        params: Map<String, String>
    ): NetworkResponse<T> {
        "post retrofitApi.hashCode() = ${retrofitApi.hashCode()}".eClassTagLog<RetrofitProcessor>()
        return getNetworkResponse(type) {
            retrofitApi.requestPost(url, params).body()!!
        }
    }

    override suspend fun <T> get(
        url: String,
        type: Type,
        params: Map<String, String>
    ): NetworkResponse<T> {
        "get retrofitApi.hashCode() = ${retrofitApi.hashCode()}".eClassTagLog<RetrofitProcessor>()
        return getNetworkResponse(type) {
            retrofitApi.requestGet(url, params).body()!!
        }
    }

    interface RetrofitApi {
        @FormUrlEncoded
        @POST
        suspend fun requestPost(
            @Url url: String,
            @FieldMap params: Map<String, String>
        ): Response<ResponseBody>

        @GET
        suspend fun requestGet(
            @Url url: String,
            @QueryMap params: Map<String, String>
        ): Response<ResponseBody>
    }
}