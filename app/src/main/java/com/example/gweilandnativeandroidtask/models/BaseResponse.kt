package com.example.gweilandnativeandroidtask.models

data class BaseResponse<M>(
    val data: M? = null,
    val status: Status? = null,
)


data class Status(
    val credit_count: Int? = null,
    val elapsed: Int? = null,
    val error_code: Int? = null,
    val error_message: String? = null,
    val notice: Any? = null,
    val timestamp: String? = null,
    val total_count: Int? = null
)