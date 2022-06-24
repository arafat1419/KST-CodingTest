package com.arafat1419.codingtest.core.domain.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class AddressDomain(

    val street: String? = null,
    val suite: String? = null,
    val city: String? = null,
    val zipcode: String? = null,
    val geo: @RawValue GeoDomain? = null,
) : Parcelable