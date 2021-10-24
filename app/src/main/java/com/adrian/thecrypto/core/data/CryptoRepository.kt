package com.adrian.thecrypto.core.data

import com.adrian.thecrypto.core.data.source.local.LocalDataSource
import com.adrian.thecrypto.core.data.source.remote.RemoteDataSource
import com.adrian.thecrypto.core.data.source.remote.network.ApiResponse
import com.adrian.thecrypto.core.data.source.remote.response.CryptoResponse
import com.adrian.thecrypto.core.domain.model.Crypto
import com.adrian.thecrypto.core.domain.repository.ICryptoRepository
import com.adrian.thecrypto.core.utils.AppExecutors
import com.adrian.thecrypto.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CryptoRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
):ICryptoRepository {

    override fun loadAllCoin(sort: String): Flow<Resource<List<Crypto>>> =
        object : NetworkBoundResource<List<Crypto>, List<CryptoResponse>>() {
            override fun loadFromDB(): Flow<List<Crypto>> {
                return localDataSource.getAllCoin(sort).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Crypto>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<CryptoResponse>>> =
                remoteDataSource.getAllCoin()

            override suspend fun saveCallResult(data: List<CryptoResponse>) {
                val coinList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertCoin(coinList)
            }
        }.asFlow()

    override fun getFavoriteCoin(): Flow<List<Crypto>> {
        return localDataSource.getFavoriteCoin().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun searchCoin(search: String): Flow<List<Crypto>> {
        return localDataSource.searchCoin(search).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteCoin(coin: Crypto, state: Boolean) {
        val dataCoin = DataMapper.mapDomainToEntity(coin)
        appExecutors.diskIO().execute {
            localDataSource.updateCoin(dataCoin, state)
        }
    }
}