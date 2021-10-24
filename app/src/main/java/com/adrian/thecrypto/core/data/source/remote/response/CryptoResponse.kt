package com.adrian.thecrypto.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CryptoResponse(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("symbol")
    val symbol: String?,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("price_change_percentage_1h_in_currency")
    val percentage: Double?,

    @field:SerializedName("image")
    val image: String?,

    @field:SerializedName("current_price")
    val price: Double?,

    @field:SerializedName("total_volume")
    val volume: Double?,

    @field:SerializedName("ath")
    val ath: Double?,

    @field:SerializedName("market_cap_change_24h")
    val marketCap: Double?,

    @field:SerializedName("high_24h")
    val high: Double?,

    @field:SerializedName("low_24h")
    val low: Double?,

    @field:SerializedName("max_supply")
    val maxSupply: Double?,
)