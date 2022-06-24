package com.arafat1419.codingtest.core.domain.usecase

import com.arafat1419.codingtest.core.data.Resource
import com.arafat1419.codingtest.core.domain.domain.UserDomain
import kotlinx.coroutines.flow.Flow

interface DataUseCase {
    fun getUsers(): Flow<Resource<List<UserDomain>>>
}