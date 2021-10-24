package com.adrian.thecrypto.core.data.source.local

import com.adrian.thecrypto.core.data.source.local.entity.CryptoEntity
import com.adrian.thecrypto.core.data.source.local.room.CryptoDao
import com.adrian.thecrypto.core.utils.SortUtils
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val cryptoDao: CryptoDao) {
    fun getAllCoin(sort: String): Flow<List<CryptoEntity>> {
        return cryptoDao.getCoinList(SortUtils.getSortedQuery(sort))
    }

    fun searchCoin(search: String): Flow<List<CryptoEntity>> = cryptoDao.searchCoin(search)

    fun getFavoriteCoin(): Flow<List<CryptoEntity>> = cryptoDao.getFavoriteCoin()

    fun getDetailCoin(id: String): Flow<CryptoEntity> {
        return cryptoDao.getDetailCoin(id)
    }

    suspend fun insertCoin(coin: List<CryptoEntity>) =
        cryptoDao.insertCoin(coin)

    fun updateCoin(coin: CryptoEntity, newState: Boolean) {
        coin.favorite = newState
        cryptoDao.updateFavoriteCoin(coin)
    }
}