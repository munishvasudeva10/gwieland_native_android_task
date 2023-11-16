package com.example.gweilandnativeandroidtask.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.DecimalFormat

@Parcelize
data class CryptoModel(
    val circulating_supply: Double? = null,
    val cmc_rank: Int? = null,
    val date_added: String? = null,
    val id: Int? = null,
    val infinite_supply: Boolean? = null,
    val last_updated: String? = null,
    val max_supply: Long? = null,
    val name: String? = null,
    val num_market_pairs: Int? = null,
    val quote: Quote? = null,
    val self_reported_circulating_supply: String? = null,
    val self_reported_market_cap: String? = null,
    val slug: String? = null,
    val symbol: String? = null,
    val tags: List<String?>? = null,
    val total_supply: Double? = null,
    val tvl_ratio: String? = null,
    var logo:String?=null
):Parcelable{

}

@Parcelize
data class Quote(
    val USD: USD? = null
):Parcelable

@Parcelize
data class USD(
    val fully_diluted_market_cap: Double? = null,
    val last_updated: String? = null,
    val market_cap: Double? = null,
    val market_cap_dominance: Double? = null,
    val percent_change_1h: Double? = null,
    val percent_change_24h: Double? = null,
    val percent_change_30d: Double? = null,
    val percent_change_60d: Double? = null,
    val percent_change_7d: Double? = null,
    val percent_change_90d: Double? = null,
    val price: Double? = null,
    val tvl: String? = null,
    val volume_24h: Double? = null,
    val volume_change_24h: Double? = null
):Parcelable{
    fun getRoundedPrice():String{
        val df = DecimalFormat("#.##")
        return df.format(price)
    }

    fun getPercentChange24():String{
        val df = DecimalFormat("#.##")
        return df.format(percent_change_24h)
    }
}