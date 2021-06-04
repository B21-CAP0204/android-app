package com.juarai.capstone.data.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserResponse(
    val nik: String = "",
    val name: String = "",
    val birthPlace: String = "",
    val birthDate: String = "",
    val golDar: String = "",
    val gender: String = "",
    val address: String = "",
    val rt: Int = 0,
    val rw: Int = 0,
    val kelurahan: String = "",
    val kecamatan: String = "",
    val religion: String = "",
    val occupation: String = ""
) : Parcelable
