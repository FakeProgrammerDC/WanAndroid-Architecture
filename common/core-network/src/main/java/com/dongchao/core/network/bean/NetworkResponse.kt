package com.dongchao.core.network.bean

import kotlinx.serialization.Serializable


@Serializable
data class NetworkResponse<T>(
    val data: T
)