package com.adrian.thecrypto.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adrian.thecrypto.core.domain.usecase.CryptoUseCase

class FavoriteViewModel(cryptoUseCase: CryptoUseCase): ViewModel() {
    val favoriteCoin = cryptoUseCase.getFavoriteCoin().asLiveData()
}