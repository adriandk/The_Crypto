package com.adrian.thecrypto.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CryptoResponse(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("symbol")
    val symbol: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("price_change_percentage_1h_in_currency")
    val percentage: String,

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("current_price")
    val price: Int,

    @field:SerializedName("total_volume")
    val volume: Int,

    @field:SerializedName("description")
    val description: CryptoDescription,
)

data class CryptoDescription(
    @field:SerializedName("en")
    val desc: String,
)