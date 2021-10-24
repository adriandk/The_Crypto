package com.adrian.thecrypto.core.utils

import com.adrian.thecrypto.core.data.source.local.entity.CryptoEntity
import com.adrian.thecrypto.core.data.source.remote.response.CryptoResponse
import com.adrian.thecrypto.core.domain.model.Crypto

object DataMapper {

    fun mapResponseToEntities(data: List<CryptoResponse>): List<CryptoEntity>{
        val listCoin = ArrayList<CryptoEntity>()
        data.map {
            val coin = CryptoEntity(
                id = it.id,
                symbol = it.symbol,
                name = it.name,
                image = it.image,
                price = it.price,
                percent = it.percentage,
                volume = it.volume,
                ath = it.ath,
                marketcap = it.marketCap,
                high = it.high,
                low = it.low,
                supply = it.maxSupply,
                favorite = false
            )
            listCoin.add(coin)
        }
        return listCoin
    }

    fun mapEntitiesToDomain(data: List<CryptoEntity>): List<Crypto> =
        data.map {
            Crypto(
                id = it.id,
                symbol = it.symbol,
                name = it.name,
                image = it.image,
                price = it.price,
                percent = it.percent,
                volume = it.volume,
                ath = it.ath,
                marketCap = it.marketcap,
                high = it.high,
                low = it.low,
                supply = it.supply,
                favorite = it.favorite
            )
        }

    fun mapDomainToEntity(data: Crypto) = CryptoEntity(
        id = data.id,
        symbol = data.symbol,
        name = data.name,
        image = data.image,
        price = data.price,
        percent = data.percent,
        volume = data.volume,
        ath = data.ath,
        marketcap = data.marketCap,
        high = data.high,
        low = data.low,
        supply = data.supply,
        favorite = data.favorite
    )
}