package com.dongchao.core.network.exception

import kotlinx.serialization.Serializable
import retrofit2.HttpException
import retrofit2.Response

@Serializable
class NetworkException(var code: Int?, var message: String?)