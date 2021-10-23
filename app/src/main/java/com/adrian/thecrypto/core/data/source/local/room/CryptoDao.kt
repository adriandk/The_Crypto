package com.adrian.thecrypto.core.data.source.local.room

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.adrian.thecrypto.core.data.source.local.entity.CryptoEntity
import kotlinx.coroutines.flow.Flow

interface CryptoDao {
    @RawQuery(observedEntities = [CryptoEntity::class])
    fun getCoinList(query: SimpleSQLiteQuery): Flow<List<CryptoEntity>>

    @Query("SELECT * FROM crypto where id = :id")
    fun getDetailCoin(id: String): Flow<CryptoEntity>

    @Query("SELECT * FROM crypto where name LIKE '%' || :searchQuery || '%'")
    fun searchCoin(searchQuery: String): Flow<List<CryptoEntity>>

    @Query("SELECT * FROM crypto where favorite = 1")
    fun getFavoriteCoin(): Flow<List<CryptoEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCoin(coin: List<CryptoEntity>)

    @Update
    fun updateFavoriteCoin(coin: CryptoEntity)
}