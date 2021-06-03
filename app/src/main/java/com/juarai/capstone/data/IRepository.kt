package com.juarai.capstone.data

import androidx.lifecycle.LiveData
import com.juarai.capstone.data.local.UserEntity

interface IRepository {
    fun getUser(): LiveData<List<UserEntity>>

    fun insert(userEntity: UserEntity)
}