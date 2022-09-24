package com.dongchao.core.network.exception

import com.dongchao.core.network.exception.CommonCode.HTTP_ERROR_MSG
import com.dongchao.core.network.exception.CommonCode.HTTP_SERVER_ERROR_MSG
import com.google.gson.JsonParseException
import kotlinx.serialization.Serializable
import org.json.JSONException
import java.io.FileNotFoundException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

@Serializable
open class AppNetworkException(var code: Int, var msg: String) : RuntimeException()

@Serializable
class AppEmptyResponseException(code: Int, msg: String) : AppNetworkException(code, msg)

@Serializable
class ApiException(code: Int, msg: String) : AppNetworkException(code, msg)


fun getError(e: Exception): ApiException {
    //println("异常类信息: $e")
    var ex: ApiException
    when (e) {
        is ConnectException -> {
            ex =
                ApiException(CommonCode.HTTP_CONNECTION_ERROR, CommonCode.HTTP_CONNECTION_ERROR_MSG)
        }
        is UnknownHostException -> {
            ex = ApiException(
                CommonCode.HTTP_UNKNOWN_HOST_ERROR,
                CommonCode.HTTP_UNKNOWN_HOST_ERROR_MSG
            )
        }
        is SocketTimeoutException -> {
            ex = ApiException(CommonCode.HTTP_TIMEOUT_ERROR, CommonCode.HTTP_TIMEOUT_ERROR_MSG)
        }
        is JsonParseException, is JSONException -> {
            ex = ApiException(
                CommonCode.INNER_JSON_PARSE_ERROR,
                CommonCode.INNER_JSON_PARSE_ERROR_MSG
            )
        }
        is FileNotFoundException -> {
            ex = ApiException(CommonCode.INNER_FILE_NOT_FOUND_ERROR, CommonCode.INNER_ERROR_MSG)
        }
        is IllegalArgumentException -> {
            ex = ApiException(CommonCode.INNER_FILE_NOT_FOUND_ERROR, CommonCode.INNER_ERROR_MSG)
        }
        else -> {
            ex = ApiException(CommonCode.UNKNOWN_ERROR, CommonCode.UNKNOWN_ERROR_MSG)
        }
    }

    if (CommonCode.isNetErr(ex.code)) {
        ex.msg = HTTP_ERROR_MSG
    } else if (CommonCode.isServerErr(ex.code)) {
        ex.msg = HTTP_SERVER_ERROR_MSG
    }
    return ex
}