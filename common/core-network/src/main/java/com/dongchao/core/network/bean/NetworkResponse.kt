package com.dongchao.core.network.bean

import com.dongchao.core.network.exception.NetworkException
import kotlinx.serialization.Serializable


@Serializable
data class NetworkResponse<T : Any>(var errorCode: Int, var errorMsg: String, var data: T)