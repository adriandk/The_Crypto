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
                price = it.price.toString(),
                percent = it.percentage.toString(),
                volume = it.volume.toString(),
                desc = it.description.desc,
                favorite = false
            )
            listCoin.add(coin)
        }
        return listCoin
    }

    fun mapResponseToEntity(data: CryptoResponse) = CryptoEntity(
        id = data.id,
        symbol = data.symbol,
        name = data.name,
        image = data.image,
        price = data.price.toString(),
        percent = data.percentage.toString(),
        volume = data.volume.toString(),
        desc = data.description.desc,
        favorite = false
    )

    fun mapEntityToDomain(data: CryptoEntity) = Crypto(
        id = data.id,
        symbol = data.symbol,
        name = data.name,
        image = data.image,
        price = data.price,
        percent = data.percent,
        volume = data.volume,
        description = data.desc,
        favorite = false
    )

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
                description = it.desc,
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
        desc = data.description,
        favorite = data.favorite
    )
}