package com.example.gweilandnativeandroidtask.modules.home.exchange

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.SavedStateHandle
import com.example.gweilandnativeandroidtask.base.BaseVM
import com.example.gweilandnativeandroidtask.models.BaseResponse
import com.example.gweilandnativeandroidtask.models.CryptoModel
import com.example.gweilandnativeandroidtask.network.ApiProcessor
import com.example.gweilandnativeandroidtask.network.Repository
import com.example.gweilandnativeandroidtask.network.RetrofitApi
import com.google.gson.JsonElement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class ExchangeVM @Inject constructor(
    application: Application,
    savedStateHandle: SavedStateHandle,
    private val repository: Repository
) : BaseVM(
    application,
    savedStateHandle
) {

    var selectedOption =  ""

    var searchText = mutableStateOf("")

    private var _cryptoList: MutableStateFlow<SnapshotStateList<CryptoModel>> = MutableStateFlow(
        mutableStateListOf()
    )
    val cryptoList: StateFlow<SnapshotStateList<CryptoModel>> = _cryptoList
    var localList= arrayListOf<CryptoModel>()
    var idsList= listOf<Int>()

    init {
        getLatestCryptoList()
    }


    //-- Get Latest Crypto Api--//
    private fun getLatestCryptoList(
    ) = repository.makeCall(
        requestProcessor = object : ApiProcessor<Response<BaseResponse<List<CryptoModel>?>>> {
            override suspend fun sendRequest(retrofitApi: RetrofitApi): Response<BaseResponse<List<CryptoModel>?>> {
                return retrofitApi.getCryptoListApi()
            }

            override fun onResponse(res: Response<BaseResponse<List<CryptoModel>?>>) {
                if (res.body()?.status?.error_code==0) {
                    localList=(res.body()?.data?: arrayListOf()) as ArrayList
                    sortListIfRequired()
                    idsList=localList.map {
                        it.id?:0
                    }
                    getLogosApi()
                } else {
                    alert(res.body()?.status?.error_message?:"")
                }
            }

            override fun onError(message: String) {
                super.onError(message)
                alert(message)
            }
        }
    )


    //-- Get Logos Api --//
    private fun getLogosApi(
    ) = repository.makeCall(
        requestProcessor = object : ApiProcessor<Response<BaseResponse<JsonElement?>>> {
            override suspend fun sendRequest(retrofitApi: RetrofitApi): Response<BaseResponse<JsonElement?>> {
                val ids=idsList.joinToString().replace(" ","")
                Log.e("dvcfvjfd",ids)
                return retrofitApi.getCryptoLogosApi(ids)
            }

            override fun onResponse(res: Response<BaseResponse<JsonElement?>>) {
                if (res.body()?.status?.error_code==0) {
                    val jsonObject = JSONObject(res.body()?.data.toString())
                    for(i in localList.indices){
                        val id=localList[i].id.toString()
                        localList[i]= localList[i].copy(logo = jsonObject.optJSONObject(id).optString("logo"))
                    }
                    refreshCryptoList(localList)
                } else {
                    alert(res.body()?.status?.error_message?:"")
                }
            }

            override fun onError(message: String) {
                super.onError(message)
                alert(message)
            }
        }
    )


    //-- Filter Crypto List --//
    fun sortListIfRequired() {
        val tempList= localList
        val sortedList:List<CryptoModel> = if(selectedOption.isNotEmpty()){
            tempList.sortedBy { if(selectedOption=="price") it.quote?.USD?.price else it.quote?.USD?.percent_change_24h}
        }else{
            tempList.sortedBy {it.cmc_rank}
        }
        refreshCryptoList(sortedList)
    }

    //-- Search in List --//
    fun searchCrypto(){
        if (searchText.value.isNotEmpty()) {
            val temp = kotlin.collections.ArrayList<CryptoModel>()
            for (d in localList) {
                if ((d.symbol ?: "").lowercase().contains(searchText.value.lowercase()) ||
                    (d.name ?: "").lowercase().contains(searchText.value.lowercase())
                ) {
                    temp.add(d)
                }
            }
            refreshCryptoList(temp.distinct())
        } else {
            sortListIfRequired()
        }
    }


    //-- Refresh MainCrypto List -- //
    fun refreshCryptoList(arrayList:List<CryptoModel>){
        _cryptoList.value.clear()
        _cryptoList.value.addAll(arrayList)
    }


}