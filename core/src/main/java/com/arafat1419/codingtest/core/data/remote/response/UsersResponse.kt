package com.arafat1419.codingtest.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class UsersResponse(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("username")
    val username: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("address")
    val address: AddressResponse? = null,
    @field:SerializedName("phone")
    val phone: String? = null,

    @field:SerializedName("website")
    val website: String? = null,

    @field:SerializedName("company")
    val company: CompanyResponse? = null

)