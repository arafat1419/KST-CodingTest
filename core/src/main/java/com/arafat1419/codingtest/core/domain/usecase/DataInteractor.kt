package com.arafat1419.codingtest.core.domain.usecase

import com.arafat1419.codingtest.core.data.Resource
import com.arafat1419.codingtest.core.domain.domain.UserDomain
import com.arafat1419.codingtest.core.domain.repository.IDataRepository
import kotlinx.coroutines.flow.Flow

class DataInteractor(private val iDataRepository: IDataRepository) : DataUseCase {
    override fun getUsers(): Flow<Resource<List<UserDomain>>> = iDataRepository.getUsers()
}