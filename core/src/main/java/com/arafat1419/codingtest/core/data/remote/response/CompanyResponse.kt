package com.arafat1419.codingtest.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class CompanyResponse(

    @field:SerializedName("bs")
    val bs: String? = null,

    @field:SerializedName("catchPhrase")
    val catchPhrase: String? = null,

    @field:SerializedName("name")
    val name: String? = null
)
