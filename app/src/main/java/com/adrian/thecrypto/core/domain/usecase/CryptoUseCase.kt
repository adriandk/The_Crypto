package com.adrian.thecrypto.core.domain.usecase

import com.adrian.thecrypto.core.data.Resource
import com.adrian.thecrypto.core.domain.model.Crypto
import kotlinx.coroutines.flow.Flow

interface CryptoUseCase {
    fun loadAllCoin(sort: String): Flow<Resource<List<Crypto>>>
    fun getFavoriteCoin(): Flow<List<Crypto>>
    fun searchCoin(search: String): Flow<List<Crypto>>
    fun setFavoriteCoin(coin: Crypto, state: Boolean)
}