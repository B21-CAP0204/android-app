package com.juarai.capstone.util

import com.juarai.capstone.data.local.UserEntity
import com.juarai.capstone.data.network.UserResponse

object DataMapper {
    fun toValidation(userResponse: UserResponse, uri: String): UserEntity{
        return UserEntity(
            nik = userResponse.nik,
            name = userResponse.name,
            birthPlace = userResponse.birthPlace,
            birthDate = userResponse.birthDate,
            golDar = userResponse.golDar,
            gender = userResponse.gender,
            address = userResponse.gender,
            rt = userResponse.rt,
            rw = userResponse.rw,
            kelurahan = userResponse.kelurahan,
            kecamatan = userResponse.kecamatan,
            religion = userResponse.religion,
            occupation = userResponse.occupation,
            fileUriKK = uri
        )
    }
}