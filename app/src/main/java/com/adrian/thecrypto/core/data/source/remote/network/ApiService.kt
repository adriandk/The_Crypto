package com.adrian.thecrypto.core.data.source.remote.network

import com.adrian.thecrypto.core.data.source.remote.response.CryptoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("coins/markets")
    suspend fun getAllCoin(
        @Query("vs_currency") currency: String = "usd",
        @Query("per_page") perPage: Int = 100,
        @Query("sparkline") sparkline: Boolean = false,
        @Query("order") order: String = "market_cap_desc",
        @Query("price_change_percentage") percent: String = "1h"
    ): List<CryptoResponse>

    @GET("coins/{id}")
    suspend fun getCoinDetail(
        @Path("id") id: String,
        @Query("localization") localization: Boolean = false
    ): CryptoResponse
}