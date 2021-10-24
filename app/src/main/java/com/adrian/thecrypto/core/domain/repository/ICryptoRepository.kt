package com.adrian.thecrypto.core.domain.repository

import com.adrian.thecrypto.core.data.Resource
import com.adrian.thecrypto.core.domain.model.Crypto
import kotlinx.coroutines.flow.Flow

interface ICryptoRepository {
    fun loadAllCoin(sort: String): Flow<Resource<List<Crypto>>>
    fun getDetailCoin(id: String): Flow<Resource<Crypto>>
    fun getFavoriteCoin(): Flow<List<Crypto>>
    fun searchCoin(search: String): Flow<List<Crypto>>
    fun setFavoriteCoin(coin: Crypto, state: Boolean)
}