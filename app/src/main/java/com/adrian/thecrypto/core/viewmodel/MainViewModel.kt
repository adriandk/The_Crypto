package com.adrian.thecrypto.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adrian.thecrypto.core.domain.usecase.CryptoUseCase

class MainViewModel(private val cryptoUseCase: CryptoUseCase): ViewModel() {
    fun getAllCoin(sort: String) = cryptoUseCase.loadAllCoin(sort).asLiveData()
    fun searchCoin(search: String) = cryptoUseCase.searchCoin(search).asLiveData()
}