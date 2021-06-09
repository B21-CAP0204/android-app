package com.juarai.capstone.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user")
@Parcelize
data class UserEntity(
    @PrimaryKey
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
    val occupation: String = "",
    val fileUriKK: String = ""
) : Parcelable