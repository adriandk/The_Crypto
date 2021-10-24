package com.adrian.thecrypto.core.di

import com.adrian.thecrypto.core.domain.usecase.CryptoInteractor
import com.adrian.thecrypto.core.domain.usecase.CryptoUseCase
import com.adrian.thecrypto.core.viewmodel.DetailViewModel
import com.adrian.thecrypto.core.viewmodel.FavoriteViewModel
import com.adrian.thecrypto.core.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<CryptoUseCase> { CryptoInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}