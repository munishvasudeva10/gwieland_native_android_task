package com.example.gweilandnativeandroidtask.network

interface ApiProcessor<T> {
    suspend fun sendRequest(retrofitApi: RetrofitApi): T
    fun onResponse(res: T)
    fun onError(message: String) {}
}