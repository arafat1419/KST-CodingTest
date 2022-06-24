package com.arafat1419.codingtest.di

import com.arafat1419.codingtest.core.domain.usecase.DataInteractor
import com.arafat1419.codingtest.core.domain.usecase.DataUseCase
import com.arafat1419.codingtest.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// Set use case module
val useCaseModule = module {
    factory<DataUseCase> { DataInteractor(get()) }
}

// Set view model module
val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}