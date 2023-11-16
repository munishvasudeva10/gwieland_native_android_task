package com.example.gweilandnativeandroidtask.network

import com.example.gweilandnativeandroidtask.models.BaseResponse
import com.example.gweilandnativeandroidtask.models.CryptoModel
import com.example.gweilandnativeandroidtask.network.ApiConstants.CRYPTO_LIST_API
import com.example.gweilandnativeandroidtask.network.ApiConstants.CRYPTO_LOGO_API
import com.google.gson.JsonElement
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.*

interface RetrofitApi {

    @GET(CRYPTO_LIST_API)
    suspend fun getCryptoListApi(): Response<BaseResponse<List<CryptoModel>?>>

    @GET(CRYPTO_LOGO_API)
    suspend fun getCryptoLogosApi(
        @Query("id")  ids :String
    ): Response<BaseResponse<JsonElement?>>

}