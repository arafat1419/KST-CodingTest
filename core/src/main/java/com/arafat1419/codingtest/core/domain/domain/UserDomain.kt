package com.arafat1419.codingtest.core.domain.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class UserDomain(

    val id: Int? = null,
    val name: String? = null,
    val username: String? = null,
    val email: String? = null,
    val address: @RawValue AddressDomain? = null,
    val phone: String? = null,
    val website: String? = null,
    val company: @RawValue CompanyDomain? = null

) : Parcelable