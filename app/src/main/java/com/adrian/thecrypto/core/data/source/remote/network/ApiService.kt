package com.adrian.thecrypto.core.data.source.remote.network

import com.adrian.thecrypto.core.data.source.remote.response.CryptoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("coins/markets")
    fun getAllCoin(
        @Query("vs_currency") currency: String = "usd",
        @Query("per_page") perPage: Int = 100,
        @Query("sparkline") sparkline: Boolean = false,
        @Query("order") order: String = "market_cap_desc"
    ): List<CryptoResponse>

    @GET("coins/{id}")
    fun getCoinDetail(
        @Path("id") id: String,
        @Query("localization") localization: Boolean = false
    ): CryptoResponse
}