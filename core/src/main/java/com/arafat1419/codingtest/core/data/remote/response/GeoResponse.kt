package com.arafat1419.codingtest.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class GeoResponse(

    @field:SerializedName("lat")
    val lat: String? = null,

    @field:SerializedName("lng")
    val lng: String? = null

)