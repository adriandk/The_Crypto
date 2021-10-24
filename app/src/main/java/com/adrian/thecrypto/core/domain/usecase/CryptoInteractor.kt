package com.adrian.thecrypto.core.domain.usecase

import com.adrian.thecrypto.core.domain.model.Crypto
import com.adrian.thecrypto.core.domain.repository.ICryptoRepository

class CryptoInteractor(private val cryptoRepository: ICryptoRepository): CryptoUseCase {

    override fun loadAllCoin(sort: String) = cryptoRepository.loadAllCoin(sort)

    override fun getDetailCoin(id: String) = cryptoRepository.getDetailCoin(id)

    override fun getFavoriteCoin() = cryptoRepository.getFavoriteCoin()

    override fun searchCoin(search: String) = cryptoRepository.searchCoin(search)

    override fun setFavoriteCoin(coin: Crypto, state: Boolean) {
        cryptoRepository.setFavoriteCoin(coin, state)
    }
}