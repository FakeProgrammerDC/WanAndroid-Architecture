package com.dongchao.core.network.bean

import kotlinx.serialization.Serializable

///**
// * A generic class that holds a value or error.
// * @param <T>
// */
//sealed class Result<out R> {
//
//    data class Success<out T>(val data: T) : Result<T>()
//
//    data class Error(val exception: Exception) : Result<Nothing>()
//
//    override fun toString(): String {
//        return when (this) {
//            is Success<*> -> "Success[data=$data]"
//            is Error -> "Error[exception=$exception]"
//        }
//    }
//}
//
///**
// * `true` if [Result] is of type [Success] & holds non-null [Success.data].
// */
//val Result<*>.succeeded
//    get() = this is Success && data != null

//@Serializable
//data class NetworkResponse<T>(var errorCode: Int, var errorMsg: String, var data: T)


sealed class NetworkResponse<out T> {

    /**
     *  网络数据请求成功,数据返回正常
     */
    @Serializable
    data class Success<T>(var errorCode: Int, var errorMsg: String, var data: T) :
        NetworkResponse<T>()

    /**
     *  网络数据请求成功,业务异常如:账号密码错误
     */
    data class BusinessError(var errorCode: Int, var errorMsg: String) : NetworkResponse<Nothing>()

    /**
     *  网络数据请求异常,如:无网
     */
    data class Error(var errorCode: Int, var errorMsg: String, val exception: Exception) :
        NetworkResponse<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is BusinessError -> "BusinessError[data=$errorMsg]"
            is Error -> "Error[exception=$exception]"
        }
    }
}