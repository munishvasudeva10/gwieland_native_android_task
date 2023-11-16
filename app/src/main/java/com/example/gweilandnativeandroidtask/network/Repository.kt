package com.example.gweilandnativeandroidtask.network

import android.content.Context
import android.util.Log
import com.example.gweilandnativeandroidtask.R
import com.example.gweilandnativeandroidtask.utilities.alerts.Alerts
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject


class Repository @Inject constructor(
    @ApplicationContext val mContext: Context,
    private val retrofitApi: RetrofitApi,
) {
    fun <T> makeCall(
        requestProcessor: ApiProcessor<Response<T>>
    ) {


        val dataResponse: Flow<Response<Any>> = flow {
            val response =
                requestProcessor.sendRequest(retrofitApi) as Response<Any>
            emit(response)
        }.flowOn(Dispatchers.IO)

        CoroutineScope(Dispatchers.Main).launch {
            dataResponse.catch { exception ->
                exception.printStackTrace()
            }.collect { response ->
                Log.e("resCodeIsd", "====${response.code()}")
                when {
                    response.code() in 100..199 -> {
                        /**Informational*/
                        requestProcessor.onError(
                            mContext.resources?.getString(R.string.some_error_occured) ?: ""
                        )
                        Alerts.showMessage(
                            mContext,
                            mContext.resources?.getString(R.string.some_error_occured) ?: ""
                        )
                    }
                    response.isSuccessful -> {
                        /**Success*/
                        Log.d("successBody", "====${response.body()}")
                        requestProcessor.onResponse(response as Response<T>)
                    }
                    response.code() in 300..399 -> {
                        /**Redirection*/
                        requestProcessor.onError(
                            mContext.resources?.getString(R.string.some_error_occured) ?: ""
                        )
                        Alerts.showMessage(
                            mContext,
                            mContext.resources?.getString(R.string.some_error_occured) ?: ""
                        )
                    }
                    response.code() == 401 -> {
                        /**UnAuthorized*/
                        Log.d("errorBody", "====${response.errorBody()?.string()}")
                        requestProcessor.onError("UnAuthorized")
                    }
                    response.code() == 404 -> {
                        /**Page Not Found*/
                        requestProcessor.onError(
                            mContext.resources?.getString(R.string.some_error_occured) ?: ""
                        )
                        Alerts.showMessage(
                            mContext,
                            mContext.resources?.getString(R.string.some_error_occured) ?: ""
                        )
                    }
                    response.code() == 400 -> {
                        /**ClientErrors*/
                        val res = response.errorBody()!!.string()
                        val jsonObject = JSONObject(res)
                        when {
                            jsonObject.has("message") -> {
                                requestProcessor.onError(jsonObject.getString("message"))
                            }
                            else -> {
                                requestProcessor.onError(
                                    mContext.resources?.getString(R.string.some_error_occured)
                                        ?: ""
                                )

                            }
                        }
                    }
                    response.code() in 500..599 -> {
                        /**ServerErrors*/
                        requestProcessor.onError(
                                "Internal Server Error"
                        )
                    }
                    else -> {
                        /**ClientErrors*/
                        val res = response.errorBody()!!.string()
                        val jsonObject = JSONObject(res)
                        when {
                            jsonObject.has("message") -> {
                                requestProcessor.onError(jsonObject.getString("message"))

                                if (!jsonObject.getString("message").equals("Data not found", true))
                                    Alerts.showMessage(mContext, jsonObject.getString("message"))
                            }
                            else -> {
                                requestProcessor.onError(
                                    mContext.resources?.getString(R.string.some_error_occured)
                                        ?: ""
                                )
                                Alerts.showMessage(
                                    mContext,
                                    mContext.resources?.getString(R.string.some_error_occured)
                                        ?: ""
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getRefreshToken() {}
}
