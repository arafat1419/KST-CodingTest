package com.arafat1419.codingtest.core.data.remote

import com.arafat1419.codingtest.core.data.remote.api.ApiService
import com.arafat1419.codingtest.core.data.remote.response.ApiResponse
import com.arafat1419.codingtest.core.data.remote.response.UsersResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    // Try to get users and set status response by flow
    suspend fun getUsers(): Flow<ApiResponse<List<UsersResponse>>> = flow {
        try {
            val listResponse = apiService.getUsers()
            if (listResponse.isNotEmpty()) {
                emit(ApiResponse.Success(listResponse))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
        }
    }.flowOn(Dispatchers.IO)

}