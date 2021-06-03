package com.juarai.capstone.ui.register

import androidx.lifecycle.ViewModel
import com.juarai.capstone.data.Repository
import com.juarai.capstone.data.local.UserEntity

class RegisterViewModel(private val repository: Repository) : ViewModel() {
    fun insert(userEntity: UserEntity) {
        repository.insert(userEntity)
    }
}