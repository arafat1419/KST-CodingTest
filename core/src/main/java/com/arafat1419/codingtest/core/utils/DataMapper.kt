package com.arafat1419.codingtest.core.utils

import com.arafat1419.codingtest.core.data.remote.response.UsersResponse
import com.arafat1419.codingtest.core.domain.domain.AddressDomain
import com.arafat1419.codingtest.core.domain.domain.CompanyDomain
import com.arafat1419.codingtest.core.domain.domain.GeoDomain
import com.arafat1419.codingtest.core.domain.domain.UserDomain

object DataMapper {

    fun usersResponseToDomain(input: UsersResponse): UserDomain =
        UserDomain(
            input.id,
            input.name,
            input.username,
            input.email,
            AddressDomain(
                input.address?.street,
                input.address?.suite,
                input.address?.city,
                input.address?.zipcode,
                GeoDomain(
                    input.address?.geo?.lat,
                    input.address?.geo?.lng
                )
            ),
            input.phone,
            input.website,
            CompanyDomain(
                input.company?.bs,
                input.company?.catchPhrase,
                input.company?.name
            )
        )
}