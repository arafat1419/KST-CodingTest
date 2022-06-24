package com.arafat1419.codingtest.core.data

import com.arafat1419.codingtest.core.data.remote.RemoteDataSource
import com.arafat1419.codingtest.core.data.remote.response.ApiResponse
import com.arafat1419.codingtest.core.data.remote.response.UsersResponse
import com.arafat1419.codingtest.core.domain.domain.UserDomain
import com.arafat1419.codingtest.core.domain.repository.IDataRepository
import com.arafat1419.codingtest.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class DataRepository(
    private val remoteDataSource: RemoteDataSource
) : IDataRepository {

    // Using NetworkBoundService for give option to save data in local
    override fun getUsers(): Flow<Resource<List<UserDomain>>> =
        object : NetworkBoundService<List<UserDomain>, List<UsersResponse>>() {
            override suspend fun load(data: List<UsersResponse>): Flow<List<UserDomain>> =
                listOf(data.map {
                    // Convert data from response to domain
                    DataMapper.usersResponseToDomain(it)
                }).asFlow()

            // Call api
            override suspend fun createCall(): Flow<ApiResponse<List<UsersResponse>>> =
                remoteDataSource.getUsers()

            // Save to local if needed
            override suspend fun saveCallResult(data: List<UsersResponse>) {}
        }.asFlow()
}