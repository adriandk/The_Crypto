package com.adrian.thecrypto.core.data.source.remote

import android.util.Log
import com.adrian.thecrypto.core.data.source.remote.network.ApiResponse
import com.adrian.thecrypto.core.data.source.remote.network.ApiService
import com.adrian.thecrypto.core.data.source.remote.response.CryptoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllCoin(): Flow<ApiResponse<List<CryptoResponse>>> {
        return flow {
            try {
                val response = apiService.getAllCoin()
                Log.e("RemoteData", "$response")

                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }

            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}