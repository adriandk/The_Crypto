package com.adrian.thecrypto.core.viewmodel

import androidx.lifecycle.ViewModel
import com.adrian.thecrypto.core.domain.model.Crypto
import com.adrian.thecrypto.core.domain.usecase.CryptoUseCase

class DetailViewModel(private val cryptoUseCase: CryptoUseCase): ViewModel() {
    fun setFavoriteCoin(coin: Crypto, newStat: Boolean) = cryptoUseCase.setFavoriteCoin(coin, newStat)
}