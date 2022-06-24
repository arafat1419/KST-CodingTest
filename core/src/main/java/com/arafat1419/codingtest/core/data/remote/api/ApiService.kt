package com.arafat1419.codingtest.core.data.remote.api

import com.arafat1419.codingtest.core.data.remote.response.UsersResponse
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<UsersResponse>

}