package com.juarai.capstone.data

import androidx.lifecycle.LiveData
import com.juarai.capstone.data.local.UserDao
import com.juarai.capstone.data.local.UserEntity

class Repository(
    private val dao: UserDao
): IRepository {
    override fun getUser(): LiveData<List<UserEntity>> {
        return dao.getUser()
    }

    override fun insert(userEntity: UserEntity) {
        dao.insert(userEntity)
    }
}