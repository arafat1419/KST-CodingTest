package com.arafat1419.codingtest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.arafat1419.codingtest.core.data.Resource
import com.arafat1419.codingtest.core.domain.domain.UserDomain
import com.arafat1419.codingtest.core.domain.usecase.DataUseCase

class MainViewModel(private val useCase: DataUseCase) : ViewModel() {
    // Get users from DataUseCase as flow and convert to live data
    fun getUsers(): LiveData<Resource<List<UserDomain>>> = useCase.getUsers().asLiveData()
}