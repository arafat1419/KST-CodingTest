package com.arafat1419.codingtest.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class AddressResponse(

    @field:SerializedName("street")
    val street: String? = null,

    @field:SerializedName("suite")
    val suite: String? = null,

    @field:SerializedName("city")
    val city: String? = null,

    @field:SerializedName("zipcode")
    val zipcode: String? = null,

    @field:SerializedName("geo")
    val geo: GeoResponse? = null,

    )